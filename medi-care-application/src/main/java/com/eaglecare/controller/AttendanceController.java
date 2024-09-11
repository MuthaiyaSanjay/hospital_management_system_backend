package com.medicare.controller;

import com.medicare.api.AttendanceApi;
import com.medicare.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class AttendanceController implements AttendanceApi {
    @Override
    public ResponseEntity<Void> applyLeave(LeaveApplication leaveApplication) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> approveLeave(Integer leaveApplicationId, LeaveApproval leaveApproval) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<CheckInCheckOut> checkIn(Integer userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<CheckInCheckOut> checkOut(Integer userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> createHolidayDetails(HolidayConfiguration holidayConfiguration) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<CheckInCheckOut> getCheckInAndOutDetails(Integer userId, Date date) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<HolidayConfiguration>> getHolidayDetails() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<LeaveAllocation> getLeaveAllicationByUser(Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<LeaveApplication>> getLeaveApplicaitons() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> createLeaveAllcoationForUsers(LeaveAllocation leaveAllocation) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<LeaveApplication> getLeaveDetailsById(Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
