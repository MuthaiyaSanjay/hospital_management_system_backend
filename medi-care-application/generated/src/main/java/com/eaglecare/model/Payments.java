package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Payments
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class Payments {

  @JsonProperty("id")
  private String id;

  @JsonProperty("invoice_id")
  private BigDecimal invoiceId;

  @JsonProperty("payment_due_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date paymentDueDate;

  @JsonProperty("payment_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date paymentDate;

  @JsonProperty("amount")
  private Float amount;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    UN_PAID("UN_PAID"),
    
    PAID("PAID"),
    
    PROCESSING("PROCESSING");

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

  @JsonProperty("status")
  private StatusEnum status;

  public Payments id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "4acd0b30-0ac1-4d0f-938b-c7b86d804375", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Payments invoiceId(BigDecimal invoiceId) {
    this.invoiceId = invoiceId;
    return this;
  }

  /**
   * Get invoiceId
   * @return invoiceId
  */
  @Valid 
  @Schema(name = "invoice_id", example = "158", required = false)
  public BigDecimal getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(BigDecimal invoiceId) {
    this.invoiceId = invoiceId;
  }

  public Payments paymentDueDate(Date paymentDueDate) {
    this.paymentDueDate = paymentDueDate;
    return this;
  }

  /**
   * Invoice Date
   * @return paymentDueDate
  */
  @Valid @Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z$") 
  @Schema(name = "payment_due_date", example = "2024-11-05T00:00Z", description = "Invoice Date", required = false)
  public Date getPaymentDueDate() {
    return paymentDueDate;
  }

  public void setPaymentDueDate(Date paymentDueDate) {
    this.paymentDueDate = paymentDueDate;
  }

  public Payments paymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
    return this;
  }

  /**
   * Invoice Date
   * @return paymentDate
  */
  @Valid @Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z$") 
  @Schema(name = "payment_date", example = "2024-11-05T00:00Z", description = "Invoice Date", required = false)
  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }

  public Payments amount(Float amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  
  @Schema(name = "amount", example = "78965", required = false)
  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public Payments status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", example = "UN_PAID", required = false)
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payments payments = (Payments) o;
    return Objects.equals(this.id, payments.id) &&
        Objects.equals(this.invoiceId, payments.invoiceId) &&
        Objects.equals(this.paymentDueDate, payments.paymentDueDate) &&
        Objects.equals(this.paymentDate, payments.paymentDate) &&
        Objects.equals(this.amount, payments.amount) &&
        Objects.equals(this.status, payments.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, invoiceId, paymentDueDate, paymentDate, amount, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payments {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    invoiceId: ").append(toIndentedString(invoiceId)).append("\n");
    sb.append("    paymentDueDate: ").append(toIndentedString(paymentDueDate)).append("\n");
    sb.append("    paymentDate: ").append(toIndentedString(paymentDate)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

