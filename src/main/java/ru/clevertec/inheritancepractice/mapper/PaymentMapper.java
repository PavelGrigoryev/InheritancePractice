package ru.clevertec.inheritancepractice.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.inheritancepractice.dto.request.PaymentByERIPRequest;
import ru.clevertec.inheritancepractice.dto.request.PaymentByRequisiteRequest;
import ru.clevertec.inheritancepractice.dto.request.PaymentRequest;
import ru.clevertec.inheritancepractice.dto.response.PaymentByERIPResponse;
import ru.clevertec.inheritancepractice.dto.response.PaymentByRequisiteResponse;
import ru.clevertec.inheritancepractice.dto.response.PaymentResponse;
import ru.clevertec.inheritancepractice.exception.WrongPaymentTypeException;
import ru.clevertec.inheritancepractice.model.Payment;
import ru.clevertec.inheritancepractice.model.PaymentByERIP;
import ru.clevertec.inheritancepractice.model.PaymentByRequisite;

import java.util.Optional;

@Mapper
public interface PaymentMapper {

    PaymentByERIPResponse toERIPResponse(PaymentByERIP paymentByERIP);

    PaymentByRequisiteResponse toRequisiteResponse(PaymentByRequisite paymentByRequisite);

    PaymentByERIP toERIPEntity(PaymentByERIPRequest request);

    PaymentByRequisite toRequisiteEntity(PaymentByRequisiteRequest request);

    default PaymentResponse toResponse(Payment payment) {
        return switch (payment.getPaymentType()) {
            case ERIP -> toERIPResponse((PaymentByERIP) payment);
            case REQUISITE -> toRequisiteResponse((PaymentByRequisite) payment);
        };
    }

    default Payment fromRequest(PaymentRequest request) {
        return switch (request.getPaymentType()) {
            case ERIP -> {
                PaymentByERIPRequest req = (PaymentByERIPRequest) request;
                yield Optional.ofNullable(req.getFieldForInsertion())
                        .flatMap(field -> Optional.ofNullable(req.getValueForInsertion()))
                        .map(value -> toERIPEntity(req))
                        .orElseThrow(() -> new WrongPaymentTypeException(
                                "Payment with type ERIP must contains fields: field_for_insertion and value_for_insertion"));
            }
            case REQUISITE -> {
                PaymentByRequisiteRequest req = (PaymentByRequisiteRequest) request;
                yield Optional.ofNullable(req.getReceiver())
                        .flatMap(receiver -> Optional.ofNullable(req.getUnp()))
                        .flatMap(unp -> Optional.ofNullable(req.getDestination()))
                        .map(destination -> toRequisiteEntity(req))
                        .orElseThrow(() -> new WrongPaymentTypeException(
                                "Payment with type REQUISITE must contains fields: unp, receiver and destination"));
            }
        };
    }

}
