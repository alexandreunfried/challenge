package com.aunfried.challenge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aunfried.challenge.business.order.OrderService;
import com.aunfried.challenge.business.order.dto.OrderCreateDTO;
import com.aunfried.challenge.business.order.dto.OrderDTO;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> get(@PathVariable Long id) {
		OrderDTO order = orderService.get(id);

		return ResponseEntity.ok(order);
	}

	@PostMapping
	public ResponseEntity<Long> create(@RequestBody @Validated OrderCreateDTO orderRecordCreateDTO) {

		Long id = orderService.create(orderRecordCreateDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PutMapping("/cancel/{id}")
	public ResponseEntity<?> update(@PathVariable Long id) {

		orderService.cancel(id);

		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/confirm/{id}")
	public ResponseEntity<?> confirm(@PathVariable Long id) {

		orderService.confirm(id);

		return ResponseEntity.noContent().build();
	}

}
