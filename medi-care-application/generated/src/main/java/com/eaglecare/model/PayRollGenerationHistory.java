package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.eaglecare.model.DeductionSummary;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PayRollGenerationHistory
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class PayRollGenerationHistory {

  @JsonProperty("emp_id")
  private String empId;

  @JsonProperty("emp_name")
  private String empName;

  @JsonProperty("leave")
  private Integer leave;

  @JsonProperty("cross_salary")
  private Double crossSalary;

  @JsonProperty("deductions")
  private Double deductions;

  @JsonProperty("deduction_summary")
  @Valid
  private List<DeductionSummary> deductionSummary = null;

  @JsonProperty("net_salary")
  private Double netSalary;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    APPROVED("APPROVED"),
    
    REJECTED("REJECTED");

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

  public PayRollGenerationHistory empId(String empId) {
    this.empId = empId;
    return this;
  }

  /**
   * Get empId
   * @return empId
  */
  
  @Schema(name = "emp_id", example = "4acd0b30-0ac1-4d0f-938b-c7b86d804375", required = false)
  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public PayRollGenerationHistory empName(String empName) {
    this.empName = empName;
    return this;
  }

  /**
   * Get empName
   * @return empName
  */
  
  @Schema(name = "emp_name", example = "aravind", required = false)
  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public PayRollGenerationHistory leave(Integer leave) {
    this.leave = leave;
    return this;
  }

  /**
   * Get leave
   * @return leave
  */
  
  @Schema(name = "leave", example = "10", required = false)
  public Integer getLeave() {
    return leave;
  }

  public void setLeave(Integer leave) {
    this.leave = leave;
  }

  public PayRollGenerationHistory crossSalary(Double crossSalary) {
    this.crossSalary = crossSalary;
    return this;
  }

  /**
   * Get crossSalary
   * @return crossSalary
  */
  
  @Schema(name = "cross_salary", example = "15000.63", required = false)
  public Double getCrossSalary() {
    return crossSalary;
  }

  public void setCrossSalary(Double crossSalary) {
    this.crossSalary = crossSalary;
  }

  public PayRollGenerationHistory deductions(Double deductions) {
    this.deductions = deductions;
    return this;
  }

  /**
   * Get deductions
   * @return deductions
  */
  
  @Schema(name = "deductions", example = "1596.32", required = false)
  public Double getDeductions() {
    return deductions;
  }

  public void setDeductions(Double deductions) {
    this.deductions = deductions;
  }

  public PayRollGenerationHistory deductionSummary(List<DeductionSummary> deductionSummary) {
    this.deductionSummary = deductionSummary;
    return this;
  }

  public PayRollGenerationHistory addDeductionSummaryItem(DeductionSummary deductionSummaryItem) {
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

  public PayRollGenerationHistory netSalary(Double netSalary) {
    this.netSalary = netSalary;
    return this;
  }

  /**
   * Get netSalary
   * @return netSalary
  */
  
  @Schema(name = "net_salary", example = "14899.9", required = false)
  public Double getNetSalary() {
    return netSalary;
  }

  public void setNetSalary(Double netSalary) {
    this.netSalary = netSalary;
  }

  public PayRollGenerationHistory status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", example = "APPROVED", required = false)
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
    PayRollGenerationHistory payRollGenerationHistory = (PayRollGenerationHistory) o;
    return Objects.equals(this.empId, payRollGenerationHistory.empId) &&
        Objects.equals(this.empName, payRollGenerationHistory.empName) &&
        Objects.equals(this.leave, payRollGenerationHistory.leave) &&
        Objects.equals(this.crossSalary, payRollGenerationHistory.crossSalary) &&
        Objects.equals(this.deductions, payRollGenerationHistory.deductions) &&
        Objects.equals(this.deductionSummary, payRollGenerationHistory.deductionSummary) &&
        Objects.equals(this.netSalary, payRollGenerationHistory.netSalary) &&
        Objects.equals(this.status, payRollGenerationHistory.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(empId, empName, leave, crossSalary, deductions, deductionSummary, netSalary, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayRollGenerationHistory {\n");
    sb.append("    empId: ").append(toIndentedString(empId)).append("\n");
    sb.append("    empName: ").append(toIndentedString(empName)).append("\n");
    sb.append("    leave: ").append(toIndentedString(leave)).append("\n");
    sb.append("    crossSalary: ").append(toIndentedString(crossSalary)).append("\n");
    sb.append("    deductions: ").append(toIndentedString(deductions)).append("\n");
    sb.append("    deductionSummary: ").append(toIndentedString(deductionSummary)).append("\n");
    sb.append("    netSalary: ").append(toIndentedString(netSalary)).append("\n");
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

