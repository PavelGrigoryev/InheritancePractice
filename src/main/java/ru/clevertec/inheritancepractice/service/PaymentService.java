package ru.clevertec.inheritancepractice.service;

import org.springframework.data.domain.Pageable;
import ru.clevertec.inheritancepractice.dto.request.PaymentRequest;
import ru.clevertec.inheritancepractice.dto.response.DeleteResponse;
import ru.clevertec.inheritancepractice.dto.response.PaymentResponse;

import java.util.List;

public interface PaymentService {

    PaymentResponse findById(Long id);

    List<PaymentResponse> findAll(Pageable pageable);

    PaymentResponse save(PaymentRequest request);

    PaymentResponse updateById(Long id, PaymentRequest request);

    DeleteResponse deleteById(Long id);

}
