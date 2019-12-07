package com.aunfried.challenge.service;

import com.aunfried.challenge.model.Manufacturer;
import com.aunfried.challenge.repository.ManufacturerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufaturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Long create(String name) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);

        manufacturer = manufacturerRepository.save(manufacturer);
        return manufacturer.getId();
    }


}
