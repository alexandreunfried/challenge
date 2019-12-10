package com.aunfried.challenge.business.orderrecordproduct.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderRecordProductCreateDTO {

	@NotNull
	private Long id;
	
	@NotNull
	private Double units;
	
}
