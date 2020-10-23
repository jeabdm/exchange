package com.challenge.exchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.challenge.exchange.entity.Price;
import com.challenge.exchange.repository.PriceRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@DataR2dbcTest
public class PriceRepositoryTest {

	@Autowired
	private PriceRepository priceRepository;
	
	
	@Test
	public void testInsert() {
		Price price = new Price();
		price.setPrice(BigDecimal.ZERO);
		price.setCreationDate(LocalDate.now());
		price.setCurrency("BTC");
		price.setCurrencyOrigin("PEPE");
		
		priceRepository.save(price).block();
		Mono<Price> findOne = priceRepository.findById(new Long(1));
		
		findOne.as(StepVerifier::create)
        .assertNext(actual -> {
            assertThat(actual.getCurrency()).isEqualTo("BTC");
        })
        .verifyComplete();
	}
	
}
