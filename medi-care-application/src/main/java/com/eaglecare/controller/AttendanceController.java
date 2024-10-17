package com.eaglecare.controller;

import com.eaglecare.api.AttendanceApi;
import com.eaglecare.exception.CustomException;
import com.eaglecare.exception.ResourceNotFoundException;
import com.eaglecare.model.*;
import com.eaglecare.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
public class AttendanceController implements AttendanceApi {

    @Autowired
    LeaveApplicationService leaveApplicationService;

    @Override
    public ResponseEntity<Void> applyLeave(LeaveApplication leaveApplication) {
        try {
            leaveApplicationService.applyLeave(leaveApplication);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> approveLeave(Integer leaveApplicationId, LeaveApproval leaveApproval) {
        try {
            leaveApproval.setLeaveApplicationId(Long.valueOf(leaveApplicationId));
            leaveApplicationService.approveLeave(leaveApproval);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CheckInCheckOut> checkIn(Integer userId) {
        try {
            CheckInCheckOut check = leaveApplicationService.checkInService(Long.valueOf(userId));
            return new ResponseEntity<>(check, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CheckInCheckOut> checkOut(Integer userId) {
        try {
            CheckInCheckOut check = leaveApplicationService.checkOutService(Long.valueOf(userId));
            return new ResponseEntity<>(check, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> createHolidayDetails(HolidayConfiguration holidayConfiguration) {
        try {
            HolidayConfiguration holidayDetails = leaveApplicationService.createHolidayDetails(holidayConfiguration);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CheckInCheckOut> getCheckInAndOutDetails(Integer userId, Date date) {
        try {
            CheckInCheckOut checkInAndOutDetails = leaveApplicationService.getCheckInAndOutDetails(Long.valueOf(userId), date);
            return new ResponseEntity<>(checkInAndOutDetails, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<HolidayConfiguration>> getHolidayDetails() {
        try {
            List<HolidayConfiguration> holidayDetails = leaveApplicationService.getHolidayDetails();
            return new ResponseEntity<>(holidayDetails, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<LeaveApplication>> getLeaveApplicaitons() {
        try {
            List<LeaveApplication> leave = leaveApplicationService.getLeaveApplications();
            return new ResponseEntity<>(leave, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<LeaveApplication> getLeaveDetailsById(Integer id) {
        try {
            LeaveApplication leave = leaveApplicationService.getLeaveDetailsById(Long.valueOf(id));
            return new ResponseEntity<>(leave, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> createLeaveAllcoationForUsers(LeaveAllocation leaveAllocation) {
        try {
            LeaveAllocation leaveAllocation1 = leaveApplicationService.createLeaveAllocationForUsers(leaveAllocation);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<LeaveAllocation> getLeaveAllicationByUser(Integer id) {
        try {
            LeaveAllocation userLeaveAllocation = leaveApplicationService.getLeaveAllicationByUser(Long.valueOf(id));
            return new ResponseEntity<>(userLeaveAllocation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            System.err.println("Errors : " + e);
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
