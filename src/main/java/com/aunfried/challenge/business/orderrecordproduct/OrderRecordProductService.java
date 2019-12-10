package com.aunfried.challenge.business.orderrecordproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.orderrecordproduct.domain.OrderRecordProduct;
import com.aunfried.challenge.business.orderrecordproduct.domain.OrderRecordProductId;
import com.aunfried.challenge.business.orderrecordproduct.domain.OrderRecordProductRepository;
import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductCreateDTO;
import com.aunfried.challenge.business.product.ProductService;
import com.aunfried.challenge.business.product.domain.Product;
import com.aunfried.challenge.util.UtilDouble;

@Service
public class OrderRecordProductService {

	@Autowired
	private OrderRecordProductRepository orderRecordProductRepository;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	public void createProductsOrder(Long idOrderRecord, List<OrderRecordProductCreateDTO> productsOrder) {
		productsOrder.forEach(productOrder -> {
			createProductOrder(idOrderRecord, productOrder);
		});
	}
	
	protected void createProductOrder(Long idOrderRecord, OrderRecordProductCreateDTO productOrder) {
		
		OrderRecordProduct orderRecordProduct = new OrderRecordProduct();
		
		OrderRecordProductId orderRecordProductId = new OrderRecordProductId(idOrderRecord, productOrder.getId());
		orderRecordProduct.setOrderRecordProductId(orderRecordProductId);
		
		orderRecordProduct.setUnits(productOrder.getUnits());
		
		Product product = productService.get(productOrder.getId());
		orderRecordProduct.setName(product.getName());
		orderRecordProduct.setUnitPrice(product.getUnitPrice());
		
		Double amount = product.getUnitPrice() * productOrder.getUnits();
		orderRecordProduct.setAmount(UtilDouble.getMonetaryDouble(amount));
		
		orderRecordProductRepository.save(orderRecordProduct);
		
	}
	
	@Transactional(readOnly = true)
	public List<OrderRecordProduct> getByIdOrderRecord(Long idOrderRecord){
		return orderRecordProductRepository.findByIdOrderRecord(idOrderRecord);
	}
}
