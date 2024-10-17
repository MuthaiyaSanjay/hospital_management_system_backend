package com.eaglecare.service;

import com.eaglecare.entity.*;
import com.eaglecare.exception.CustomException;
import com.eaglecare.model.DeductionSummary;
import com.eaglecare.model.PayRoll;
import com.eaglecare.model.PayRollGenerationHistory;
import com.eaglecare.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PayrollService {

    @Autowired
    UserBasicInfoRepo userBasicInfoRepo;

    @Autowired
    EmploymentDetailsRepo employmentDetailsRepo;

    @Autowired
    PayRollRepo payRollRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PayRollGeneraionHistoryRepo payRollGeneraionHistoryRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    PayrollCalculator payrollCalculator;

    public PayRoll createPayrollForEmployee(PayRoll payRoll) {
        Optional<UserBasicInfoEntity> user = userBasicInfoRepo.
                findById(Long.parseLong(payRoll.getUser()));
        if (user.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find user id is " + payRoll.getUser());
        }
        if (user.get().getEmploymentDetails() == null) {
            System.out.println("getEmploymentDetails is Empty");
            throw new ResponseStatusException(NOT_FOUND, "getEmploymentDetails is Empty  " + payRoll.getUser());
        }
        Optional<EmploymentDetailsEntity> employmentDetails = employmentDetailsRepo.findById(user.get().getEmploymentDetails().getId());
        if (employmentDetails.get().getPayroll() == null) {
            EmploymentDetailsEntity emp = new EmploymentDetailsEntity();
            PayRollEntity payRolls = modelMapper.map(payRoll, PayRollEntity.class);
            emp.setId(employmentDetails.get().getId());
            emp.setDepartment(employmentDetails.get().getDepartment());
            emp.setRelievingDate(employmentDetails.get().getRelievingDate());
            emp.setJoiningDate(employmentDetails.get().getJoiningDate());
            emp.setSalary(employmentDetails.get().getSalary());
            emp.setPayroll(payRolls);
            payRollRepo.save(payRolls);
            EmploymentDetailsEntity empobj = employmentDetailsRepo.save(emp);
            PayRollEntity payRoll1 = empobj.getPayroll();
            return modelMapper.map(payRoll1, PayRoll.class);
        } else {
            Optional<PayRollEntity> pay = payRollRepo.findById(employmentDetails.get().getPayroll().getId());
            PayRollEntity payRolls = modelMapper.map(payRoll, PayRollEntity.class);
            payRolls.setUser(Long.parseLong(payRoll.getUser()));
            payRolls.setId(pay.get().getId());
            PayRollEntity payobj = payRollRepo.save(payRolls);
            return modelMapper.map(payobj, PayRoll.class);
        }
    }

    public PayRoll updatePayroll(Long id, PayRoll payRoll) {
        Optional<PayRollEntity> existingPayRoll = payRollRepo.findById(id);
        if (existingPayRoll.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "PayRoll not found with ID: " + id);
        }
        PayRollEntity payRollEntity = modelMapper.map(payRoll, PayRollEntity.class);
        payRollEntity.setId(id);
        PayRollEntity updatedPayRollEntity = payRollRepo.save(payRollEntity);
        return modelMapper.map(updatedPayRollEntity, PayRoll.class);
    }

    public void deletePayroll(Long id) {
        Optional<PayRollEntity> existingPayRoll = payRollRepo.findById(id);
        if (existingPayRoll.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "PayRoll not found with ID: " + id);
        }
        payRollRepo.deleteById(id);
    }

    public PayRoll findById(long id) {
        Optional<PayRollEntity> optionalPayRoll = payRollRepo.findById(id);
        if (optionalPayRoll.isPresent()) {
            return modelMapper.map(optionalPayRoll.get(), PayRoll.class);
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "PayRoll not found with ID: " + id);
        }
    }

    public List<PayRoll> findAllPayRolls(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PayRollEntity> payRollPage = payRollRepo.findAll(pageable);
        return payRollPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, PayRoll.class))
                .collect(Collectors.toList());
    }

    public PayRoll findByUserId(long id) {
        List<PayRollEntity> payRolls = payRollRepo.findMostRecentByUser(id, PageRequest.of(0, 1));
        if (!payRolls.isEmpty()) {
            return modelMapper.map(payRolls.get(0), PayRoll.class);
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "PayRoll not found for user ID: " + id);
        }
    }

    // Create
    public PayRollGenerationHistory createPayRollGenHistory(PayRollGenerationHistory payRollGenerationHistoryDTO) {
        Optional<UserBasicInfoEntity> user = userBasicInfoRepo.
                findById(Long.parseLong(payRollGenerationHistoryDTO.getEmpId()));
        if (user.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find user id is " + payRollGenerationHistoryDTO.getEmpId());
        }
        PayRollGenerationHistoryEntity payRollGenerationHistory =
                modelMapper.map(payRollGenerationHistoryDTO, PayRollGenerationHistoryEntity.class);
        for (DeductionSummaryEntity deduction : payRollGenerationHistory.getDeductionSummary()) {
            deduction.setPayRollGenerationHistoryEntity(payRollGenerationHistory);
        }
        PayRollGenerationHistoryEntity savedEntity = payRollGeneraionHistoryRepo.save(payRollGenerationHistory);
        return modelMapper.map(savedEntity, PayRollGenerationHistory.class);
    }

    // Read All
    public List<PayRollGenerationHistory> getAll() {
        List<PayRollGenerationHistoryEntity> entities = payRollGeneraionHistoryRepo.findAll();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, PayRollGenerationHistory.class))
                .toList();
    }

    // Read by ID
    public PayRollGenerationHistory getById(Long id) {
        Optional<PayRollGenerationHistoryEntity> optionalEntity = payRollGeneraionHistoryRepo.findById(String.valueOf(id));
        return optionalEntity.map(entity -> modelMapper.map(entity, PayRollGenerationHistory.class)).orElse(null);
    }

    // Update
    public PayRollGenerationHistory updatePayRollGenHistory(Long id, PayRollGenerationHistory payRollGenerationHistoryDTO) {
        Optional<PayRollGenerationHistoryEntity> optionalEntity = payRollGeneraionHistoryRepo.findById(String.valueOf(id));
        if (optionalEntity.isPresent()) {
            PayRollGenerationHistoryEntity existingEntity = optionalEntity.get();
            // Map new values from DTO to existing entity
            modelMapper.map(payRollGenerationHistoryDTO, existingEntity);
            PayRollGenerationHistoryEntity updatedEntity = payRollGeneraionHistoryRepo.save(existingEntity);
            return modelMapper.map(updatedEntity, PayRollGenerationHistory.class);
        }
        return null; // or throw an exception
    }

    // Delete
    public void deletePayRollGenHistory(Long id) {
        payRollGeneraionHistoryRepo.deleteById(String.valueOf(id));
    }


    public PayRollGenerationHistory toDTO(PayRollGenerationHistoryEntity entity) {
        PayRollGenerationHistory dto = new PayRollGenerationHistory();
        dto.setEmpId(String.valueOf(entity.getEmpId()));
        dto.setEmpName(entity.getEmpName());
        dto.setLeave(entity.getLeave());
        dto.setCrossSalary(entity.getCrossSalary());
        dto.setDeductions(entity.getDeductions());
        dto.setNetSalary(entity.getNetSalary());
        String statusEnum = String.valueOf(fromString(entity.getStatus().getValue()));
        dto.setStatus(PayRollGenerationHistory.StatusEnum.valueOf(statusEnum));
        return dto;
    }

    public PayRollGenerationHistoryEntity toEntity(PayRollGenerationHistory dto) {
        PayRollGenerationHistoryEntity entity = new PayRollGenerationHistoryEntity();
        entity.setEmpId(dto.getEmpId());
        entity.setEmpName(dto.getEmpName());
        entity.setLeave(dto.getLeave());
        entity.setCrossSalary(dto.getCrossSalary());
        entity.setDeductions(dto.getDeductions());
        entity.setNetSalary(dto.getNetSalary());
//        entity.setStatus(dto.getStatus());
        return entity;
    }

    public PayRollGenerationHistory generatePayRollForEmployees(String month, String year) {
        List<PayRollGenerationHistoryEntity> payrollHistory = new ArrayList<>();
        List<UserBasicInfoEntity> employees = userBasicInfoRepo.findAll();

        for (UserBasicInfoEntity employee : employees) {
            if (employee.getEmploymentDetails() == null) {
                System.err.println("No employment details for user: " + employee.getUserId());
                continue; // Skip to the next employee
            }

            PayRollGenerationHistoryEntity history = new PayRollGenerationHistoryEntity();
            history.setEmpId(employee.getUserId().toString());

            Double grossSalary = payrollCalculator.calculateGrossSalary(employee);
            history.setCrossSalary(grossSalary);

            BigDecimal deductions = payrollCalculator.calculateDeductions(employee);
            history.setDeductions(Double.parseDouble(String.valueOf(deductions)));

            Integer remainingLeaves = payrollCalculator.getRemainingLeave(employee);
            history.setLeave(remainingLeaves);

            history.setNetSalary((double) (Integer.parseInt(grossSalary.toString()) - Integer.parseInt(deductions.toString())));
            history.setStatus(PayRollGenerationHistoryEntity.StatusEnum.APPROVED);

            List<DeductionSummaryEntity> deductionSummaries = payrollCalculator.getDeductionSummary(employee);
            history.setDeductionSummary(deductionSummaries);

            payrollHistory.add(history);
        }

        return modelMapper.map(payrollHistory, PayRollGenerationHistory.class);
    }


    public boolean deletePayRollById(long id) {
        try {
            if (payRollRepo.existsById(id)) {
                payRollRepo.deleteById(id);
                return true;
            } else {
                throw new CustomException(HttpStatus.NOT_FOUND, "PayRoll not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while deleting PayRoll with ID: " + id);
        }
    }
}



