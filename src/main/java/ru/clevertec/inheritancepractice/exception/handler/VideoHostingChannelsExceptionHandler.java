package ru.clevertec.inheritancepractice.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.clevertec.inheritancepractice.exception.NotFoundException;
import ru.clevertec.inheritancepractice.exception.ServiceException;
import ru.clevertec.inheritancepractice.exception.WrongPaymentTypeException;
import ru.clevertec.inheritancepractice.exception.model.ExceptionResponse;

@Slf4j
@ControllerAdvice
public class VideoHostingChannelsExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionResponse> handleServiceException(ServiceException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPaymentTypeException.class)
    public ResponseEntity<ExceptionResponse> handleWrongPaymentTypeException(WrongPaymentTypeException exception) {
        return sendResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponse> sendResponse(String message, HttpStatus httpStatus) {
        ExceptionResponse response = new ExceptionResponse(message);
        log.error(response.toString());
        return ResponseEntity.status(httpStatus).body(response);
    }

}
