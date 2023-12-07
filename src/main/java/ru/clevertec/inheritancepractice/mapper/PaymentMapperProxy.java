package ru.clevertec.inheritancepractice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.inheritancepractice.dto.request.PaymentByERIPRequest;
import ru.clevertec.inheritancepractice.dto.request.PaymentByRequisiteRequest;
import ru.clevertec.inheritancepractice.dto.request.PaymentRequest;
import ru.clevertec.inheritancepractice.dto.response.PaymentResponse;
import ru.clevertec.inheritancepractice.exception.WrongPaymentTypeException;
import ru.clevertec.inheritancepractice.model.Payment;
import ru.clevertec.inheritancepractice.model.PaymentByERIP;
import ru.clevertec.inheritancepractice.model.PaymentByRequisite;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentMapperProxy {

    private final PaymentMapper paymentMapper;

    public PaymentResponse toResponse(Payment payment) {
        return switch (payment.getPaymentType()) {
            case ERIP -> paymentMapper.toERIPResponse((PaymentByERIP) payment);
            case REQUISITE -> paymentMapper.toRequisiteResponse((PaymentByRequisite) payment);
        };
    }

    public Payment fromRequest(PaymentRequest request) {
        return switch (request.getPaymentType()) {
            case ERIP -> {
                PaymentByERIPRequest req = (PaymentByERIPRequest) request;
                yield Optional.ofNullable(req.getFieldForInsertion())
                        .flatMap(field -> Optional.ofNullable(req.getValueForInsertion()))
                        .map(value -> paymentMapper.toERIPEntity(req))
                        .orElseThrow(() -> new WrongPaymentTypeException(
                                "Payment with type ERIP must contains fields: field_for_insertion and value_for_insertion"));
            }
            case REQUISITE -> {
                PaymentByRequisiteRequest req = (PaymentByRequisiteRequest) request;
                yield Optional.ofNullable(req.getReceiver())
                        .flatMap(receiver -> Optional.ofNullable(req.getUnp()))
                        .flatMap(unp -> Optional.ofNullable(req.getDestination()))
                        .map(destination -> paymentMapper.toRequisiteEntity(req))
                        .orElseThrow(() -> new WrongPaymentTypeException(
                                "Payment with type REQUISITE must contains fields: unp, receiver and destination"));
            }
        };
    }

}
