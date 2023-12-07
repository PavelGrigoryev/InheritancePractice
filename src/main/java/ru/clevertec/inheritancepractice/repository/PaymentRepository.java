package ru.clevertec.inheritancepractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.inheritancepractice.model.Payment;
import ru.clevertec.inheritancepractice.model.PaymentType;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentTypeAndId(PaymentType paymentType, Long id);

}
