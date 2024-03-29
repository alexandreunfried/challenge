package com.aunfried.challenge.business.product.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aunfried.challenge.business.product.dto.SimpleProductDTO;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select new com.aunfried.challenge.business.product.dto.SimpleProductDTO(p.id, p.name) from Product p order by p.id ASC")
    public List<SimpleProductDTO> findAllOrderById(Pageable page);
	
	@Query("select count(p) from Product p where p.manufacturer.id = ?1")
    public Integer countByManufacturer(Long idManufacturer);
	
	@Query("select p.unitPrice from Product p where p.id = ?1")
    public Optional<Double> findUnitPriceById(Long idProduct);
	
}