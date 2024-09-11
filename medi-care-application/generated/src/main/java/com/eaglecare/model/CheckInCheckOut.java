package com.medicare.model;

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
 * CheckInCheckOut
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-12T00:43:21.532621800+05:30[Asia/Calcutta]")
public class CheckInCheckOut {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("userId")
  private Long userId;

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date date;

  @JsonProperty("check_in_time")
  private String checkInTime;

  @JsonProperty("check_out_time")
  private String checkOutTime;

  public CheckInCheckOut id(Long id) {
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

  public CheckInCheckOut userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "userId", required = false)
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public CheckInCheckOut date(Date date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @Schema(name = "date", required = false)
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public CheckInCheckOut checkInTime(String checkInTime) {
    this.checkInTime = checkInTime;
    return this;
  }

  /**
   * Get checkInTime
   * @return checkInTime
  */
  
  @Schema(name = "check_in_time", required = false)
  public String getCheckInTime() {
    return checkInTime;
  }

  public void setCheckInTime(String checkInTime) {
    this.checkInTime = checkInTime;
  }

  public CheckInCheckOut checkOutTime(String checkOutTime) {
    this.checkOutTime = checkOutTime;
    return this;
  }

  /**
   * Get checkOutTime
   * @return checkOutTime
  */
  
  @Schema(name = "check_out_time", required = false)
  public String getCheckOutTime() {
    return checkOutTime;
  }

  public void setCheckOutTime(String checkOutTime) {
    this.checkOutTime = checkOutTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CheckInCheckOut checkInCheckOut = (CheckInCheckOut) o;
    return Objects.equals(this.id, checkInCheckOut.id) &&
        Objects.equals(this.userId, checkInCheckOut.userId) &&
        Objects.equals(this.date, checkInCheckOut.date) &&
        Objects.equals(this.checkInTime, checkInCheckOut.checkInTime) &&
        Objects.equals(this.checkOutTime, checkInCheckOut.checkOutTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, date, checkInTime, checkOutTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CheckInCheckOut {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    checkInTime: ").append(toIndentedString(checkInTime)).append("\n");
    sb.append("    checkOutTime: ").append(toIndentedString(checkOutTime)).append("\n");
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

