package com.aunfried.challenge.business.product.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aunfried.challenge.business.product.dto.SimpleProductDTO;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select new com.aunfried.challenge.business.product.dto.SimpleProductDTO(p.id, p.name) from Product p order by p.id ASC")
    public List<SimpleProductDTO> findAllOrderById(Pageable page);

}