package com.eaglecare.controller;

import com.eaglecare.api.DashboardApi;
import com.eaglecare.model.Summary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashBoardController implements DashboardApi {

    @Override
    public ResponseEntity<Summary> getSummaryForLoginedUser(String userId) {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
