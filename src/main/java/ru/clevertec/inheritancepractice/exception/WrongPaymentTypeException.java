package ru.clevertec.inheritancepractice.exception;

public class WrongPaymentTypeException extends RuntimeException {

    public WrongPaymentTypeException(String message) {
        super(message);
    }

}
