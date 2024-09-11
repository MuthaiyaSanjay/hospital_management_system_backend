package com.medicare.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payroll")
public class PayRollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBasicInfoEntity user;

    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP) // Store as TIMESTAMP in the database
    private Date payment_date;

    @Column(name = "gross_amount")
    private Float gross_amount;

    @Column(name = "deductions")
    private Float deductions;

    @Column(name = "net_amount")
    private Float net_amount;

    public PayRollEntity() {
    }

    public PayRollEntity(Long id, UserBasicInfoEntity user, Date paymentDate, Float grossAmount, Float deductions, Float netAmount) {
        this.id = id;
        this.user = user;
        this.payment_date = paymentDate;
        this.gross_amount = grossAmount;
        this.deductions = deductions;
        this.net_amount = netAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserBasicInfoEntity getUser() {
        return user;
    }

    public void setUser(UserBasicInfoEntity user) {
        this.user = user;
    }

    public Date getPaymentDate() {
        return payment_date;
    }

    public void setPaymentDate(Date paymentDate) {
        this.payment_date = paymentDate;
    }

    public Float getGrossAmount() {
        return gross_amount;
    }

    public void setGrossAmount(Float grossAmount) {
        this.gross_amount = grossAmount;
    }

    public Float getDeductions() {
        return deductions;
    }

    public void setDeductions(Float deductions) {
        this.deductions = deductions;
    }

    public Float getNetAmount() {
        return net_amount;
    }

    public void setNetAmount(Float netAmount) {
        this.net_amount = netAmount;
    }
}
