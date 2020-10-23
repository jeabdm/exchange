package com.challenge.exchange.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.challenge.exchange.dto.PriceModelDTO;

@Table("exchange_prices")
public class Price {

	@Id
	private Long id;
	private LocalDate creationDate = LocalDate.now();
	private BigDecimal price;
	private String currencyOrigin;
	private String currency;

	public Price(BigDecimal price, String currency, String currencyOrigin) {
		this.price = price;
		this.currency = currency;
		this.currencyOrigin = currencyOrigin;
	}
	
	public Price() {

	}

	public Price(PriceModelDTO dto) {
		this.price = new BigDecimal(dto.getLprice());
		this.currencyOrigin = dto.getCurr1();
		this.currency = dto.getCurr2();
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurrencyOrigin() {
		return currencyOrigin;
	}

	public void setCurrencyOrigin(String currencyOrigin) {
		this.currencyOrigin = currencyOrigin;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
