package com.challenge.exchange.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.challenge.exchange.dto.PriceModelDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExchangeUtils {

	public static PriceModelDTO mapStringToJson(String plainResponse) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			PriceModelDTO jj = objectMapper.readValue(plainResponse, PriceModelDTO.class);
			return jj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static BigDecimal getPercentageDifference(BigDecimal value1, BigDecimal value2) {
		return value1.subtract(value2).abs().divide(value2, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
	}
}
