package com.challenge.exchange.repository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Repository
public class ExchangeRateRepositoryImp implements ExchangeRateRepository{
	
	private final WebClient webClient;
	private final String EXCHANGE_RATE_BASE = "https://cex.io";
	public static final String BTC_RATE = "/api/last_price/BTC/USD";

    public ExchangeRateRepositoryImp() {
        this.webClient = WebClient.builder().baseUrl(EXCHANGE_RATE_BASE)
        		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        		.build();
    }

    public Mono<String> getPrice(String currency){
        	return webClient.get().uri(currency).
                    retrieve().bodyToMono(String.class);
    }

}
