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
 * GradeWiseSummary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-12T00:43:21.532621800+05:30[Asia/Calcutta]")
public class GradeWiseSummary {

  @JsonProperty("role")
  private String role;

  @JsonProperty("count")
  private BigDecimal count;

  public GradeWiseSummary role(String role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
  */
  
  @Schema(name = "role", example = "role_name", required = false)
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public GradeWiseSummary count(BigDecimal count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  */
  @Valid 
  @Schema(name = "count", example = "10", required = false)
  public BigDecimal getCount() {
    return count;
  }

  public void setCount(BigDecimal count) {
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GradeWiseSummary gradeWiseSummary = (GradeWiseSummary) o;
    return Objects.equals(this.role, gradeWiseSummary.role) &&
        Objects.equals(this.count, gradeWiseSummary.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, count);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GradeWiseSummary {\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
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

