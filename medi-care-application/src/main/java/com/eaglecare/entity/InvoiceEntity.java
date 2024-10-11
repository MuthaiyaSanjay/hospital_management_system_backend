package com.eaglecare.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice_table")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "invoice_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date invoiceDate;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "deductions")
    private BigDecimal deductions;

    @Valid
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeductionSummaryEntity> deductionSummary;

    @Column(name = "notes")
    private String notes;

    @Column(name = "reference_id")
    private BigDecimal referenceId;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "invoice_reference_type")
    @Enumerated(EnumType.STRING)
    private InvoiceReferenceTypeEnum invoiceReferenceType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }

    public List<DeductionSummaryEntity> getDeductionSummary() {
        return deductionSummary;
    }

    public void setDeductionSummary(List<DeductionSummaryEntity> deductionSummary) {
        this.deductionSummary = deductionSummary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(BigDecimal referenceId) {
        this.referenceId = referenceId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public InvoiceReferenceTypeEnum getInvoiceReferenceType() {
        return invoiceReferenceType;
    }

    public void setInvoiceReferenceType(InvoiceReferenceTypeEnum invoiceReferenceType) {
        this.invoiceReferenceType = invoiceReferenceType;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    // Enums

    public enum InvoiceReferenceTypeEnum {
        PAYROLL("PAYROLL"),
        EXPENSES("EXPENSES"),
        PURCHASE("PURCHASE"),
        SALES("SALES"),
        BILL("BILL"),
        OTHERS("OTHERS");

        private final String value;

        InvoiceReferenceTypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }

        @JsonCreator
        public static InvoiceReferenceTypeEnum fromValue(String value) {
            for (InvoiceReferenceTypeEnum b : InvoiceReferenceTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    public enum StatusEnum {
        UN_PAID("UN_PAID"),
        PAID("PAID"),
        PROCESSING("PROCESSING");

        private final String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
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
}
