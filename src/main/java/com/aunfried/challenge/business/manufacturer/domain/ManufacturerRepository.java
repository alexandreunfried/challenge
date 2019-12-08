package com.aunfried.challenge.business.manufacturer.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
	
	@Query("select m from Manufacturer m order by m.id ASC")
    public List<Manufacturer> findAllOrderById(Pageable page);

}