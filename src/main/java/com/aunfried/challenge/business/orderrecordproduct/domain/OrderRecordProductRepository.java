package com.aunfried.challenge.business.orderrecordproduct.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRecordProductRepository extends JpaRepository<OrderRecordProduct, OrderRecordProductId> {

}