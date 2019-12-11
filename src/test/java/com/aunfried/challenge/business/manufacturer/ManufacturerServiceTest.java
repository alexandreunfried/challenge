package com.aunfried.challenge.business.manufacturer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.manufacturer.domain.Manufacturer;
import com.aunfried.challenge.business.manufacturer.dto.ManufacturerCreateUpdateDTO;
import com.aunfried.challenge.config.exception.BaseException;
import com.aunfried.challenge.config.exception.ErrorCode;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
class ManufacturerServiceTest {

	@Autowired
	ManufacturerService manufacturerService;

	@Test
	@Transactional
	void testGet() {
		Manufacturer manufacturer = manufacturerService.get(1L);
		assertNotNull(manufacturer);
		assertEquals(1L, manufacturer.getId());
		assertEquals("Made in Grill", manufacturer.getName());

		manufacturer = manufacturerService.get(2L);
		assertNotNull(manufacturer);
		assertEquals(2L, manufacturer.getId());
		assertEquals("Quality farm goods", manufacturer.getName());

		try {
			manufacturerService.get(3L);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
		} catch (Exception ex) {
			fail("expected exception not found");
		}

	}

	@Test
	@Transactional
	void testList() {

		List<Manufacturer> manufacturers = manufacturerService.list(0, 10);
		assertNotNull(manufacturers);
		assertEquals(2, manufacturers.size());

		manufacturers = manufacturerService.list(1, 10);
		assertNotNull(manufacturers);
		assertEquals(0, manufacturers.size());

		manufacturers = manufacturerService.list(1, 1);
		assertNotNull(manufacturers);
		assertEquals(1, manufacturers.size());

	}

	@Test
	@Transactional
	void testCreate() {
		ManufacturerCreateUpdateDTO manufacturerCreateUpdateDTO = new ManufacturerCreateUpdateDTO();
		manufacturerCreateUpdateDTO.setName("Burguer King");

		Long id = manufacturerService.create(manufacturerCreateUpdateDTO);
		assertNotNull(id);

		Manufacturer manufacturer = manufacturerService.get(id);
		assertNotNull(manufacturer);
		assertEquals(id, manufacturer.getId());
		assertEquals(manufacturerCreateUpdateDTO.getName(), manufacturer.getName());
	}

	@Test
	@Transactional
	void testUpdate() {
		
		ManufacturerCreateUpdateDTO manufacturerCreateUpdateDTO = new ManufacturerCreateUpdateDTO();
		manufacturerCreateUpdateDTO.setName("Burguer King");
		
		Long id = 1L;
		
		manufacturerService.update(id, manufacturerCreateUpdateDTO);

		Manufacturer manufacturer = manufacturerService.get(id);
		assertNotNull(manufacturer);
		assertEquals(id, manufacturer.getId());
		assertEquals(manufacturerCreateUpdateDTO.getName(), manufacturer.getName());
		
		try {
			manufacturerService.update(3L, manufacturerCreateUpdateDTO);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
	}

	@Test
	@Transactional
	void testDelete() {
		
		try {
			manufacturerService.delete(3L);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
		
		Manufacturer manufacturer = manufacturerService.get(2L);
		assertNotNull(manufacturer);
		
		manufacturerService.delete(2L);
				
		try {
			manufacturerService.get(2L);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
		
	}

}
