package com.eaglecare.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deduction_summary")
public class DeductionSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private long id;

    @Column(name = "reason", nullable = false, length = 255)
    @JsonProperty("reason")
    private String reason;

    @Column(name = "amount", nullable = false)
    @JsonProperty("amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;

    @ManyToOne
    @JoinColumn(name = "pay_gen_id")
    private PayRollGenerationHistoryEntity payRollGenerationHistoryEntity;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    public PayRollGenerationHistoryEntity getPayRollGenerationHistoryEntity() {
        return payRollGenerationHistoryEntity;
    }

    public void setPayRollGenerationHistoryEntity(PayRollGenerationHistoryEntity payRollGenerationHistoryEntity) {
        this.payRollGenerationHistoryEntity = payRollGenerationHistoryEntity;
    }
}
