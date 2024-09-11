package com.medicare.controller;

import com.medicare.api.AppointmentApi;
import com.medicare.model.Appointment;
import com.medicare.model.Doctor;
import com.medicare.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class AppointmentController implements AppointmentApi {

    @Autowired
    private AppointmentService appointmentService;

    @Override
    public ResponseEntity<Appointment> bookAppointment(Appointment appointment) {
        Appointment appointmentDetails = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(appointmentDetails,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAppointmentById(String id) {
        appointmentService.deleteById(Long.parseLong(id));
        return new ResponseEntity<>(id + " Appointment Deleted Successfully ", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> getAppoinmentById(String id) {
        Appointment appointmentDetails = appointmentService.findById(Long.parseLong(id));
        return new ResponseEntity<>(appointmentDetails, HttpStatus.OK);
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
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error retrieving doctors: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Appointment> updateAppointment(String id, Appointment appointment) {
        appointment.setId(id);
        Appointment appointmentDetails = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(appointmentDetails,HttpStatus.OK);
    }
}
