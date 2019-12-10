package com.aunfried.challenge.business.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.consumer.ConsumerService;
import com.aunfried.challenge.business.consumer.domain.Consumer;
import com.aunfried.challenge.business.delivery.DeliveryService;
import com.aunfried.challenge.business.delivery.domain.Delivery;
import com.aunfried.challenge.business.order.dto.OrderCreateDTO;
import com.aunfried.challenge.business.orderrecord.domain.OrderRecord;
import com.aunfried.challenge.business.orderrecord.domain.OrderRecordRepository;
import com.aunfried.challenge.business.orderrecordproduct.OrderRecordProductService;
import com.aunfried.challenge.business.payment.PaymentService;
import com.aunfried.challenge.business.payment.domain.Payment;
import com.aunfried.challenge.business.product.ProductService;
import com.aunfried.challenge.config.exception.ErrorCode;
import com.aunfried.challenge.config.exception.NotFoundException;

@Service
public class OrderService {

	public static class Const {

		private Const() {
		}

		public static final String STATUS_PENDING_CONFIRMATION = "pending confirmation";
		public static final String STATUS_CONFIRMED = "confirmed";
		public static final String STATUS_CANCELED = "canceled";
	}

	@Autowired
	private OrderRecordRepository orderRecordRepository;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderRecordProductService orderRecordProductService;

	@Transactional
	public Long create(OrderCreateDTO orderCreateDTO) {

		OrderRecord orderRecord = new OrderRecord();
		orderRecord.setStatus(Const.STATUS_PENDING_CONFIRMATION);

		Consumer consumer = consumerService.createUpdate(orderCreateDTO.getConsumer());
		orderRecord.setConsumer(consumer);

		Double amount = productService.getAmount(orderCreateDTO.getProducts());
		
		Payment payment = paymentService.create(orderCreateDTO.getPayment(), amount);
		orderRecord.setPayment(payment);

		Delivery delivery = deliveryService.createUpdate(orderCreateDTO.getDelivery());
		orderRecord.setDelivery(delivery);

		orderRecord = orderRecordRepository.save(orderRecord);

		orderRecordProductService.createProductsOrder(orderRecord, orderCreateDTO.getProducts());

		return orderRecord.getId();
	}
	
	@Transactional
	public OrderRecord get(Long id) {
		Optional<OrderRecord> orderOptional = orderRecordRepository.findById(id);

		if (!orderOptional.isPresent()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND, "Pedido não encontrado");
		}

		return orderOptional.get();
	}

}
