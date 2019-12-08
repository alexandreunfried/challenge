package com.aunfried.challenge.business.orderrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.consumer.ConsumerService;
import com.aunfried.challenge.business.consumer.domain.Consumer;
import com.aunfried.challenge.business.orderrecord.domain.OrderRecordRepository;
import com.aunfried.challenge.business.orderrecord.dto.OrderRecordCreateDTO;

@Service
public class OrderRecordService {

	@Autowired
	private OrderRecordRepository orderRecordRepository;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Transactional
	public Long create(OrderRecordCreateDTO orderRecordCreateDTO) {
		
		Consumer consumer = consumerService.createUpdate(orderRecordCreateDTO.getConsumer());
		
		return null;
	}

}
