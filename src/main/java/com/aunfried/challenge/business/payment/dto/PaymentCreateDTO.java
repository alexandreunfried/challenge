package com.aunfried.challenge.business.payment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PaymentCreateDTO {

	@NotBlank
	private String mode;

	@NotNull
	private Integer installments;
}
