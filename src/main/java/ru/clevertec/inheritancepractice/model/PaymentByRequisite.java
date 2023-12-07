package ru.clevertec.inheritancepractice.model;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class PaymentByRequisite extends Payment {

    private String unp;
    private String receiver;
    private String destination;

}
