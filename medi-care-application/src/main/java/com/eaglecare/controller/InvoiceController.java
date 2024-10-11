package com.eaglecare.controller;

import com.eaglecare.api.InvoicesApi;
import com.eaglecare.model.Invoices;
import com.eaglecare.model.PayRoll;
import com.eaglecare.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class InvoiceController implements InvoicesApi {
    @Autowired
    InvoiceService invoiceService;

    @Override
    public ResponseEntity<Invoices> createInvoices(Invoices invoices) {
        try {
            Invoices invoices1=invoiceService.saveInvoice(invoices);
            return new ResponseEntity<>(invoices1,HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<String> deleteInvoicesById(String id) {
        try {
            boolean response = invoiceService.deleteInvoice(Long.parseLong(id));
            String response1= (response) ? "successfully deleted" : "some issues";
            return new ResponseEntity<>(response1,HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<Invoices>> getInvoices(BigDecimal page, BigDecimal count, Date month, Integer year) {
        try {
            int pageNumber = page.intValue();
            int pageSize = count.intValue();
            List<Invoices> invoices = invoiceService.getAllInvoices(pageNumber,pageSize);
            return new ResponseEntity<>(invoices,HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<PayRoll> getInvoicesById(String id) {
        try {
            PayRoll PayRoll=invoiceService.getInvoiceById(Long.parseLong(id));
            return new ResponseEntity<>(PayRoll, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Invoices> updateInvoices(String id, Invoices invoices) {
        try {
            Invoices invoices1= invoiceService.updateInvoice(Long.parseLong(id),invoices);
            return new ResponseEntity<>(invoices1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
