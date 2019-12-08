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

import com.aunfried.challenge.business.manufacturer.ManufacturerService;
import com.aunfried.challenge.business.manufacturer.domain.Manufacturer;
import com.aunfried.challenge.business.manufacturer.dto.ManufacturerCreateUpdateDTO;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;

	@GetMapping("/{id}")
	public ResponseEntity<Manufacturer> get(@PathVariable Long id) {
		Manufacturer manufacturer = manufacturerService.get(id);

		return ResponseEntity.ok(manufacturer);
	}

	@GetMapping
	public ResponseEntity<List<Manufacturer>> list(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		List<Manufacturer> list = manufacturerService.list(page, size);

		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<Long> create(
			@RequestBody @Validated ManufacturerCreateUpdateDTO manufacturerCreateUpdateDTO) {

		Long id = manufacturerService.create(manufacturerCreateUpdateDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Manufacturer> update(@PathVariable Long id,
			@RequestBody @Validated ManufacturerCreateUpdateDTO manufacturerCreateUpdateDTO) {

		Manufacturer manufacturer = manufacturerService.update(id, manufacturerCreateUpdateDTO);

		return ResponseEntity.ok(manufacturer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		manufacturerService.delete(id);

		return ResponseEntity.noContent().build();
	}

}
