package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LeaveAllocation
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class LeaveAllocation {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("user_id")
  private Long userId;

  /**
   * Gets or Sets allocationType
   */
  public enum AllocationTypeEnum {
    MONTH("EACH_MONTH"),
    
    YEAR("EACH_YEAR");

    private String value;

    AllocationTypeEnum(String value) {
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
    public static AllocationTypeEnum fromValue(String value) {
      for (AllocationTypeEnum b : AllocationTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("allocation_type")
  private AllocationTypeEnum allocationType;

  @JsonProperty("allocated_days")
  private BigDecimal allocatedDays;

  public LeaveAllocation id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LeaveAllocation userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "user_id", required = false)
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public LeaveAllocation allocationType(AllocationTypeEnum allocationType) {
    this.allocationType = allocationType;
    return this;
  }

  /**
   * Get allocationType
   * @return allocationType
  */
  
  @Schema(name = "allocation_type", example = "EACH_MONTH", required = false)
  public AllocationTypeEnum getAllocationType() {
    return allocationType;
  }

  public void setAllocationType(AllocationTypeEnum allocationType) {
    this.allocationType = allocationType;
  }

  public LeaveAllocation allocatedDays(BigDecimal allocatedDays) {
    this.allocatedDays = allocatedDays;
    return this;
  }

  /**
   * Get allocatedDays
   * @return allocatedDays
  */
  @Valid 
  @Schema(name = "allocated_days", required = false)
  public BigDecimal getAllocatedDays() {
    return allocatedDays;
  }

  public void setAllocatedDays(BigDecimal allocatedDays) {
    this.allocatedDays = allocatedDays;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveAllocation leaveAllocation = (LeaveAllocation) o;
    return Objects.equals(this.id, leaveAllocation.id) &&
        Objects.equals(this.userId, leaveAllocation.userId) &&
        Objects.equals(this.allocationType, leaveAllocation.allocationType) &&
        Objects.equals(this.allocatedDays, leaveAllocation.allocatedDays);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, allocationType, allocatedDays);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveAllocation {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    allocationType: ").append(toIndentedString(allocationType)).append("\n");
    sb.append("    allocatedDays: ").append(toIndentedString(allocatedDays)).append("\n");
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

