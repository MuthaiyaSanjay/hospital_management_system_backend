package com.eaglecare.controller;

import com.eaglecare.api.BillingHistoryApi;
import com.eaglecare.model.BillingHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillingHistoryController implements BillingHistoryApi {

    @Override
    public ResponseEntity<List<BillingHistory>> getBillingHistoryByInvoiceId(String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
