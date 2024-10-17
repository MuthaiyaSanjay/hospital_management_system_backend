package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.eaglecare.model.GradeWiseSummary;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PayRollSummary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class PayRollSummary {

  @JsonProperty("total_amount")
  private BigDecimal totalAmount;

  @JsonProperty("salary_per_month")
  private Float salaryPerMonth;

  @JsonProperty("gradWise")
  @Valid
  private List<GradeWiseSummary> gradWise = null;

  public PayRollSummary totalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
    return this;
  }

  /**
   * Get totalAmount
   * @return totalAmount
  */
  @Valid 
  @Schema(name = "total_amount", example = "10", required = false)
  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public PayRollSummary salaryPerMonth(Float salaryPerMonth) {
    this.salaryPerMonth = salaryPerMonth;
    return this;
  }

  /**
   * Get salaryPerMonth
   * @return salaryPerMonth
  */
  
  @Schema(name = "salary_per_month", example = "12563.58", required = false)
  public Float getSalaryPerMonth() {
    return salaryPerMonth;
  }

  public void setSalaryPerMonth(Float salaryPerMonth) {
    this.salaryPerMonth = salaryPerMonth;
  }

  public PayRollSummary gradWise(List<GradeWiseSummary> gradWise) {
    this.gradWise = gradWise;
    return this;
  }

  public PayRollSummary addGradWiseItem(GradeWiseSummary gradWiseItem) {
    if (this.gradWise == null) {
      this.gradWise = new ArrayList<>();
    }
    this.gradWise.add(gradWiseItem);
    return this;
  }

  /**
   * Get gradWise
   * @return gradWise
  */
  @Valid 
  @Schema(name = "gradWise", required = false)
  public List<GradeWiseSummary> getGradWise() {
    return gradWise;
  }

  public void setGradWise(List<GradeWiseSummary> gradWise) {
    this.gradWise = gradWise;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayRollSummary payRollSummary = (PayRollSummary) o;
    return Objects.equals(this.totalAmount, payRollSummary.totalAmount) &&
        Objects.equals(this.salaryPerMonth, payRollSummary.salaryPerMonth) &&
        Objects.equals(this.gradWise, payRollSummary.gradWise);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalAmount, salaryPerMonth, gradWise);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayRollSummary {\n");
    sb.append("    totalAmount: ").append(toIndentedString(totalAmount)).append("\n");
    sb.append("    salaryPerMonth: ").append(toIndentedString(salaryPerMonth)).append("\n");
    sb.append("    gradWise: ").append(toIndentedString(gradWise)).append("\n");
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

