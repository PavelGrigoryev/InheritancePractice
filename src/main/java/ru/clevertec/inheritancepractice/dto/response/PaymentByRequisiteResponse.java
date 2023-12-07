package ru.clevertec.inheritancepractice.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class PaymentByRequisiteResponse extends PaymentResponse {

    private String unp;
    private String receiver;
    private String destination;

}
