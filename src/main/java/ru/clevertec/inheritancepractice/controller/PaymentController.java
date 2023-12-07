package ru.clevertec.inheritancepractice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.inheritancepractice.dto.request.PaymentRequest;
import ru.clevertec.inheritancepractice.dto.response.DeleteResponse;
import ru.clevertec.inheritancepractice.dto.response.PaymentResponse;
import ru.clevertec.inheritancepractice.service.PaymentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(paymentService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> save(@RequestBody PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updateById(@PathVariable Long id, @RequestBody PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.deleteById(id));
    }

}
