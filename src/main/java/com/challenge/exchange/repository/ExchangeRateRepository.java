package com.challenge.exchange.repository;

import reactor.core.publisher.Mono;

public interface ExchangeRateRepository {

	public Mono<String> getPrice(String currency);
}
