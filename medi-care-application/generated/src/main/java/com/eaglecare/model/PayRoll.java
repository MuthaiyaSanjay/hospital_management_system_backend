package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PayRoll
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class PayRoll {

  @JsonProperty("id")
  private String id;

  @JsonProperty("user")
  private String user;

  @JsonProperty("payment_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date paymentDate;

  @JsonProperty("gross_amount")
  private Float grossAmount;

  @JsonProperty("deductions")
  private Float deductions;

  @JsonProperty("net_amount")
  private Float netAmount;

  public PayRoll id(String id) {
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

  public PayRoll user(String user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  
  @Schema(name = "user", required = false)
  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public PayRoll paymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
    return this;
  }

  /**
   * Appointment Booking Date
   * @return paymentDate
  */
  @Valid @Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z$") 
  @Schema(name = "payment_date", example = "2024-11-05T00:00Z", description = "Appointment Booking Date", required = false)
  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }

  public PayRoll grossAmount(Float grossAmount) {
    this.grossAmount = grossAmount;
    return this;
  }

  /**
   * Get grossAmount
   * @return grossAmount
  */
  
  @Schema(name = "gross_amount", required = false)
  public Float getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(Float grossAmount) {
    this.grossAmount = grossAmount;
  }

  public PayRoll deductions(Float deductions) {
    this.deductions = deductions;
    return this;
  }

  /**
   * Get deductions
   * @return deductions
  */
  
  @Schema(name = "deductions", required = false)
  public Float getDeductions() {
    return deductions;
  }

  public void setDeductions(Float deductions) {
    this.deductions = deductions;
  }

  public PayRoll netAmount(Float netAmount) {
    this.netAmount = netAmount;
    return this;
  }

  /**
   * Get netAmount
   * @return netAmount
  */
  
  @Schema(name = "net_amount", required = false)
  public Float getNetAmount() {
    return netAmount;
  }

  public void setNetAmount(Float netAmount) {
    this.netAmount = netAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayRoll payRoll = (PayRoll) o;
    return Objects.equals(this.id, payRoll.id) &&
        Objects.equals(this.user, payRoll.user) &&
        Objects.equals(this.paymentDate, payRoll.paymentDate) &&
        Objects.equals(this.grossAmount, payRoll.grossAmount) &&
        Objects.equals(this.deductions, payRoll.deductions) &&
        Objects.equals(this.netAmount, payRoll.netAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, paymentDate, grossAmount, deductions, netAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayRoll {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    paymentDate: ").append(toIndentedString(paymentDate)).append("\n");
    sb.append("    grossAmount: ").append(toIndentedString(grossAmount)).append("\n");
    sb.append("    deductions: ").append(toIndentedString(deductions)).append("\n");
    sb.append("    netAmount: ").append(toIndentedString(netAmount)).append("\n");
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

