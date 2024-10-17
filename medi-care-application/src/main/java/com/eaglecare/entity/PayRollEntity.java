package com.eaglecare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payroll")
public class PayRollEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private long id;

    @Column(name = "user_id")
    private long user;

    @Column(name = "payment_date")
    private Date payment_date;

    @Column(name = "gross_amount")
    private double gross_amount;

    @Column(name = "deductions")
    private double deductions;

    @Column(name = "net_amount")
    private double net_amount;

    public PayRollEntity() {
    }

    public PayRollEntity(long id, long user, Date paymentDate, double grossAmount, double deductions, double netAmount) {
        this.id = id;
        this.user = user;
        this.payment_date = paymentDate;
        this.gross_amount = grossAmount;
        this.deductions = deductions;
        this.net_amount = netAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public Date getPaymentDate() {
        return payment_date;
    }

    public void setPaymentDate(Date paymentDate) {
        this.payment_date = paymentDate;
    }

    public double getGrossAmount() {
        return gross_amount;
    }

    public void setGrossAmount(double grossAmount) {
        this.gross_amount = grossAmount;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getNetAmount() {
        return net_amount;
    }

    public void setNetAmount(double netAmount) {
        this.net_amount = netAmount;
    }
}
