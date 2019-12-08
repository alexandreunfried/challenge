package com.aunfried.challenge.business.product.dto;

import lombok.Data;

@Data
public class SimpleProductDTO {
	
	private Long id;
	private String name;
	
	public SimpleProductDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
