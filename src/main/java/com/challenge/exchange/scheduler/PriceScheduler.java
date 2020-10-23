package com.challenge.exchange.scheduler;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.challenge.exchange.service.ExchangeRateService;

@Component
public class PriceScheduler {
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	@Scheduled(fixedDelay = 10000)
	@Retryable(value = { TimeoutException.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000))
	public void getCurrentPrice(){
		System.out.println("Scheduler running...");
		this.exchangeRateService.updateCurrentBTCRate();
	}
}
