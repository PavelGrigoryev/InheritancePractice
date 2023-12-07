package ru.clevertec.inheritancepractice.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.inheritancepractice.dto.request.PaymentByERIPRequest;
import ru.clevertec.inheritancepractice.dto.request.PaymentByRequisiteRequest;
import ru.clevertec.inheritancepractice.dto.response.PaymentByERIPResponse;
import ru.clevertec.inheritancepractice.dto.response.PaymentByRequisiteResponse;
import ru.clevertec.inheritancepractice.model.PaymentByERIP;
import ru.clevertec.inheritancepractice.model.PaymentByRequisite;

@Mapper
public interface PaymentMapper {

    PaymentByERIPResponse toERIPResponse(PaymentByERIP paymentByERIP);

    PaymentByRequisiteResponse toRequisiteResponse(PaymentByRequisite paymentByRequisite);

    PaymentByERIP toERIPEntity(PaymentByERIPRequest request);

    PaymentByRequisite toRequisiteEntity(PaymentByRequisiteRequest request);

}
