package ru.clevertec.inheritancepractice.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.inheritancepractice.model.PaymentType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "payment_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaymentByERIPRequest.class, name = "ERIP"),
        @JsonSubTypes.Type(value = PaymentByRequisiteRequest.class, name = "REQUISITE")
})
public class PaymentRequest {

    private String paymentFrom;
    private String paymentTo;
    private PaymentType paymentType;

}
