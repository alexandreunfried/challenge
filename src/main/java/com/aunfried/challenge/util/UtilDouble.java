package com.aunfried.challenge.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UtilDouble {

	private UtilDouble() {
	}

	public static Double getMonetaryDouble(Double value) {
		return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
	}
}
