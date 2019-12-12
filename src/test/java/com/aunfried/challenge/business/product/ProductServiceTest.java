package com.aunfried.challenge.business.product;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductCreateDTO;
import com.aunfried.challenge.business.product.domain.Product;
import com.aunfried.challenge.business.product.dto.ProductCreateUpdateDTO;
import com.aunfried.challenge.business.product.dto.SimpleProductDTO;
import com.aunfried.challenge.config.exception.BaseException;
import com.aunfried.challenge.config.exception.ErrorCode;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
class ProductServiceTest {
	
	@Autowired
	ProductService productService;

	@Test
	@Transactional
	void testGet() {
		
		Product product = productService.get(25L);
		assertNotNull(product);
		assertEquals(25L, product.getId());
		assertEquals("Orange juice", product.getName());
		assertEquals("Delicious natural orange juice. No addition of apples to fool consumers.", product.getDescription());
		assertEquals("8901072002478", product.getBarcode());
		assertEquals(2, product.getManufacturer().getId());
		assertEquals(10.00, product.getUnitPrice());

		product = productService.get(26L);
		assertNotNull(product);
		assertEquals(26L, product.getId());
		assertEquals("Apple juice", product.getName());
		assertEquals("Delicious natural apple juice.", product.getDescription());
		assertEquals("1201072002421", product.getBarcode());
		assertEquals(2, product.getManufacturer().getId());
		assertEquals(15.50, product.getUnitPrice());

		try {
			productService.get(5L);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
	}

	@Test
	@Transactional
	void testGetUnitPrice() {
		
		Double unitPrice = productService.getUnitPrice(25L);
		assertNotNull(unitPrice);
		assertEquals(10.00, unitPrice);

		unitPrice = productService.getUnitPrice(26L);
		assertNotNull(unitPrice);
		assertEquals(15.50, unitPrice);

		try {
			productService.getUnitPrice(5L);
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
		
		List<SimpleProductDTO> products = productService.list(0, 10);
		assertNotNull(products);
		assertEquals(3, products.size());
		assertEquals(25, products.get(0).getId());
		assertEquals("Orange juice", products.get(0).getName());
		assertEquals(26, products.get(1).getId());
		assertEquals(29, products.get(2).getId());

		products = productService.list(1, 10);
		assertNotNull(products);
		assertEquals(0, products.size());

		products = productService.list(1, 1);
		assertNotNull(products);
		assertEquals(1, products.size());
		assertEquals(26, products.get(0).getId());
	}

	@Test
	@Transactional
	void testCreate() {
		
		ProductCreateUpdateDTO productCreateUpdateDTO = new ProductCreateUpdateDTO();
		productCreateUpdateDTO.setName("Lemon juice");
		productCreateUpdateDTO.setDescription("Delicious natural lemon juice.");
		productCreateUpdateDTO.setBarcode("9101074507821");
		productCreateUpdateDTO.setIdManufacturer(2L);
		productCreateUpdateDTO.setUnitPrice(12.999);

		Long id = productService.create(productCreateUpdateDTO);
		assertNotNull(id);

		Product product = productService.get(id);
		assertNotNull(product);
		assertEquals(id, product.getId());
		assertEquals(productCreateUpdateDTO.getName(), product.getName());
		assertEquals(productCreateUpdateDTO.getDescription(), product.getDescription());
		assertEquals(productCreateUpdateDTO.getBarcode(), product.getBarcode());
		assertEquals(productCreateUpdateDTO.getIdManufacturer(), product.getManufacturer().getId());
		assertEquals(13.00, product.getUnitPrice());
		
		ProductCreateUpdateDTO product2CreateUpdateDTO = new ProductCreateUpdateDTO();
		product2CreateUpdateDTO.setName("Strawberry juice");
		product2CreateUpdateDTO.setDescription("Delicious natural strawberry juice.");
		product2CreateUpdateDTO.setBarcode("1234574507821");
		product2CreateUpdateDTO.setIdManufacturer(15L);
		product2CreateUpdateDTO.setUnitPrice(12.00);
		
		try {
			productService.create(product2CreateUpdateDTO);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
			assertEquals("Fabricante não encontrado", ex.getMessage());
		} catch (Exception ex) {
			fail("expected exception not found");
		}

	}

	@Test
	@Transactional
	void testUpdate() {
		
		ProductCreateUpdateDTO productCreateUpdateDTO = new ProductCreateUpdateDTO();
		productCreateUpdateDTO.setName("Orange juice");
		productCreateUpdateDTO.setDescription("Delicious natural orange juice.");
		productCreateUpdateDTO.setBarcode("9101074507821");
		productCreateUpdateDTO.setIdManufacturer(2L);
		productCreateUpdateDTO.setUnitPrice(18.999);

		productService.update(25L, productCreateUpdateDTO);

		Product product = productService.get(25L);
		assertNotNull(product);
		assertEquals(25L, product.getId());
		assertEquals(productCreateUpdateDTO.getName(), product.getName());
		assertEquals(productCreateUpdateDTO.getDescription(), product.getDescription());
		assertEquals(productCreateUpdateDTO.getBarcode(), product.getBarcode());
		assertEquals(productCreateUpdateDTO.getIdManufacturer(), product.getManufacturer().getId());
		assertEquals(19.00, product.getUnitPrice());
		
		productCreateUpdateDTO.setIdManufacturer(15L);
		
		try {
			productService.update(25L, productCreateUpdateDTO);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
			assertEquals("Fabricante não encontrado", ex.getMessage());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
		
		ProductCreateUpdateDTO product2CreateUpdateDTO = new ProductCreateUpdateDTO();
		product2CreateUpdateDTO.setName("Strawberry juice");
		product2CreateUpdateDTO.setDescription("Delicious natural strawberry juice.");
		product2CreateUpdateDTO.setBarcode("1234574507821");
		product2CreateUpdateDTO.setIdManufacturer(15L);
		product2CreateUpdateDTO.setUnitPrice(12.00);
		
		try {
			productService.update(5L, product2CreateUpdateDTO);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
			assertEquals("Produto não encontrado", ex.getMessage());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
	}

	@Test
	@Transactional
	void testDelete() {
		
		try {
			productService.delete(3L);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
		
		Product product = productService.get(25L);
		assertNotNull(product);
		
		productService.delete(25L);
				
		try {
			productService.get(25L);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
		
	}

	@Test
	@Transactional
	void testGetAmount() {
		
		OrderRecordProductCreateDTO product1 = new OrderRecordProductCreateDTO();
		product1.setId(25L);
		product1.setUnits(2.0);
		
		OrderRecordProductCreateDTO product2 = new OrderRecordProductCreateDTO();
		product2.setId(26L);
		product2.setUnits(2.25);
		
		OrderRecordProductCreateDTO product3 = new OrderRecordProductCreateDTO();
		product3.setId(29L);
		product3.setUnits(1.0);
		
		List<OrderRecordProductCreateDTO> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		Double amount = productService.getAmount(products);
		assertEquals(91.52, amount);
		
		OrderRecordProductCreateDTO product4 = new OrderRecordProductCreateDTO();
		product4.setId(12L);
		product4.setUnits(3.0);
		
		products.add(product4);
		
		try {
			productService.getAmount(products);
			fail("expected exception not found");
		} catch (BaseException ex) {
			assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
			assertEquals("Produto não encontrado", ex.getMessage());
		} catch (Exception ex) {
			fail("expected exception not found");
		}
	}

	@Test
	@Transactional
	void testMergeToProduct() {
		
		ProductCreateUpdateDTO productCreateUpdateDTO = new ProductCreateUpdateDTO();
		productCreateUpdateDTO.setName("Orange juice");
		productCreateUpdateDTO.setDescription("Delicious natural orange juice.");
		productCreateUpdateDTO.setBarcode("9101074507821");
		productCreateUpdateDTO.setIdManufacturer(2L);
		productCreateUpdateDTO.setUnitPrice(18.999);
		
		Product product = new Product();
		
		productService.mergeToProduct(product, productCreateUpdateDTO);
		
		assertNotNull(product);
		assertNull(product.getId());
		assertEquals(productCreateUpdateDTO.getName(), product.getName());
		assertEquals(productCreateUpdateDTO.getDescription(), product.getDescription());
		assertEquals(productCreateUpdateDTO.getBarcode(), product.getBarcode());
		assertEquals(productCreateUpdateDTO.getIdManufacturer(), product.getManufacturer().getId());
		assertEquals(19.00, product.getUnitPrice());
		
		Product product25 = productService.get(25L);
		
		productService.mergeToProduct(product25, productCreateUpdateDTO);
		
		assertNotNull(product25);
		assertEquals(25L, product25.getId());
		assertEquals(productCreateUpdateDTO.getName(), product25.getName());
		assertEquals(productCreateUpdateDTO.getDescription(), product25.getDescription());
		assertEquals(productCreateUpdateDTO.getBarcode(), product25.getBarcode());
		assertEquals(productCreateUpdateDTO.getIdManufacturer(), product25.getManufacturer().getId());
		assertEquals(19.00, product25.getUnitPrice());
		
	}

}
