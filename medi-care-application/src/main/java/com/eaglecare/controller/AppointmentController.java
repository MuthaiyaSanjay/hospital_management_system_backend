package com.eaglecare.controller;

import com.eaglecare.api.AppointmentApi;
import com.eaglecare.exception.CustomException;
import com.eaglecare.model.Appointment;
import com.eaglecare.service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class AppointmentController implements AppointmentApi {

    @Autowired
    AppointmentService appointmentService;

    @Override
    public ResponseEntity<Appointment> bookAppointment(Appointment appointment) {
        try {
            Appointment appointmentDetails = appointmentService.createOrUpdateAppointment(appointment);
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("ID Not Found Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            System.err.println("Error retrieving Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteAppointmentById(String id) {
        try {
            appointmentService.deleteById(Long.parseLong(id));
            return new ResponseEntity<>(id + " Appointment Deleted Successfully ", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("ID Not Found Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            System.err.println("Error retrieving Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Appointment> getAppoinmentById(String id) {
        try {
            Appointment appointmentDetails = appointmentService.findById(Long.parseLong(id));
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("ID Not Found Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            System.err.println("Error retrieving Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<Appointment>> getAppointments(BigDecimal page, BigDecimal count, Date appointmentDate, String patientId, String doctorId, String phoneNumber) {
        try {
            BigDecimal bg1 = new BigDecimal(String.valueOf(page));
            BigDecimal bg2 = new BigDecimal(String.valueOf(count));
            int page1 = bg1.intValue();
            int count1 = bg2.intValue();
            List<Appointment> appointments = appointmentService.getAllAppointments(page1, count1);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("ID Not Found Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            System.err.println("Error retrieving Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Appointment> updateAppointment(String id, Appointment appointment) {
        try {
            appointment.setId(id);
            Appointment appointmentDetails = appointmentService.createOrUpdateAppointment(appointment);
            return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("ID Not Found Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            System.err.println("Error retrieving Book Appointment: " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
