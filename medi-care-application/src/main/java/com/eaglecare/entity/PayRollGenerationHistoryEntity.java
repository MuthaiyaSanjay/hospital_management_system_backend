package com.eaglecare.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import java.util.List;

@Entity
@Table(name = "payroll_generation_history")
public class PayRollGenerationHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("pay_gen_id")
    private Long id;

    @Column(name = "emp_id", nullable = false, length = 50)
    @JsonProperty("emp_id")
    private String empId;

    @Column(name = "emp_name", nullable = false, length = 100)
    @JsonProperty("emp_name")
    private String empName;

    @Column(name = "leave")
    @JsonProperty("leave")
    private Integer leave;

    @Column(name = "cross_salary")
    @JsonProperty("cross_salary")
    private Double crossSalary;

    @Column(name = "deductions")
    @JsonProperty("deductions")
    private Double deductions;

    @OneToMany(mappedBy = "payRollGenerationHistoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeductionSummaryEntity> deductionSummary;

    @Column(name = "net_salary")
    @JsonProperty("net_salary")
    private Double netSalary;

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    public enum StatusEnum {
        APPROVED("APPROVED"),
        REJECTED("REJECTED");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String value) {
            for (StatusEnum b : StatusEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    public Double getCrossSalary() {
        return crossSalary;
    }

    public void setCrossSalary(Double crossSalary) {
        this.crossSalary = crossSalary;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public @Valid List<DeductionSummaryEntity> getDeductionSummary() {
        return deductionSummary;
    }

    public void setDeductionSummary(@Valid List<DeductionSummaryEntity> deductionSummary) {
        this.deductionSummary = deductionSummary;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
