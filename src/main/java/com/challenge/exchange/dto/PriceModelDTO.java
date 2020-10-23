package com.challenge.exchange.dto;

public class PriceModelDTO {

//	@JsonProperty("lprice")
	private String lprice;
	
//	@JsonProperty("curr1")
	private String curr1;
	
//	@JsonProperty("curr2")
	private String curr2;
	
	public String getLprice() {
		return lprice;
	}
	public void setLprice(String lprice) {
		this.lprice = lprice;
	}
	public String getCurr1() {
		return curr1;
	}
	public void setCurr1(String curr1) {
		this.curr1 = curr1;
	}
	public String getCurr2() {
		return curr2;
	}
	public void setCurr2(String curr2) {
		this.curr2 = curr2;
	}
}
