package com.aunfried.challenge.business.orderrecordproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.orderrecord.domain.OrderRecord;
import com.aunfried.challenge.business.orderrecordproduct.domain.OrderRecordProduct;
import com.aunfried.challenge.business.orderrecordproduct.domain.OrderRecordProductId;
import com.aunfried.challenge.business.orderrecordproduct.domain.OrderRecordProductRepository;
import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductCreateDTO;
import com.aunfried.challenge.business.product.ProductService;
import com.aunfried.challenge.business.product.domain.Product;

@Service
public class OrderRecordProductService {

	@Autowired
	private OrderRecordProductRepository orderRecordProductRepository;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	public void createProductsOrder(OrderRecord orderRecord, List<OrderRecordProductCreateDTO> productsOrder) {
		productsOrder.forEach(productOrder -> {
			createProductOrder(orderRecord, productOrder);
		});
	}
	
	protected void createProductOrder(OrderRecord orderRecord, OrderRecordProductCreateDTO productOrder) {
		
		OrderRecordProduct orderRecordProduct = new OrderRecordProduct();
		
		OrderRecordProductId orderRecordProductId = new OrderRecordProductId(orderRecord.getId(), productOrder.getId());
		orderRecordProduct.setOrderRecordProductId(orderRecordProductId);
		
		orderRecordProduct.setUnits(productOrder.getUnits());
		orderRecordProduct.setOrderRecord(orderRecord);
		
		Product product = productService.get(productOrder.getId());
		orderRecordProduct.setProduct(product);
		
		orderRecordProductRepository.save(orderRecordProduct);
		
	}
}
