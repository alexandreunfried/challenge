package com.aunfried.challenge.business.orderrecord.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {

}