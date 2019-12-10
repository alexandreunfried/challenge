package com.aunfried.challenge.business.orderrecordproduct.dto;

import lombok.Data;

@Data
public class OrderRecordProductDTO {

	private Long id;
	private String name;
	private Double units;
	private Double unitPrice;
	private Double amount;

}
