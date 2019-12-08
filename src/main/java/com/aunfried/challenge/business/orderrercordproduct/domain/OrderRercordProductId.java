package com.aunfried.challenge.business.orderrercordproduct.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Embeddable
public class OrderRercordProductId implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@NotNull
	@Column(name = "id_order_record")
	private Long idOrderRecord;

	@EqualsAndHashCode.Include
	@NotNull
	@Column(name = "id_product")
	private Long idProduct;

	public OrderRercordProductId() {
	}

	public OrderRercordProductId(Long idOrderRecord, Long idProduct) {
		this.idOrderRecord = idOrderRecord;
		this.idProduct = idProduct;
	}

}
