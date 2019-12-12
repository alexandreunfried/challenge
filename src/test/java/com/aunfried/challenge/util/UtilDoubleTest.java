package com.aunfried.challenge.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "test")
public class UtilDoubleTest {
	
	public UtilDoubleTest() {
    }
	
	@Test
	public void testGetMonetaryDouble() {
		
		assertEquals(0.0, UtilDouble.getMonetaryDouble(0.0));
		assertEquals(1.5, UtilDouble.getMonetaryDouble(1.5));
		assertEquals(1.5, UtilDouble.getMonetaryDouble(1.50));
		assertEquals(1.55, UtilDouble.getMonetaryDouble(1.55));
		assertEquals(1.55, UtilDouble.getMonetaryDouble(1.555));
		assertEquals(1.56, UtilDouble.getMonetaryDouble(1.556));
	}

}
