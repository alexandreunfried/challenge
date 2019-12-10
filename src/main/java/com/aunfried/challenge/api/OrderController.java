package com.aunfried.challenge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aunfried.challenge.business.order.OrderService;
import com.aunfried.challenge.business.order.dto.OrderCreateDTO;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

//	@GetMapping("/{id}")
//	public ResponseEntity<Product> get(@PathVariable Long id) {
//		Product product = orderService.get(id);
//
//		return ResponseEntity.ok(product);
//	}

	@PostMapping
	public ResponseEntity<Long> create(@RequestBody @Validated OrderCreateDTO orderRecordCreateDTO) {

		Long id = orderService.create(orderRecordCreateDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<Product> update(@PathVariable Long id,
//			@RequestBody @Validated ProductCreateUpdateDTO productCreateUpdateDTO) {
//
//		Product product = orderService.update(id, productCreateUpdateDTO);
//
//		return ResponseEntity.ok(product);
//	}

}
