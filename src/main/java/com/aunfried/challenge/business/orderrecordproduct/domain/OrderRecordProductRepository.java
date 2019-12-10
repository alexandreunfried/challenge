package com.aunfried.challenge.business.orderrecordproduct.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRecordProductRepository extends JpaRepository<OrderRecordProduct, OrderRecordProductId> {

	@Query("select o from OrderRecordProduct o where o.orderRecordProductId.idOrderRecord = ?1")
	public List<OrderRecordProduct> findByIdOrderRecord(Long idOrderRecord);
}