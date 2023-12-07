package ru.clevertec.inheritancepractice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.inheritancepractice.dto.request.PaymentRequest;
import ru.clevertec.inheritancepractice.dto.response.DeleteResponse;
import ru.clevertec.inheritancepractice.dto.response.PaymentResponse;
import ru.clevertec.inheritancepractice.exception.NotFoundException;
import ru.clevertec.inheritancepractice.exception.ServiceException;
import ru.clevertec.inheritancepractice.mapper.PaymentMapperProxy;
import ru.clevertec.inheritancepractice.model.Payment;
import ru.clevertec.inheritancepractice.repository.PaymentRepository;
import ru.clevertec.inheritancepractice.service.PaymentService;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapperProxy paymentMapper;

    @Override
    public PaymentResponse findById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toResponse)
                .orElseThrow(throwNotFoundException(id));
    }

    @Override
    public List<PaymentResponse> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PaymentResponse save(PaymentRequest request) {
        return Optional.of(request)
                .map(paymentMapper::fromRequest)
                .map(paymentRepository::save)
                .map(paymentMapper::toResponse)
                .orElseThrow(() -> new ServiceException("Cant save payment"));
    }

    @Override
    @Transactional
    public PaymentResponse updateById(Long id, PaymentRequest request) {
        return paymentRepository.findByPaymentTypeAndId(request.getPaymentType(), id)
                .map(payment -> {
                    Payment fromRequest = paymentMapper.fromRequest(request);
                    fromRequest.setId(payment.getId());
                    return fromRequest;
                })
                .map(paymentRepository::save)
                .map(paymentMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Payment with id %s and type %s is not found"
                        .formatted(id, request.getPaymentType())));
    }

    @Override
    @Transactional
    public DeleteResponse deleteById(Long id) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    paymentRepository.delete(payment);
                    return payment;
                })
                .map(payment -> new DeleteResponse("Payment with id %s was successfully deleted".formatted(id)))
                .orElseThrow(throwNotFoundException(id));
    }

    private Supplier<NotFoundException> throwNotFoundException(Long id) {
        return () -> new NotFoundException("Payment with id %s is not found".formatted(id));
    }

}
