package com.aunfried.challenge.business.orderrecordproduct.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class OrderRecordProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected OrderRecordProductId orderRecordProductId;
	
	@NotNull
	@Column(nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false)
	private Double units;

	@NotNull
	@Column(name = "unit_price", nullable = false)
	private Double unitPrice;

	@NotNull
	@Column(nullable = false)
	private Double amount;

}
