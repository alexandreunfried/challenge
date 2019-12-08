package com.aunfried.challenge.business.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductCreateUpdateDTO {

	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String barcode;
	
	@NotNull
	private Long idManufacturer;
	
	@NotNull
	private Double unitPrice;
	
}
