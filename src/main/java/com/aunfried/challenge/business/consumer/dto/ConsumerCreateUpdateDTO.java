package com.aunfried.challenge.business.consumer.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ConsumerCreateUpdateDTO {

	@NotBlank
	private String name;

	@NotBlank
	private String phone;

	@NotBlank
	private String email;

}
