package com.aunfried.challenge.business.consumer.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    public Optional<Consumer> findByEmail(String email);
}