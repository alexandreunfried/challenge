package com.aunfried.challenge.business.manufacturer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.manufacturer.domain.Manufacturer;
import com.aunfried.challenge.business.manufacturer.domain.ManufacturerRepository;
import com.aunfried.challenge.business.manufacturer.dto.ManufacturerCreateUpdateDTO;
import com.aunfried.challenge.business.product.domain.ProductRepository;
import com.aunfried.challenge.config.exception.BadRequestException;
import com.aunfried.challenge.config.exception.ErrorCode;
import com.aunfried.challenge.config.exception.NotFoundException;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public Manufacturer get(Long id) {
		Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);

		if (!manufacturerOptional.isPresent()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND, "Fabricante não encontrado");
		}
		
		return manufacturerOptional.get();
	}

	@Transactional(readOnly = true)
	public List<Manufacturer> list(Integer page, Integer size) {
		return manufacturerRepository.findAllOrderById(PageRequest.of(page, size));
	}

	@Transactional
	public Long create(ManufacturerCreateUpdateDTO manufacturerCreateUpdateDTO) {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(manufacturerCreateUpdateDTO.getName());

		manufacturer = manufacturerRepository.save(manufacturer);
		return manufacturer.getId();
	}

	@Transactional
	public Manufacturer update(Long id, ManufacturerCreateUpdateDTO manufacturerCreateUpdateDTO) {
		Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);

		if (!manufacturerOptional.isPresent()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND, "Fabricante não encontrado");
		}

		Manufacturer manufacturer = manufacturerOptional.get();
		manufacturer.setName(manufacturerCreateUpdateDTO.getName());

		return manufacturerRepository.save(manufacturer);
	}
	
	@Transactional
	public void delete(Long id) {
		Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);

		if (!manufacturerOptional.isPresent()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND, "Fabricante não encontrado");
		}
		
		Integer coutProductsByManufacturer = productRepository.countByManufacturer(id);
		
		if(coutProductsByManufacturer > 0) {
			throw new BadRequestException(ErrorCode.BAD_REQUEST, "Existem produtos com este fabricante");
		}

		Manufacturer manufacturer = manufacturerOptional.get();

		manufacturerRepository.delete(manufacturer);
	}

}
