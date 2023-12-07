package ru.clevertec.inheritancepractice.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class PaymentByERIPRequest extends PaymentRequest {

    private String fieldForInsertion;
    private String valueForInsertion;

}
