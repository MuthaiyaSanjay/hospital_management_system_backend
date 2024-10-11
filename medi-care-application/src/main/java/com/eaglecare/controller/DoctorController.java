package com.eaglecare.controller;

import com.eaglecare.api.DoctorApi;
import com.eaglecare.exception.CustomException;
import com.eaglecare.model.Doctor;
import com.eaglecare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class DoctorController implements DoctorApi {

    @Autowired
    DoctorService doctorService;

    @Override
    public ResponseEntity<Doctor> createNewDoctor(Doctor doctor) {
        try {
            System.out.println("--doctor values -----" + doctor);
            Doctor res = doctorService.saveDoctor(doctor);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            System.err.println("Error creating doctor: " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteDoctorsById(String id) {
        try {
            long doctorId = Long.parseLong(id);
            doctorService.deleteDoctorId(doctorId);
            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid ID format");
        } catch (Exception e) {
            // Log other exceptions
            System.err.println("Error deleting doctor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting doctor");
        }
    }

    @Override
    public ResponseEntity<List<Doctor>> getDoctors(BigDecimal page, BigDecimal count, BigDecimal doctorId) {
        try {
            BigDecimal bg1 = new BigDecimal(String.valueOf(page));
            BigDecimal bg2 = new BigDecimal(String.valueOf(count));
            int page1 = bg1.intValue();
            int count1 = bg2.intValue();
            List<Doctor> doctors = doctorService.getAllDoctors(page1, count1);
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving doctors: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Doctor> getDoctorsById(String id) {
        try {
            long doctorId = Long.parseLong(id);
            Doctor res = doctorService.getDoctorsById(doctorId);
            return ResponseEntity.ok(res);
        } catch (NumberFormatException e) {
            // Log the exception for invalid ID format
            System.err.println("Invalid ID format: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            System.err.println("Error retrieving doctor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Doctor> updateDoctor(String id, Doctor doctor) {
        try {
            System.out.println("--doctor values-----" + doctor);
            Doctor res = doctorService.updateDoctor(id, doctor);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            System.err.println("Error updating doctor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
