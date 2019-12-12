package com.aunfried.challenge.business.order;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.consumer.dto.ConsumerCreateUpdateDTO;
import com.aunfried.challenge.business.delivery.domain.Delivery;
import com.aunfried.challenge.business.order.dto.OrderCreateDTO;
import com.aunfried.challenge.business.order.dto.OrderDTO;
import com.aunfried.challenge.business.orderrecord.domain.OrderRecord;
import com.aunfried.challenge.business.orderrecordproduct.OrderRecordProductService;
import com.aunfried.challenge.business.orderrecordproduct.domain.OrderRecordProduct;
import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductCreateDTO;
import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductDTO;
import com.aunfried.challenge.business.payment.dto.PaymentCreateDTO;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
class OrderServiceTest {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRecordProductService orderRecordProductService;

	@Test
	@Transactional
	void testCreate() {

		OrderCreateDTO orderCreateDTO = new OrderCreateDTO();

		OrderRecordProductCreateDTO product1 = new OrderRecordProductCreateDTO();
		product1.setId(25L);
		product1.setUnits(2.0);

		OrderRecordProductCreateDTO product2 = new OrderRecordProductCreateDTO();
		product2.setId(26L);
		product2.setUnits(2.25);

		OrderRecordProductCreateDTO product3 = new OrderRecordProductCreateDTO();
		product3.setId(29L);
		product3.setUnits(1.0);

		List<OrderRecordProductCreateDTO> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		orderCreateDTO.setProducts(products);

		ConsumerCreateUpdateDTO consumer = new ConsumerCreateUpdateDTO();
		consumer.setName("John Doe");
		consumer.setPhone("+554512345678");
		consumer.setEmail("some@one.com");
		orderCreateDTO.setConsumer(consumer);

		PaymentCreateDTO payment = new PaymentCreateDTO();
		payment.setMode("bank slip");
		payment.setInstallments(3);
		orderCreateDTO.setPayment(payment);

		Delivery delivery = new Delivery();
		delivery.setMode("in-store withdrawal");
		orderCreateDTO.setDelivery(delivery);

		Long id = orderService.create(orderCreateDTO);
		assertNotNull(id);

	}

	@Test
	@Transactional
	void testGet() {
		Long id = createOrderToTest();
		assertNotNull(id);

		OrderDTO order = orderService.get(id);
		assertNotNull(order);

		assertEquals(id, order.getId());
		assertEquals(OrderService.Const.STATUS_PENDING_CONFIRMATION, order.getStatus());

		assertEquals(3, order.getProducts().size());

		assertEquals(25L, order.getProducts().get(0).getId());
		assertEquals("Orange juice", order.getProducts().get(0).getName());
		assertEquals(2.0, order.getProducts().get(0).getUnits());
		assertEquals(10.00, order.getProducts().get(0).getUnitPrice());
		assertEquals(20.00, order.getProducts().get(0).getAmount());

		assertEquals(26L, order.getProducts().get(1).getId());
		assertEquals("Apple juice", order.getProducts().get(1).getName());
		assertEquals(2.25, order.getProducts().get(1).getUnits());
		assertEquals(15.50, order.getProducts().get(1).getUnitPrice());
		assertEquals(34.87, order.getProducts().get(1).getAmount());

		assertEquals(29L, order.getProducts().get(2).getId());
		assertEquals("Grape juice", order.getProducts().get(2).getName());
		assertEquals(1.0, order.getProducts().get(2).getUnits());
		assertEquals(36.65, order.getProducts().get(2).getUnitPrice());
		assertEquals(36.65, order.getProducts().get(2).getAmount());

		assertNotNull(order.getConsumer().getId());
		assertEquals("John Doe", order.getConsumer().getName());
		assertEquals("+554512345678", order.getConsumer().getPhone());
		assertEquals("some@one.com", order.getConsumer().getEmail());

		assertNotNull(order.getPayment().getId());
		assertEquals("bank slip", order.getPayment().getMode());
		assertEquals(91.52, order.getPayment().getAmount());
		assertEquals(3, order.getPayment().getInstallments());
		assertEquals(30.51, order.getPayment().getInstallmentValue());

		assertNotNull(order.getDelivery());
		assertEquals("in-store withdrawal", order.getDelivery().getMode());
	}

	@Test
	@Transactional
	void testCancel() {
		Long id = createOrderToTest();
		assertNotNull(id);

		orderService.cancel(id);

		OrderDTO order = orderService.get(id);
		assertNotNull(order);

		assertEquals(id, order.getId());
		assertEquals(OrderService.Const.STATUS_CANCELED, order.getStatus());
	}

	@Test
	@Transactional
	void testConfirm() {
		Long id = createOrderToTest();
		assertNotNull(id);

		orderService.confirm(id);

		OrderDTO order = orderService.get(id);
		assertNotNull(order);

		assertEquals(id, order.getId());
		assertEquals(OrderService.Const.STATUS_CONFIRMED, order.getStatus());
	}

	@Test
	@Transactional
	void testGetOrderRecord() {
		Long id = createOrderToTest();
		assertNotNull(id);

		OrderRecord order = orderService.getOrderRecord(id);
		assertNotNull(order);

		assertEquals(id, order.getId());
		assertEquals(OrderService.Const.STATUS_PENDING_CONFIRMATION, order.getStatus());

		assertNotNull(order.getConsumer().getId());
		assertEquals("John Doe", order.getConsumer().getName());
		assertEquals("+554512345678", order.getConsumer().getPhone());
		assertEquals("some@one.com", order.getConsumer().getEmail());

		assertNotNull(order.getPayment().getId());
		assertEquals("bank slip", order.getPayment().getMode());
		assertEquals(91.52, order.getPayment().getAmount());
		assertEquals(3, order.getPayment().getInstallments());
		assertEquals(30.51, order.getPayment().getInstallmentValue());

		assertNotNull(order.getDelivery());
		assertEquals("in-store withdrawal", order.getDelivery().getMode());
	}

	@Test
	@Transactional
	void testMapperToOrderRecordProductDTOListOfOrderRecordProduct() {
		Long id = createOrderToTest();

		List<OrderRecordProduct> products = orderRecordProductService.getByIdOrderRecord(id);

		List<OrderRecordProductDTO> productsDTO = orderService.mapperToOrderRecordProductDTO(products);

		assertEquals(3, productsDTO.size());

		assertEquals(25L, productsDTO.get(0).getId());
		assertEquals("Orange juice", productsDTO.get(0).getName());
		assertEquals(2.0, productsDTO.get(0).getUnits());
		assertEquals(10.00, productsDTO.get(0).getUnitPrice());
		assertEquals(20.00, productsDTO.get(0).getAmount());

		assertEquals(26L, productsDTO.get(1).getId());
		assertEquals("Apple juice", productsDTO.get(1).getName());
		assertEquals(2.25, productsDTO.get(1).getUnits());
		assertEquals(15.50, productsDTO.get(1).getUnitPrice());
		assertEquals(34.87, productsDTO.get(1).getAmount());

		assertEquals(29L, productsDTO.get(2).getId());
		assertEquals("Grape juice", productsDTO.get(2).getName());
		assertEquals(1.0, productsDTO.get(2).getUnits());
		assertEquals(36.65, productsDTO.get(2).getUnitPrice());
		assertEquals(36.65, productsDTO.get(2).getAmount());

	}

	@Test
	@Transactional
	void testMapperToOrderRecordProductDTOOrderRecordProduct() {
		Long id = createOrderToTest();

		List<OrderRecordProduct> products = orderRecordProductService.getByIdOrderRecord(id);

		OrderRecordProductDTO productDTO = orderService.mapperToOrderRecordProductDTO(products.get(0));

		assertEquals(25L, productDTO.getId());
		assertEquals("Orange juice", productDTO.getName());
		assertEquals(2.0, productDTO.getUnits());
		assertEquals(10.00, productDTO.getUnitPrice());
		assertEquals(20.00, productDTO.getAmount());

	}

	private Long createOrderToTest() {
		OrderCreateDTO orderCreateDTO = new OrderCreateDTO();

		OrderRecordProductCreateDTO product1 = new OrderRecordProductCreateDTO();
		product1.setId(25L);
		product1.setUnits(2.0);

		OrderRecordProductCreateDTO product2 = new OrderRecordProductCreateDTO();
		product2.setId(26L);
		product2.setUnits(2.25);

		OrderRecordProductCreateDTO product3 = new OrderRecordProductCreateDTO();
		product3.setId(29L);
		product3.setUnits(1.0);

		List<OrderRecordProductCreateDTO> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		orderCreateDTO.setProducts(products);

		ConsumerCreateUpdateDTO consumer = new ConsumerCreateUpdateDTO();
		consumer.setName("John Doe");
		consumer.setPhone("+554512345678");
		consumer.setEmail("some@one.com");
		orderCreateDTO.setConsumer(consumer);

		PaymentCreateDTO payment = new PaymentCreateDTO();
		payment.setMode("bank slip");
		payment.setInstallments(3);
		orderCreateDTO.setPayment(payment);

		Delivery delivery = new Delivery();
		delivery.setMode("in-store withdrawal");
		orderCreateDTO.setDelivery(delivery);

		return orderService.create(orderCreateDTO);
	}

}
