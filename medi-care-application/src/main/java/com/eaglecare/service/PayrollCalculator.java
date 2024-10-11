package com.eaglecare.service;

import com.eaglecare.entity.DeductionSummaryEntity;
import com.eaglecare.entity.LeaveAllocationEntity;
import com.eaglecare.entity.PayRollGenerationHistoryEntity;
import com.eaglecare.entity.UserBasicInfoEntity; // Adjust import based on your package structure
import com.eaglecare.repository.LeaveAllocationEntityRepo;
import com.eaglecare.repository.PayRollGeneraionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PayrollCalculator {

    @Autowired
    PayRollGeneraionHistoryRepo payRollGeneraionHistoryRepo;

    @Autowired
    LeaveAllocationEntityRepo leaveAllocationEntityRepo;

    private static final BigDecimal LEAVE_DEDUCTION_PER_DAY = BigDecimal.valueOf(100.0); // Example deduction per leave
    private static final BigDecimal TAX_DEDUCTION_RATE = BigDecimal.valueOf(0.10); // 10% tax rate

    public double calculateGrossSalary(UserBasicInfoEntity employee) {
        // Assuming a method getGrossSalary() in EmploymentDetailsEntity
        return employee.getEmploymentDetails().getPayroll().getGrossAmount(); // Adjust according to your actual structure
    }

    public BigDecimal calculateDeductions(UserBasicInfoEntity employee) {
        BigDecimal leaveDeductions = calculateLeaveDeductions(employee);
        BigDecimal taxDeductions = calculateTaxDeductions(BigDecimal.valueOf(calculateGrossSalary(employee)));

        return leaveDeductions.add(taxDeductions);
    }

    private BigDecimal calculateLeaveDeductions(UserBasicInfoEntity employee) {
        Optional<PayRollGenerationHistoryEntity> payRollGenerationHistoryEntity = payRollGeneraionHistoryRepo.findById(employee.getUserId().toString());
        if (payRollGenerationHistoryEntity.isPresent()) {
            throw new RuntimeException("Not Found in PayrollGenerationHistory");
        }
        PayRollGenerationHistoryEntity pay = payRollGenerationHistoryEntity.get();

        int leavesTaken = pay.getLeave();
        return LEAVE_DEDUCTION_PER_DAY.multiply(BigDecimal.valueOf(leavesTaken));
    }

    private BigDecimal calculateTaxDeductions(BigDecimal grossSalary) {
        return grossSalary.multiply(TAX_DEDUCTION_RATE);
    }

    public Integer getRemainingLeave(UserBasicInfoEntity employee) {
        // Fetch leave allocation entity based on the employee's user ID
        Optional<LeaveAllocationEntity> leaveAllocationEntity = leaveAllocationEntityRepo.findById(employee.getUserId());
        if (leaveAllocationEntity.isEmpty()) {
            throw new RuntimeException("Not Found in Leave Allocation Entity for user ID: " + employee.getUserId());
        }
        LeaveAllocationEntity leave = leaveAllocationEntity.get();

        // Fetch payroll generation history entity based on the employee's user ID
        Optional<PayRollGenerationHistoryEntity> payRollGenerationHistoryEntity = payRollGeneraionHistoryRepo.findById(employee.getUserId().toString());
        if (payRollGenerationHistoryEntity.isEmpty()) {
            throw new RuntimeException("Not Found in Payroll Generation History for user ID: " + employee.getUserId());
        }
        PayRollGenerationHistoryEntity pay = payRollGenerationHistoryEntity.get();

        // Get the number of leaves taken from the payroll entity
        int leavesTaken = pay.getLeave();

        // Ensure allocated days are not null before parsing
        BigDecimal allocatedDaysBigDecimal = leave.getAllocatedDays();
        if (allocatedDaysBigDecimal == null) {
            throw new RuntimeException("Allocated days cannot be null for user ID: " + employee.getUserId());
        }

        // Convert BigDecimal to long
        long allocatedDays = allocatedDaysBigDecimal.longValue();

        // Calculate remaining leaves
        return Math.toIntExact(allocatedDays - leavesTaken);
    }


    public List<DeductionSummaryEntity> getDeductionSummary(UserBasicInfoEntity employee) {
        List<DeductionSummaryEntity> summaries = new ArrayList<>();

        // Ensure employment details are not null
        if (employee.getEmploymentDetails() == null) {
            throw new IllegalArgumentException("Employment details cannot be null for user ID: " + employee.getUserId());
        }
        Optional<PayRollGenerationHistoryEntity> payRollGenerationHistoryEntity = payRollGeneraionHistoryRepo.findById(employee.getUserId().toString());
        if (payRollGenerationHistoryEntity.isEmpty()) {
            throw new RuntimeException("Not Found in Payroll Generation History for user ID: " + employee.getUserId());
        }
        PayRollGenerationHistoryEntity pay = payRollGenerationHistoryEntity.get();

        // Get the number of leaves taken from the payroll entity
        int leavesTaken = pay.getLeave();

        // Leave deductions
        if (leavesTaken > 0) {
            DeductionSummaryEntity leaveDeduction = new DeductionSummaryEntity();
            leaveDeduction.setReason("Leave Deduction");
            leaveDeduction.setAmount(calculateLeaveDeductions(employee));
            summaries.add(leaveDeduction);
        }

        // Tax deductions
        DeductionSummaryEntity taxDeduction = new DeductionSummaryEntity();
        taxDeduction.setReason("Tax Deduction (10%)");
        taxDeduction.setAmount(calculateTaxDeductions(BigDecimal.valueOf(calculateGrossSalary(employee))));
        summaries.add(taxDeduction);

        return summaries;
    }


}
