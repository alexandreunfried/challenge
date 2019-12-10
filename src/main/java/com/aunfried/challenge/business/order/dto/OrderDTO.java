package com.aunfried.challenge.business.order.dto;

import java.util.List;

import com.aunfried.challenge.business.consumer.domain.Consumer;
import com.aunfried.challenge.business.delivery.domain.Delivery;
import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductDTO;
import com.aunfried.challenge.business.payment.domain.Payment;

import lombok.Data;

@Data
public class OrderDTO {

	private Long id;
	private String status;
	private List<OrderRecordProductDTO> products;
	private Consumer consumer;
	private Payment payment;
	private Delivery delivery;
}
