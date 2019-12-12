package com.aunfried.challenge.business.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.consumer.ConsumerService;
import com.aunfried.challenge.business.consumer.domain.Consumer;
import com.aunfried.challenge.business.delivery.DeliveryService;
import com.aunfried.challenge.business.delivery.domain.Delivery;
import com.aunfried.challenge.business.order.dto.OrderCreateDTO;
import com.aunfried.challenge.business.order.dto.OrderDTO;
import com.aunfried.challenge.business.orderrecord.domain.OrderRecord;
import com.aunfried.challenge.business.orderrecord.domain.OrderRecordRepository;
import com.aunfried.challenge.business.orderrecordproduct.OrderRecordProductService;
import com.aunfried.challenge.business.orderrecordproduct.domain.OrderRecordProduct;
import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductDTO;
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

		orderRecordProductService.createProductsOrder(orderRecord.getId(), orderCreateDTO.getProducts());

		return orderRecord.getId();
	}

	@Transactional(readOnly=true)
	public OrderDTO get(Long id) {		
		OrderRecord orderRecord = getOrderRecord(id);

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(orderRecord.getId());
		orderDTO.setStatus(orderRecord.getStatus());
		orderDTO.setConsumer(orderRecord.getConsumer());
		orderDTO.setPayment(orderRecord.getPayment());
		orderDTO.setDelivery(orderRecord.getDelivery());

		List<OrderRecordProduct> orderRecordProducts = orderRecordProductService.getByIdOrderRecord(id);

		orderDTO.setProducts(mapperToOrderRecordProductDTO(orderRecordProducts));

		return orderDTO;
	}
	
	@Transactional
	public void cancel(Long id) {
		OrderRecord orderRecord = getOrderRecord(id);
		orderRecord.setStatus(Const.STATUS_CANCELED);
		
		orderRecordRepository.save(orderRecord);
	}
	
	@Transactional
	public void confirm(Long id) {
		OrderRecord orderRecord = getOrderRecord(id);

		orderRecord.setStatus(Const.STATUS_CONFIRMED);
		
		orderRecordRepository.save(orderRecord);
	}
	
	protected OrderRecord getOrderRecord(Long id) {
		Optional<OrderRecord> orderOptional = orderRecordRepository.findById(id);

		if (!orderOptional.isPresent()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND, "Pedido não encontrado");
		}
		
		return orderOptional.get();
	}

	protected List<OrderRecordProductDTO> mapperToOrderRecordProductDTO(List<OrderRecordProduct> orderRecordProducts) {

		List<OrderRecordProductDTO> products = new ArrayList<>();

		orderRecordProducts
				.forEach(orderRecordProduct -> products.add(mapperToOrderRecordProductDTO(orderRecordProduct)));

		return products;
	}

	protected OrderRecordProductDTO mapperToOrderRecordProductDTO(OrderRecordProduct orderRecordProduct) {

		OrderRecordProductDTO orderRecordProductDTO = new OrderRecordProductDTO();

		orderRecordProductDTO.setId(orderRecordProduct.getOrderRecordProductId().getIdProduct());
		orderRecordProductDTO.setName(orderRecordProduct.getName());
		orderRecordProductDTO.setUnits(orderRecordProduct.getUnits());
		orderRecordProductDTO.setUnitPrice(orderRecordProduct.getUnitPrice());
		orderRecordProductDTO.setAmount(orderRecordProduct.getAmount());

		return orderRecordProductDTO;
	}

}
