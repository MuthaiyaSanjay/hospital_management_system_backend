package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.eaglecare.model.DeductionSummary;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Invoices
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class Invoices {

  @JsonProperty("id")
  private String id;

  @JsonProperty("invoice_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date invoiceDate;

  @JsonProperty("total_amount")
  private Float totalAmount;

  @JsonProperty("deductions")
  private BigDecimal deductions;

  @JsonProperty("deduction_summary")
  @Valid
  private List<DeductionSummary> deductionSummary = null;

  @JsonProperty("notes")
  private String notes;

  @JsonProperty("reference_id")
  private BigDecimal referenceId;

  @JsonProperty("receiver_name")
  private String receiverName;

  /**
   * Gets or Sets invoiceReferenceType
   */
  public enum InvoiceReferenceTypeEnum {
    PAYROLL("PAYROLL"),
    
    EXPENSES("EXPENSES"),
    
    PURCHASE("PURCHASE"),
    
    SALES("SALES"),
    
    BILL("BILL"),
    
    OTHERS("OTHERS");

    private String value;

    InvoiceReferenceTypeEnum(String value) {
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
    public static InvoiceReferenceTypeEnum fromValue(String value) {
      for (InvoiceReferenceTypeEnum b : InvoiceReferenceTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("invoice_reference_type")
  private InvoiceReferenceTypeEnum invoiceReferenceType;

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

  public Invoices id(String id) {
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

  public Invoices invoiceDate(Date invoiceDate) {
    this.invoiceDate = invoiceDate;
    return this;
  }

  /**
   * Invoice Date
   * @return invoiceDate
  */
  @Valid @Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z$") 
  @Schema(name = "invoice_date", example = "2024-11-05T00:00Z", description = "Invoice Date", required = false)
  public Date getInvoiceDate() {
    return invoiceDate;
  }

  public void setInvoiceDate(Date invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  public Invoices totalAmount(Float totalAmount) {
    this.totalAmount = totalAmount;
    return this;
  }

  /**
   * Get totalAmount
   * @return totalAmount
  */
  
  @Schema(name = "total_amount", example = "78965", required = false)
  public Float getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Float totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Invoices deductions(BigDecimal deductions) {
    this.deductions = deductions;
    return this;
  }

  /**
   * Get deductions
   * @return deductions
  */
  @Valid 
  @Schema(name = "deductions", example = "1596.32", required = false)
  public BigDecimal getDeductions() {
    return deductions;
  }

  public void setDeductions(BigDecimal deductions) {
    this.deductions = deductions;
  }

  public Invoices deductionSummary(List<DeductionSummary> deductionSummary) {
    this.deductionSummary = deductionSummary;
    return this;
  }

  public Invoices addDeductionSummaryItem(DeductionSummary deductionSummaryItem) {
    if (this.deductionSummary == null) {
      this.deductionSummary = new ArrayList<>();
    }
    this.deductionSummary.add(deductionSummaryItem);
    return this;
  }

  /**
   * Get deductionSummary
   * @return deductionSummary
  */
  @Valid 
  @Schema(name = "deduction_summary", required = false)
  public List<DeductionSummary> getDeductionSummary() {
    return deductionSummary;
  }

  public void setDeductionSummary(List<DeductionSummary> deductionSummary) {
    this.deductionSummary = deductionSummary;
  }

  public Invoices notes(String notes) {
    this.notes = notes;
    return this;
  }

  /**
   * Get notes
   * @return notes
  */
  
  @Schema(name = "notes", example = "invoice for the employees salary", required = false)
  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Invoices referenceId(BigDecimal referenceId) {
    this.referenceId = referenceId;
    return this;
  }

  /**
   * user id or reference id of the table based on the type.If payroll its mean payroll history id other wise user id(vendor).
   * @return referenceId
  */
  @Valid 
  @Schema(name = "reference_id", example = "158", description = "user id or reference id of the table based on the type.If payroll its mean payroll history id other wise user id(vendor).", required = false)
  public BigDecimal getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(BigDecimal referenceId) {
    this.referenceId = referenceId;
  }

  public Invoices receiverName(String receiverName) {
    this.receiverName = receiverName;
    return this;
  }

  /**
   * Get receiverName
   * @return receiverName
  */
  
  @Schema(name = "receiver_name", example = "aravind", required = false)
  public String getReceiverName() {
    return receiverName;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public Invoices invoiceReferenceType(InvoiceReferenceTypeEnum invoiceReferenceType) {
    this.invoiceReferenceType = invoiceReferenceType;
    return this;
  }

  /**
   * Get invoiceReferenceType
   * @return invoiceReferenceType
  */
  
  @Schema(name = "invoice_reference_type", example = "PAYROLL", required = false)
  public InvoiceReferenceTypeEnum getInvoiceReferenceType() {
    return invoiceReferenceType;
  }

  public void setInvoiceReferenceType(InvoiceReferenceTypeEnum invoiceReferenceType) {
    this.invoiceReferenceType = invoiceReferenceType;
  }

  public Invoices status(StatusEnum status) {
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
    Invoices invoices = (Invoices) o;
    return Objects.equals(this.id, invoices.id) &&
        Objects.equals(this.invoiceDate, invoices.invoiceDate) &&
        Objects.equals(this.totalAmount, invoices.totalAmount) &&
        Objects.equals(this.deductions, invoices.deductions) &&
        Objects.equals(this.deductionSummary, invoices.deductionSummary) &&
        Objects.equals(this.notes, invoices.notes) &&
        Objects.equals(this.referenceId, invoices.referenceId) &&
        Objects.equals(this.receiverName, invoices.receiverName) &&
        Objects.equals(this.invoiceReferenceType, invoices.invoiceReferenceType) &&
        Objects.equals(this.status, invoices.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, invoiceDate, totalAmount, deductions, deductionSummary, notes, referenceId, receiverName, invoiceReferenceType, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Invoices {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    invoiceDate: ").append(toIndentedString(invoiceDate)).append("\n");
    sb.append("    totalAmount: ").append(toIndentedString(totalAmount)).append("\n");
    sb.append("    deductions: ").append(toIndentedString(deductions)).append("\n");
    sb.append("    deductionSummary: ").append(toIndentedString(deductionSummary)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
    sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
    sb.append("    receiverName: ").append(toIndentedString(receiverName)).append("\n");
    sb.append("    invoiceReferenceType: ").append(toIndentedString(invoiceReferenceType)).append("\n");
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

