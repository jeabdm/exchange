package com.challenge.exchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.exchange.dto.AverageResponse;
import com.challenge.exchange.entity.Price;
import com.challenge.exchange.repository.PriceRepository;
import com.challenge.exchange.service.ExchangeRateService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@SpringBootTest
public class ServiceTest {

	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@Autowired
	private PriceRepository priceRepository;
	
    @BeforeEach
    public void init() {
		Price price = new Price(new BigDecimal(60), "BTC", "USD");
		Price price2 = new Price(new BigDecimal(40), "BTC", "USD");
		Price price3 = new Price(new BigDecimal(80), "BTC", "USD");
		priceRepository.save(price).subscribe();
		priceRepository.save(price2).subscribe();
		priceRepository.save(price3).subscribe();
    }
	
	@Test
	public void testGetOne() {

		Mono<AverageResponse> resp = this.exchangeRateService.getPricesRange(1L, 2L);
		
		resp.as(StepVerifier::create)
        .assertNext(actual -> {
            assertThat(actual.getAverage()).isEqualTo("50.00");
        })
        .verifyComplete();
	}
}
