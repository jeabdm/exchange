package com.challenge.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.exchange.dto.AverageResponse;
import com.challenge.exchange.entity.Price;
import com.challenge.exchange.service.ExchangeRateService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ExchangeController {
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@GetMapping("/price/{id}")
	public Mono<ResponseEntity<Price>> getCurrent2(@PathVariable(value = "id") Long id){
		return this.exchangeRateService.getPrice(id)
				.map(response -> ResponseEntity.ok(response))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/pricesRange/{from}/{until}")
	public Mono<ResponseEntity<AverageResponse>> getPricesRange(@PathVariable(value = "from") Long from,
			@PathVariable(value = "until") Long until){
		return this.exchangeRateService.getPricesRange(from, until)
				.map(response -> ResponseEntity.ok(response))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
