package com.eaglecare.controller;

import com.eaglecare.api.PatientsApi;
import com.eaglecare.model.Patients;
import com.eaglecare.service.PatientService;
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
            patientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error Occurring Delete Patients By Id : " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<Patients>> getPatients(BigDecimal page, BigDecimal count, BigDecimal patientsId, String phoneNumber) {
        try {
            BigDecimal bg1 = new BigDecimal(String.valueOf(page));
            BigDecimal bg2 = new BigDecimal(String.valueOf(count));
            int page1 = bg1.intValue();
            int count1 = bg2.intValue();
            List<Patients> patients = patientService.getAllPatients(page1, count1);
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error Occurring GetAllPatients : " + e.getMessage());
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
            Patients patients1 = patientService.update(id, patients);
            return new ResponseEntity<>(patients1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
