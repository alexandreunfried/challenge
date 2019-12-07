package com.aunfried.challenge.business.manufacturer.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ManufacturerCreateUpdateDTO {

	@NotBlank
	private String name;
	
}
