package ru.clevertec.inheritancepractice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "payment_by_erip")
public class PaymentByERIP extends Payment {

    private String fieldForInsertion;
    private String valueForInsertion;

}
