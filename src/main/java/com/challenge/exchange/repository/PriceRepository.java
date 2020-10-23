package com.challenge.exchange.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.challenge.exchange.entity.Price;

import reactor.core.publisher.Flux;

public interface PriceRepository extends ReactiveCrudRepository<Price, Long>{

	Flux<Price> findByCurrencyOrigin(String co);
	Flux<Price> findByIdBetween(Long i, Long f);
}
