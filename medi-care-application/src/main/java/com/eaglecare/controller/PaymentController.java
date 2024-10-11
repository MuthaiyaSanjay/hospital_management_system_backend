package com.eaglecare.controller;

import com.eaglecare.api.PaymentsApi;
import com.eaglecare.exception.ResourceNotFoundException;
import com.eaglecare.model.Payments;
import com.eaglecare.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PaymentController implements PaymentsApi {

    @Autowired
    PaymentService paymentService;

    @Override
    public ResponseEntity<Payments> createPatchPayments(List<Payments> payments) {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Payments> createPayments(Payments payments) {
        try {
            Payments payments1=paymentService.createPayment(payments);
            return new ResponseEntity<>(payments1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<String> deletePaymentsById(String id) {
        try {
            paymentService.deletePayment(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<Payments>> getPayments(BigDecimal page, BigDecimal count) {
        try {
            BigDecimal bg1 = new BigDecimal(String.valueOf(page));
            BigDecimal bg2 = new BigDecimal(String.valueOf(count));
            int page1 = bg1.intValue();
            int count1 = bg2.intValue();
            List<Payments> payments = paymentService.getAllPayments(page1, count1);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Payments> getPaymentsByInvoice(BigDecimal invoiceId) {
        try {
            Payments payments=paymentService.getPaymentsByInvoices(Long.valueOf(String.valueOf(invoiceId)));
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Payments> updatePayments(String id, Payments payments) {
        try {
            Payments payments1 = paymentService.updatePayment(Long.parseLong(id),payments);
            return new ResponseEntity<>(payments1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Payments> getPaymentsById(String id) {
        try {
            Payments payments = paymentService.getPaymentById(Long.parseLong(id));
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
