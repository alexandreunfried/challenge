package com.aunfried.challenge.business.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aunfried.challenge.business.payment.domain.Payment;
import com.aunfried.challenge.business.payment.domain.PaymentRepository;
import com.aunfried.challenge.business.payment.dto.PaymentCreateDTO;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Transactional
	public Payment create(PaymentCreateDTO paymentCreateDTO, Double amount) {
		Payment payment = new Payment();
		payment.setInstallments(paymentCreateDTO.getInstallments());
		payment.setMode(paymentCreateDTO.getMode());
		payment.setAmount(amount);
		
		Double intallmentValue = amount / (1.0 * paymentCreateDTO.getInstallments());
		
		intallmentValue = BigDecimal.valueOf(intallmentValue)
			    .setScale(2, RoundingMode.HALF_DOWN)
			    .doubleValue();
		
		payment.setInstallmentValue(intallmentValue);
		
		return paymentRepository.save(payment);
	}
}
