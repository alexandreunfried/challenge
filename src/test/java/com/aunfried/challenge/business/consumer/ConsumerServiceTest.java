package com.aunfried.challenge.business.consumer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.consumer.domain.Consumer;
import com.aunfried.challenge.business.consumer.dto.ConsumerCreateUpdateDTO;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
class ConsumerServiceTest {
	
	@Autowired
	ConsumerService consumerService;

	@Transactional
	@Test
	void testCreateUpdate() {
		
		ConsumerCreateUpdateDTO createJohn = new ConsumerCreateUpdateDTO();
		createJohn.setName("John Doe");
		createJohn.setPhone("+554512345678");
		createJohn.setEmail("some@one.com");
		
		Consumer john = consumerService.createUpdate(createJohn);
		assertNotNull(john);
		assertNotNull(john.getId());
		assertEquals(createJohn.getName(), john.getName());
		assertEquals(createJohn.getPhone(), john.getPhone());
		assertEquals(createJohn.getEmail(), john.getEmail());
		
		createJohn.setName("John Doe");
		createJohn.setPhone("+554512345678");
		createJohn.setEmail("some@one.com");
		
		ConsumerCreateUpdateDTO createMichael = new ConsumerCreateUpdateDTO();
		createMichael.setName("Michael Lincoln");
		createMichael.setPhone("+554511223344");
		createMichael.setEmail("michael@a.com");
		
		Consumer michael = consumerService.createUpdate(createMichael);
		assertNotNull(michael);
		assertNotNull(michael.getId());
		assertEquals(createMichael.getName(), michael.getName());
		assertEquals(createMichael.getPhone(), michael.getPhone());
		assertEquals(createMichael.getEmail(), michael.getEmail());
		
		ConsumerCreateUpdateDTO updateJohn = new ConsumerCreateUpdateDTO();
		updateJohn.setName("John L. Doe");
		updateJohn.setPhone("+554511225678");
		updateJohn.setEmail("some@one.com");
		
		Consumer johnUpdated = consumerService.createUpdate(updateJohn);
		assertNotNull(johnUpdated);
		assertNotNull(johnUpdated.getId());
		assertEquals(johnUpdated.getId(), john.getId());
		assertEquals(updateJohn.getName(), johnUpdated.getName());
		assertEquals(updateJohn.getPhone(), johnUpdated.getPhone());
		assertEquals(updateJohn.getEmail(), johnUpdated.getEmail());
	}

	@Transactional
	@Test
	void testMergeConsumer() {
		Consumer consumer = new Consumer();
		
		ConsumerCreateUpdateDTO createJohn = new ConsumerCreateUpdateDTO();
		createJohn.setName("John Doe");
		createJohn.setPhone("+554512345678");
		createJohn.setEmail("some@one.com");
		
		consumerService.mergeConsumer(consumer, createJohn);
		assertNull(consumer.getId());
		assertEquals(createJohn.getName(), consumer.getName());
		assertEquals(createJohn.getPhone(), consumer.getPhone());
		assertEquals(createJohn.getEmail(), consumer.getEmail());
		
		
		Consumer john = consumerService.createUpdate(createJohn);
		consumerService.mergeConsumer(john, createJohn);
		assertNotNull(john.getId());
		assertEquals(createJohn.getName(), john.getName());
		assertEquals(createJohn.getPhone(), john.getPhone());
		assertEquals(createJohn.getEmail(), john.getEmail());
		
		
	}

}
