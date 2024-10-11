package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LeaveApproval
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class LeaveApproval {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("leave_application_id")
  private Long leaveApplicationId;

  @JsonProperty("approver_id")
  private Long approverId;

  @JsonProperty("approval_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date approvalDate;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    PENDING("PENDING"),
    
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

  @JsonProperty("comments")
  private String comments;

  public LeaveApproval id(Long id) {
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

  public LeaveApproval leaveApplicationId(Long leaveApplicationId) {
    this.leaveApplicationId = leaveApplicationId;
    return this;
  }

  /**
   * Get leaveApplicationId
   * @return leaveApplicationId
  */
  
  @Schema(name = "leave_application_id", required = false)
  public Long getLeaveApplicationId() {
    return leaveApplicationId;
  }

  public void setLeaveApplicationId(Long leaveApplicationId) {
    this.leaveApplicationId = leaveApplicationId;
  }

  public LeaveApproval approverId(Long approverId) {
    this.approverId = approverId;
    return this;
  }

  /**
   * Get approverId
   * @return approverId
  */
  
  @Schema(name = "approver_id", required = false)
  public Long getApproverId() {
    return approverId;
  }

  public void setApproverId(Long approverId) {
    this.approverId = approverId;
  }

  public LeaveApproval approvalDate(Date approvalDate) {
    this.approvalDate = approvalDate;
    return this;
  }

  /**
   * Get approvalDate
   * @return approvalDate
  */
  @Valid 
  @Schema(name = "approval_date", required = false)
  public Date getApprovalDate() {
    return approvalDate;
  }

  public void setApprovalDate(Date approvalDate) {
    this.approvalDate = approvalDate;
  }

  public LeaveApproval status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", required = false)
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public LeaveApproval comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Get comments
   * @return comments
  */
  
  @Schema(name = "comments", required = false)
  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveApproval leaveApproval = (LeaveApproval) o;
    return Objects.equals(this.id, leaveApproval.id) &&
        Objects.equals(this.leaveApplicationId, leaveApproval.leaveApplicationId) &&
        Objects.equals(this.approverId, leaveApproval.approverId) &&
        Objects.equals(this.approvalDate, leaveApproval.approvalDate) &&
        Objects.equals(this.status, leaveApproval.status) &&
        Objects.equals(this.comments, leaveApproval.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, leaveApplicationId, approverId, approvalDate, status, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveApproval {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    leaveApplicationId: ").append(toIndentedString(leaveApplicationId)).append("\n");
    sb.append("    approverId: ").append(toIndentedString(approverId)).append("\n");
    sb.append("    approvalDate: ").append(toIndentedString(approvalDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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

