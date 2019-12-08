package com.aunfried.challenge.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aunfried.challenge.business.product.ProductService;
import com.aunfried.challenge.business.product.domain.Product;
import com.aunfried.challenge.business.product.dto.ProductCreateUpdateDTO;
import com.aunfried.challenge.business.product.dto.SimpleProductDTO;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{id}")
	public ResponseEntity<Product> get(@PathVariable Long id) {
		Product product = productService.get(id);

		return ResponseEntity.ok(product);
	}

	@GetMapping
	public ResponseEntity<List<SimpleProductDTO>> list(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		List<SimpleProductDTO> list = productService.list(page, size);

		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<Long> create(@RequestBody @Validated ProductCreateUpdateDTO productCreateUpdateDTO) {

		Long id = productService.create(productCreateUpdateDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,
			@RequestBody @Validated ProductCreateUpdateDTO productCreateUpdateDTO) {

		productService.update(id, productCreateUpdateDTO);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		productService.delete(id);

		return ResponseEntity.noContent().build();
	}


}
