package com.aunfried.challenge.business.orderrercordproduct.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderRecordProductCreateDTO {

	@NotNull
	private Long id;
	
	@NotNull
	private Integer units;
	
}
