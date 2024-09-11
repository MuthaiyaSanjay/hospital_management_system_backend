package com.medicare.model;

import java.net.URI;
import java.util.Objects;
import com.medicare.model.PayRoll;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * EmploymentDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-12T00:43:21.532621800+05:30[Asia/Calcutta]")
public class EmploymentDetails {

  @JsonProperty("id")
  private String id;

  @JsonProperty("joining_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date joiningDate;

  @JsonProperty("relieving_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date relievingDate;

  @JsonProperty("department")
  private String department;

  @JsonProperty("salary")
  private BigDecimal salary;

  @JsonProperty("payroll")
  private PayRoll payroll;

  public EmploymentDetails id(String id) {
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

  public EmploymentDetails joiningDate(Date joiningDate) {
    this.joiningDate = joiningDate;
    return this;
  }

  /**
   * User joining date.
   * @return joiningDate
  */
  @Valid @Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z$") 
  @Schema(name = "joining_date", example = "2024-11-05T00:00Z", description = "User joining date.", required = false)
  public Date getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(Date joiningDate) {
    this.joiningDate = joiningDate;
  }

  public EmploymentDetails relievingDate(Date relievingDate) {
    this.relievingDate = relievingDate;
    return this;
  }

  /**
   * User joining date.
   * @return relievingDate
  */
  @Valid @Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z$") 
  @Schema(name = "relieving_date", example = "2024-11-05T00:00Z", description = "User joining date.", required = false)
  public Date getRelievingDate() {
    return relievingDate;
  }

  public void setRelievingDate(Date relievingDate) {
    this.relievingDate = relievingDate;
  }

  public EmploymentDetails department(String department) {
    this.department = department;
    return this;
  }

  /**
   * Get department
   * @return department
  */
  
  @Schema(name = "department", example = "CLINIC_STAFF", required = false)
  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public EmploymentDetails salary(BigDecimal salary) {
    this.salary = salary;
    return this;
  }

  /**
   * Get salary
   * @return salary
  */
  @Valid 
  @Schema(name = "salary", example = "18500", required = false)
  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public EmploymentDetails payroll(PayRoll payroll) {
    this.payroll = payroll;
    return this;
  }

  /**
   * Get payroll
   * @return payroll
  */
  @Valid 
  @Schema(name = "payroll", required = false)
  public PayRoll getPayroll() {
    return payroll;
  }

  public void setPayroll(PayRoll payroll) {
    this.payroll = payroll;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmploymentDetails employmentDetails = (EmploymentDetails) o;
    return Objects.equals(this.id, employmentDetails.id) &&
        Objects.equals(this.joiningDate, employmentDetails.joiningDate) &&
        Objects.equals(this.relievingDate, employmentDetails.relievingDate) &&
        Objects.equals(this.department, employmentDetails.department) &&
        Objects.equals(this.salary, employmentDetails.salary) &&
        Objects.equals(this.payroll, employmentDetails.payroll);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, joiningDate, relievingDate, department, salary, payroll);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmploymentDetails {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    joiningDate: ").append(toIndentedString(joiningDate)).append("\n");
    sb.append("    relievingDate: ").append(toIndentedString(relievingDate)).append("\n");
    sb.append("    department: ").append(toIndentedString(department)).append("\n");
    sb.append("    salary: ").append(toIndentedString(salary)).append("\n");
    sb.append("    payroll: ").append(toIndentedString(payroll)).append("\n");
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

