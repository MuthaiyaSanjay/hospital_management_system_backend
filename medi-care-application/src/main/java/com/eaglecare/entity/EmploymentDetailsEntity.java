package com.eaglecare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "employment_details")
public class EmploymentDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_details_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "joining_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joiningDate;

    @Column(name = "relieving_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date relievingDate;

    @Column(name = "department")
    private String department;

    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @OneToOne
    @JoinColumn(name = "payroll_id")
    private PayRollEntity payroll;

    public EmploymentDetailsEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Date getRelievingDate() {
        return relievingDate;
    }

    public void setRelievingDate(Date relievingDate) {
        this.relievingDate = relievingDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public PayRollEntity getPayroll() {
        return payroll;
    }

    public void setPayroll(PayRollEntity payroll) {
        this.payroll = payroll;
    }

    @Override
    public String toString() {
        return "EmploymentDetails{" +
                "id=" + id +
                ", joiningDate=" + joiningDate +
                ", relievingDate=" + relievingDate +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", payroll=" + payroll +
                '}';
    }
}
