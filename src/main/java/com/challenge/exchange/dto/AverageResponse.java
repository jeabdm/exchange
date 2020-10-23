package com.challenge.exchange.dto;

import java.math.BigDecimal;

public class AverageResponse {

	private BigDecimal average;
	private BigDecimal differende;
	private int cantidad;
	
	
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getAverage() {
		return average;
	}
	public void setAverage(BigDecimal average) {
		this.average = average;
	}
	public BigDecimal getDifferende() {
		return differende;
	}
	public void setDifferende(BigDecimal differende) {
		this.differende = differende;
	}
}
