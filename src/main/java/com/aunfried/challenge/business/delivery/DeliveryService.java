package com.aunfried.challenge.business.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.delivery.domain.Delivery;
import com.aunfried.challenge.business.delivery.domain.DeliveryRepository;

@Service
public class DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Transactional
	public Delivery createUpdate(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}
}
