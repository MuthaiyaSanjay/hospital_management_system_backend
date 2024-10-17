package com.eaglecare.controller;

import com.eaglecare.api.PayRollApi;
import com.eaglecare.exception.CustomException;
import com.eaglecare.model.PayRoll;
import com.eaglecare.model.PayRollGenerationHistory;
import com.eaglecare.model.PayRollSummary;
import com.eaglecare.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PayrollController implements PayRollApi {


    @Autowired
    PayrollService payrollService;

    @Override
    public ResponseEntity<PayRollGenerationHistory> createPayRollGenerationHistory(PayRollGenerationHistory payRollGenerationHistory) {
        try {
            PayRollGenerationHistory payRoll = payrollService.createPayRollGenHistory(payRollGenerationHistory);
            return new ResponseEntity<>(payRoll,HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<PayRoll> createPayrollForEmployee(PayRoll payRoll) {
        try {
            PayRoll payRoll1 = payrollService.createPayrollForEmployee(payRoll);
            return new ResponseEntity<>(payRoll1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deletePayRollById(String id) {
        try {
            boolean response = payrollService.deletePayRollById(Long.parseLong(id));
            String response1= (response) ? "successfully deleted" : "some issues";
            return new ResponseEntity<>(response1,HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<PayRollGenerationHistory> generatePayRollForEmployees(String month, String year) {
        try {

            PayRollGenerationHistory pay = payrollService.generatePayRollForEmployees(month, year);
            return new ResponseEntity<>(pay, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<PayRoll> getPayRollByEmployeeId(String id) {
        try {
            PayRoll payRoll = payrollService.findByUserId(Long.parseLong(id));
            return new ResponseEntity<>(payRoll, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<PayRoll> getPayRollById(String id) {
        try {
            PayRoll payRoll = payrollService.findById(Long.parseLong(id));
            return new ResponseEntity<>(payRoll, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<PayRollSummary> getPayRollLeaveSummaryDetails(String id, BigDecimal month, BigDecimal year, String from, String to) {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<PayRollSummary> getPayRollSummaryForDash(String id) {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<PayRoll> updatePayRoll(String id, PayRoll payRoll) {
        try {
            PayRoll payRoll1 = payrollService.updatePayroll(Long.parseLong(id), payRoll);
            return new ResponseEntity<>(payRoll1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<List<PayRoll>> getAllPayrols(int page, int size) {
        try {
            List<PayRoll> payRoll1 = payrollService.findAllPayRolls(page, size);
            return new ResponseEntity<>(payRoll1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
