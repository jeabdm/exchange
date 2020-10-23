package com.challenge.exchange.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.exchange.cache.GlobalCache;
import com.challenge.exchange.dto.AverageResponse;
import com.challenge.exchange.dto.PriceModelDTO;
import com.challenge.exchange.entity.Price;
import com.challenge.exchange.repository.ExchangeRateRepository;
import com.challenge.exchange.repository.ExchangeRateRepositoryImp;
import com.challenge.exchange.repository.PriceRepository;
import com.challenge.exchange.util.ExchangeUtils;

import reactor.core.publisher.Mono;

@Service
public class ExchangeRateServiceImp implements ExchangeRateService{

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Override
	public void updateCurrentBTCRate(){
		Mono<String> response = exchangeRateRepository.getPrice(ExchangeRateRepositoryImp.BTC_RATE);
		response.subscribe(plainResponse -> {this.processResponse(plainResponse);});
	}
	
	@Override
	public Mono<AverageResponse> getPricesRange(Long from, Long until){
		return this.priceRepository.findByIdBetween(from, until)
				.collectList().map(this::calculateAverage);
	}
	
	public Mono<Price> getPrice(Long id){
		return this.priceRepository.findById(id);
	}
	
	private AverageResponse calculateAverage(List<Price> prices){
		AverageResponse resp = new AverageResponse();
		BigDecimal avg = prices.stream().map(Price::getPrice).
				reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(prices.size()), 2, RoundingMode.HALF_UP);
		
		resp.setCantidad(prices.size());
		resp.setAverage(avg);
		resp.setDifferende(ExchangeUtils.getPercentageDifference(GlobalCache.maxPrice, avg));
		
		return resp;
	}
	
	private void processResponse(String plainResponse) {
		PriceModelDTO priceModel = ExchangeUtils.mapStringToJson(plainResponse);
		Price price = new Price(priceModel);
		this.saveMaxPrice(price);
		this.priceRepository.save(price).subscribe();
	}

	private void saveMaxPrice(Price price) {
		if(GlobalCache.maxPrice.compareTo(price.getPrice()) < 0) {
			GlobalCache.maxPrice = price.getPrice();
		}
	}
}
