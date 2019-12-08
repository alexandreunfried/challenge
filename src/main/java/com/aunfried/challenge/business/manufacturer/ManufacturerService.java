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
import com.aunfried.challenge.config.exception.ErrorCode;
import com.aunfried.challenge.config.exception.NotFoundException;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

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
	public Long create(ManufacturerCreateUpdateDTO manufacturerCreateUpdate) {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(manufacturerCreateUpdate.getName());

		manufacturer = manufacturerRepository.save(manufacturer);
		return manufacturer.getId();
	}

	@Transactional
	public void update(Long id, ManufacturerCreateUpdateDTO manufacturerCreateUpdateDTO) {
		Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);

		if (!manufacturerOptional.isPresent()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND, "Fabricante não encontrado");
		}

		Manufacturer manufacturer = manufacturerOptional.get();
		manufacturer.setName(manufacturerCreateUpdateDTO.getName());

		manufacturerRepository.save(manufacturer);
	}

}
