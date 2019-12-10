package com.aunfried.challenge.business.order.dto;

import java.util.List;

import javax.validation.Valid;

import com.aunfried.challenge.business.consumer.dto.ConsumerCreateUpdateDTO;
import com.aunfried.challenge.business.delivery.domain.Delivery;
import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductCreateDTO;
import com.aunfried.challenge.business.payment.dto.PaymentCreateDTO;

import lombok.Data;

@Data
public class OrderCreateDTO {

	@Valid
	private List<OrderRecordProductCreateDTO> products;
	
	@Valid
	private ConsumerCreateUpdateDTO consumer;
	
	@Valid
	private PaymentCreateDTO payment;
	
	@Valid
	private Delivery delivery;
}
