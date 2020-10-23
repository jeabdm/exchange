package com.challenge.exchange.service;

import com.challenge.exchange.dto.AverageResponse;
import com.challenge.exchange.entity.Price;

import reactor.core.publisher.Mono;


public interface ExchangeRateService {

	public void updateCurrentBTCRate();
	public Mono<AverageResponse> getPricesRange(Long from, Long until);
	Mono<Price> getPrice(Long id);
}
