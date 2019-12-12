package com.aunfried.challenge.business.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.delivery.domain.Delivery;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
class DeliveryServiceTest {
	
	@Autowired
	DeliveryService deliveryService;


	@Transactional
	@Test
	void testCreateUpdate() {
		Delivery createDelivery = new Delivery();
		createDelivery.setMode("in-store withdrawal");
		
		Delivery delivery = deliveryService.createUpdate(createDelivery);
		assertEquals(createDelivery.getMode(), delivery.getMode());
		
		Delivery deliveryUpdated = deliveryService.createUpdate(delivery);
		assertEquals(delivery.getMode(), deliveryUpdated.getMode());
	}

}
