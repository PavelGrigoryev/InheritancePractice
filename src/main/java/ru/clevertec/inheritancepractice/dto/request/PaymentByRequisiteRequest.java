package ru.clevertec.inheritancepractice.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class PaymentByRequisiteRequest extends PaymentRequest {

    private String unp;
    private String receiver;
    private String destination;

}
