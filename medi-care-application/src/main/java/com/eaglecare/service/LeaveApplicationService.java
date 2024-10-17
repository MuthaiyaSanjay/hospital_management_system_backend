package com.eaglecare.service;

import com.eaglecare.entity.*;
import com.eaglecare.exception.ResourceNotFoundException;
import com.eaglecare.model.*;
import com.eaglecare.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveApplicationService {

    @Autowired
    LeaveApplicationEntityRepo leaveApplicationEntityRepo;

    @Autowired
    LeaveApprovalRepo leaveApprovalRepo;

    @Autowired
    UserBasicInfoRepo userRepo;

    @Autowired
    CheckInCheckOutRepo checkInCheckOutRepo;

    @Autowired
    HolidayEntityRepo holidayEntityRepo;

    @Autowired
    LeaveAllocationEntityRepo leaveAllocationEntityRepo;

    @Autowired
    ModelMapper modelMapper;

    public void applyLeave(LeaveApplication leaveApplication1) {
        LeaveApplicationEntity leaveApplication = modelMapper.map(leaveApplication1, LeaveApplicationEntity.class);
        if (leaveApplication == null || leaveApplication.getUserId() == null) {
            throw new IllegalArgumentException("Leave application or user ID cannot be null.");
        }
        Optional<UserBasicInfoEntity> user = userRepo.findById(leaveApplication.getUserId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User ID Not Found in Database.");
        }
        leaveApplication.setStatus(LeaveApplicationEntity.StatusEnum.PENDING);
        leaveApplicationEntityRepo.save(leaveApplication);
    }

    public LeaveApplication getLeaveDetailsById(long id) {
        LeaveApplicationEntity leaveApplication = leaveApplicationEntityRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave application not found"));
        return modelMapper.map(leaveApplication, LeaveApplication.class);
    }

    public List<LeaveApplication> getLeaveApplications() {
        List<LeaveApplicationEntity> leavePage = leaveApplicationEntityRepo.findAll();

        return leavePage.stream()
                .map(this::convertToLeaveApplicationDTO)
                .collect(Collectors.toList());
    }

    private LeaveApplication convertToLeaveApplicationDTO(LeaveApplicationEntity leaveApplicationEntity) {
        LeaveApplication dto = modelMapper.map(leaveApplicationEntity, LeaveApplication.class);
        if (dto == null) {
            throw new IllegalStateException("LeaveApplicationDTO is null for leave application with id: " + leaveApplicationEntity.getId());
        }
        return dto;
    }

    public void approveLeave(LeaveApproval leaveApproval1) {
        LeaveApprovalEntity leaveApproval = modelMapper.map(leaveApproval1, LeaveApprovalEntity.class);
        if (leaveApproval == null || leaveApproval.getLeaveApplicationId() == null) {
            throw new IllegalArgumentException("Leave Application Id or cannot be null.");
        }
        Optional<LeaveApplicationEntity> leave = leaveApplicationEntityRepo.findById(leaveApproval1.getLeaveApplicationId());
        if (leave.isEmpty()) {
            throw new ResourceNotFoundException("Leave Application Id not Found");
        }
        leaveApproval.setStatus(LeaveApprovalEntity.StatusEnum.valueOf(leaveApproval.getStatus().toString()));
        System.out.println(leaveApproval);
        Optional<LeaveApplicationEntity> leaveValue = leaveApplicationEntityRepo.findById(leaveApproval.getLeaveApplicationId());
        if (leaveValue.isPresent()) {
            LeaveApplicationEntity leaveApplicationEntity = leaveValue.get();
            leaveApplicationEntity.setStatus(LeaveApplicationEntity.StatusEnum.valueOf(leaveApproval.getStatus().toString()));
            leaveApplicationEntityRepo.save(leaveApplicationEntity);
        }
        leaveApprovalRepo.save(leaveApproval);
    }

    public CheckInCheckOut checkOutService(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        Optional<UserBasicInfoEntity> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User ID Not Found in Database.");
        }

        Optional<CheckInCheckOutEntity> checkInRecord = checkInCheckOutRepo
                .findLatestCheckInByUserIdAndDate(userId, LocalDate.now());

        if (checkInRecord.isEmpty() || checkInRecord.get().getCheckOutTime() != null) {
            throw new IllegalStateException("User is either not checked in or has already checked out.");
        }

        CheckInCheckOutEntity checkIn = checkInRecord.get();
        checkIn.setCheckOutTime(LocalTime.now().toString());
        checkInCheckOutRepo.save(checkIn);

        return modelMapper.map(checkIn, CheckInCheckOut.class);
    }

    @Transactional
    public CheckInCheckOut checkInService(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        Optional<UserBasicInfoEntity> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User ID Not Found in Database.");
        }
        CheckInCheckOutEntity checkIn = new CheckInCheckOutEntity();
        checkIn.setUserId(userId);
        checkIn.setDate(LocalDate.now());
        checkIn.setCheckInTime(LocalTime.now().toString());
        checkInCheckOutRepo.save(checkIn);
        System.out.println("leave values " + checkIn);
        return modelMapper.map(checkIn, CheckInCheckOut.class);
    }

    public HolidayConfiguration createHolidayDetails(HolidayConfiguration holidayConfiguration) {
        if (holidayConfiguration == null || holidayConfiguration.getDate() == null) {
            throw new IllegalArgumentException("holidayConfiguration or Date cannot be null.");
        }
        HolidayEntity holidayEntity = modelMapper.map(holidayConfiguration, HolidayEntity.class);
        holidayEntityRepo.save(holidayEntity);
        return modelMapper.map(holidayEntity, HolidayConfiguration.class);
    }

    public LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public CheckInCheckOut getCheckInAndOutDetails(Long userId, Date date) {
        LocalDate dateValue = convertDateToLocalDate(date);
        Optional<CheckInCheckOutEntity> checkInCheckOuts = checkInCheckOutRepo.findByUserIdAndDate(userId, dateValue);

        if (checkInCheckOuts.isEmpty()) {
            throw new ResourceNotFoundException("User ID Not Found in Database.");
        }
        CheckInCheckOutEntity existingCheck = checkInCheckOuts.get();
        CheckInCheckOut check = new CheckInCheckOut();
        check.setCheckInTime(existingCheck.getCheckInTime());
        check.setCheckOutTime(existingCheck.getCheckOutTime());
        check.setUserId(existingCheck.getUserId());
        check.setDate(existingCheck.getDate().toString());
        check.setId(existingCheck.getId());
        return check;
    }

    public List<HolidayConfiguration> getHolidayDetails() {
        List<HolidayEntity> holidayEntities = holidayEntityRepo.findAll();

        return holidayEntities.stream()
                .map(entity -> modelMapper.map(entity, HolidayConfiguration.class))
                .collect(Collectors.toList());
    }

    public LeaveAllocation createLeaveAllocationForUsers(LeaveAllocation leaveAllocation) {
        if (leaveAllocation.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        Optional<UserBasicInfoEntity> user = userRepo.findById(leaveAllocation.getUserId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User ID Not Found in Database.");
        }
        Optional<LeaveAllocationEntity> leaveAllocationEntity = leaveAllocationEntityRepo.findByUserId(leaveAllocation.getUserId());
        if (leaveAllocationEntity.isPresent()) {
            LeaveAllocationEntity leaveAllocationValues = leaveAllocationEntity.get();
            leaveAllocationValues.setAllocationType(LeaveAllocationEntity.AllocationTypeEnum
                    .valueOf(leaveAllocation.getAllocationType().toString()));
            leaveAllocationValues.setAllocatedDays(leaveAllocation.getAllocatedDays());
            leaveAllocationEntityRepo.save(leaveAllocationValues);
            System.out.println("LeaveAllocationEntity " + leaveAllocationValues);
            return modelMapper.map(leaveAllocationValues, LeaveAllocation.class);
        } else {
            LeaveAllocationEntity leaveAllocation1 = modelMapper.map(leaveAllocation, LeaveAllocationEntity.class);
            leaveAllocation1.setAllocationType(LeaveAllocationEntity.AllocationTypeEnum.valueOf(leaveAllocation.getAllocationType().toString()));
            leaveAllocationEntityRepo.save(leaveAllocation1);
            System.out.println("LeaveAllocationEntity " + leaveAllocation1);
            return modelMapper.map(leaveAllocation1, LeaveAllocation.class);
        }
    }

    public LeaveAllocation getLeaveAllicationByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        Optional<LeaveAllocationEntity> leaveAllocationEntity = leaveAllocationEntityRepo.findByUserId(userId);
        if (leaveAllocationEntity.isEmpty()) {
            throw new ResourceNotFoundException("leaveAllocationEntity Not Found in Database.");
        }
        LeaveAllocationEntity leaveAllocation = leaveAllocationEntity.get();
        LeaveAllocation leaves = modelMapper.map(leaveAllocation, LeaveAllocation.class);
        com.eaglecare.entity.LeaveAllocationEntity.AllocationTypeEnum entityType = leaveAllocation.getAllocationType();
        LeaveAllocation.AllocationTypeEnum modelType = switch (entityType) {
            case EACH_MONTH -> LeaveAllocation.AllocationTypeEnum.MONTH;
            case EACH_YEAR -> LeaveAllocation.AllocationTypeEnum.YEAR;
            default -> throw new IllegalArgumentException("Unknown allocation type: " + entityType);
        };
        leaves.setAllocationType(modelType);
        System.out.println("Values of " + leaves);
        return leaves;
    }
}
