package com.medicare.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Summary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-12T00:43:21.532621800+05:30[Asia/Calcutta]")
public class Summary {

  @JsonProperty("total_doctors")
  private Long totalDoctors;

  @JsonProperty("total_patients")
  private Long totalPatients;

  @JsonProperty("current_month_cxpense")
  private BigDecimal currentMonthCxpense;

  @JsonProperty("pending_payaments")
  private BigDecimal pendingPayaments;

  public Summary totalDoctors(Long totalDoctors) {
    this.totalDoctors = totalDoctors;
    return this;
  }

  /**
   * Get totalDoctors
   * @return totalDoctors
  */
  
  @Schema(name = "total_doctors", example = "10", required = false)
  public Long getTotalDoctors() {
    return totalDoctors;
  }

  public void setTotalDoctors(Long totalDoctors) {
    this.totalDoctors = totalDoctors;
  }

  public Summary totalPatients(Long totalPatients) {
    this.totalPatients = totalPatients;
    return this;
  }

  /**
   * Get totalPatients
   * @return totalPatients
  */
  
  @Schema(name = "total_patients", example = "10", required = false)
  public Long getTotalPatients() {
    return totalPatients;
  }

  public void setTotalPatients(Long totalPatients) {
    this.totalPatients = totalPatients;
  }

  public Summary currentMonthCxpense(BigDecimal currentMonthCxpense) {
    this.currentMonthCxpense = currentMonthCxpense;
    return this;
  }

  /**
   * Get currentMonthCxpense
   * @return currentMonthCxpense
  */
  @Valid 
  @Schema(name = "current_month_cxpense", example = "15963.5", required = false)
  public BigDecimal getCurrentMonthCxpense() {
    return currentMonthCxpense;
  }

  public void setCurrentMonthCxpense(BigDecimal currentMonthCxpense) {
    this.currentMonthCxpense = currentMonthCxpense;
  }

  public Summary pendingPayaments(BigDecimal pendingPayaments) {
    this.pendingPayaments = pendingPayaments;
    return this;
  }

  /**
   * Get pendingPayaments
   * @return pendingPayaments
  */
  @Valid 
  @Schema(name = "pending_payaments", example = "10", required = false)
  public BigDecimal getPendingPayaments() {
    return pendingPayaments;
  }

  public void setPendingPayaments(BigDecimal pendingPayaments) {
    this.pendingPayaments = pendingPayaments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Summary summary = (Summary) o;
    return Objects.equals(this.totalDoctors, summary.totalDoctors) &&
        Objects.equals(this.totalPatients, summary.totalPatients) &&
        Objects.equals(this.currentMonthCxpense, summary.currentMonthCxpense) &&
        Objects.equals(this.pendingPayaments, summary.pendingPayaments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalDoctors, totalPatients, currentMonthCxpense, pendingPayaments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Summary {\n");
    sb.append("    totalDoctors: ").append(toIndentedString(totalDoctors)).append("\n");
    sb.append("    totalPatients: ").append(toIndentedString(totalPatients)).append("\n");
    sb.append("    currentMonthCxpense: ").append(toIndentedString(currentMonthCxpense)).append("\n");
    sb.append("    pendingPayaments: ").append(toIndentedString(pendingPayaments)).append("\n");
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

