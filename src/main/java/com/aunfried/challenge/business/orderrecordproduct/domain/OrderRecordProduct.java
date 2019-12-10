package com.aunfried.challenge.business.orderrecordproduct.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.aunfried.challenge.business.orderrecord.domain.OrderRecord;
import com.aunfried.challenge.business.product.domain.Product;

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

	@ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.MERGE)
	@MapsId("idOrderRecord")
	@JoinColumn(name = "id_order_record", referencedColumnName = "id")
	private OrderRecord orderRecord;

	@ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.MERGE)
	@MapsId("idProduct")
	@JoinColumn(name = "id_product", referencedColumnName = "id")
	private Product product;

	@NotNull
	@Column(nullable = false)
	private Double units;

	@NotNull
	@Column(name = "unit_price", nullable = false)
	private Double unitPrice;

	@NotNull
	@Column(nullable = false)
	private Double amount;

	@PrePersist
	public void prePersist() {
		this.unitPrice = this.product.getUnitPrice();
		this.amount = this.unitPrice * this.units;
	}

}
