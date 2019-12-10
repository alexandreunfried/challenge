package com.aunfried.challenge.business.consumer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.consumer.domain.Consumer;
import com.aunfried.challenge.business.consumer.domain.ConsumerRepository;
import com.aunfried.challenge.business.consumer.dto.ConsumerCreateUpdateDTO;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Transactional
	public Consumer createUpdate(ConsumerCreateUpdateDTO consumerCreateUpdateDTO) {
		Optional<Consumer> consumerOptional = consumerRepository.findByEmail(consumerCreateUpdateDTO.getEmail());

		Consumer consumer;

		if (consumerOptional.isPresent()) {
			consumer = consumerOptional.get();
		} else {
			consumer = new Consumer();
		}

		mergeConsumer(consumer, consumerCreateUpdateDTO);

		return consumerRepository.save(consumer);
	}

	protected void mergeConsumer(Consumer consumer, ConsumerCreateUpdateDTO consumerCreateUpdateDTO) {
		consumer.setName(consumerCreateUpdateDTO.getName());
		consumer.setEmail(consumerCreateUpdateDTO.getEmail());
		consumer.setPhone(consumerCreateUpdateDTO.getPhone());
	}

}
