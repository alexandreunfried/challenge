package com.aunfried.challenge.business.payment;

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
		payment.setInstallmentValue(amount / (1.0 * paymentCreateDTO.getInstallments()));
		
		return paymentRepository.save(payment);
	}
}
