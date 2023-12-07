package ru.clevertec.inheritancepractice.dto.response;

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
public class PaymentResponse {

    private Long id;
    private String paymentFrom;
    private String paymentTo;
    private PaymentType paymentType;

}
