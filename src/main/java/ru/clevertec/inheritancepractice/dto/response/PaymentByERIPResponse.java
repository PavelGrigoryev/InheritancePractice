package ru.clevertec.inheritancepractice.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class PaymentByERIPResponse extends PaymentResponse {

    private String fieldForInsertion;
    private String valueForInsertion;

}
