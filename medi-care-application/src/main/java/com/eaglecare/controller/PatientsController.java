package com.medicare.controller;

import com.medicare.api.PatientsApi;
import com.medicare.model.Patients;
import com.medicare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PatientsController implements PatientsApi {


    @Autowired
    PatientService patientService;


    @Override
    public ResponseEntity<Patients> createNewPatients(Patients patients) {
        try {
            Patients patients1 = patientService.save(patients);
            return new ResponseEntity<>(patients1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<String> deletePatientsById(String id) {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<Patients>> getPatients(BigDecimal page, BigDecimal count, BigDecimal patientsId, String phoneNumber) {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Patients> getPatientsById(String id) {
        try {
            Patients patients1 = patientService.getByPatientId(id);
            return new ResponseEntity<>(patients1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Patients> updatePatients(String id, Patients patients) {
        try {
            Patients patients1 = patientService.update(id ,patients);
            return new ResponseEntity<>(patients1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
