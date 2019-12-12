package com.aunfried.challenge.business.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.manufacturer.ManufacturerService;
import com.aunfried.challenge.business.manufacturer.domain.Manufacturer;
import com.aunfried.challenge.business.orderrecordproduct.dto.OrderRecordProductCreateDTO;
import com.aunfried.challenge.business.product.domain.Product;
import com.aunfried.challenge.business.product.domain.ProductRepository;
import com.aunfried.challenge.business.product.dto.ProductCreateUpdateDTO;
import com.aunfried.challenge.business.product.dto.SimpleProductDTO;
import com.aunfried.challenge.config.exception.ErrorCode;
import com.aunfried.challenge.config.exception.NotFoundException;
import com.aunfried.challenge.util.UtilDouble;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ManufacturerService manufacturerService;

	@Transactional(readOnly = true)
	public Product get(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND, "Produto não encontrado");
		}

		return productOptional.get();
	}
	
	@Transactional(readOnly = true)
	public Double getUnitPrice(Long id) {
		Optional<Double> unitPriceOptional = productRepository.findUnitPriceById(id);

		if (!unitPriceOptional.isPresent()) {
			throw new NotFoundException(ErrorCode.NOT_FOUND, "Produto não encontrado");
		}

		return unitPriceOptional.get();
	}

	@Transactional(readOnly = true)
	public List<SimpleProductDTO> list(Integer page, Integer size) {
		return productRepository.findAllOrderById(PageRequest.of(page, size));
	}

	@Transactional
	public Long create(ProductCreateUpdateDTO productCreateUpdateDTO) {
		Product product = new Product();

		mergeToProduct(product, productCreateUpdateDTO);

		product = productRepository.save(product);
		return product.getId();
	}

	@Transactional
	public Product update(Long id, ProductCreateUpdateDTO productCreateUpdateDTO) {
		Product product = get(id);

		mergeToProduct(product, productCreateUpdateDTO);

		return productRepository.save(product);
	}

	@Transactional
	public void delete(Long id) {
		Product product = get(id);

		productRepository.delete(product);
	}
	
	@Transactional(readOnly = true)
	public Double getAmount(List<OrderRecordProductCreateDTO> products) {
		Double amount = 0.0;

		for (int i = 0; i < products.size(); i++) {
			amount += getUnitPrice(products.get(i).getId()) * products.get(i).getUnits();
		}
		
		return UtilDouble.getMonetaryDouble(amount);

	}

	protected void mergeToProduct(Product product,
			ProductCreateUpdateDTO productCreateUpdateDTO) {

		Manufacturer manufacturer = manufacturerService.get(productCreateUpdateDTO.getIdManufacturer());

		product.setName(productCreateUpdateDTO.getName());
		product.setDescription(productCreateUpdateDTO.getDescription());
		product.setBarcode(productCreateUpdateDTO.getBarcode());
		product.setManufacturer(manufacturer);
		
		Double unitPrice = UtilDouble.getMonetaryDouble(productCreateUpdateDTO.getUnitPrice());
		product.setUnitPrice(unitPrice);

	}

}
