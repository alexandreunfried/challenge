package com.aunfried.challenge.business.orderrecord.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.aunfried.challenge.business.consumer.domain.Consumer;
import com.aunfried.challenge.business.delivery.domain.Delivery;
import com.aunfried.challenge.business.payment.domain.Payment;

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
public class OrderRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String status;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_consumer", referencedColumnName = "id")
	private Consumer consumer;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_payment", referencedColumnName = "id")
	private Payment payment;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_delivery", referencedColumnName = "mode")
	private Delivery delivery;

}
