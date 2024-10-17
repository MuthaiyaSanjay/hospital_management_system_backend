package com.eaglecare.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
public class PaymentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "invoice_id")
    private double invoiceId;

    @Column(name = "payment_due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDueDate;

    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "amount")
    private double amount;

    public enum StatusEnum {
        UN_PAID("UN_PAID"),
        PAID("PAID"),
        PROCESSING("PROCESSING");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }


        public static StatusEnum fromValue(String value) {
            for (StatusEnum b : StatusEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @Column(name = "status")
    private StatusEnum status;


    @Override
    public String toString() {
        return "PaymentsEntity{" +
                "id=" + id +
                ", invoiceId=" + invoiceId +
                ", paymentDueDate=" + paymentDueDate +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(double invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
