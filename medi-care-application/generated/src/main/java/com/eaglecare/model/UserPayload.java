package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.eaglecare.model.Contact;
import com.eaglecare.model.UserBasiInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UserPayload
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class UserPayload {

  @JsonProperty("user_id")
  private String userId;

  @JsonProperty("basic_info")
  private UserBasiInfo basicInfo;

  @JsonProperty("contact")
  private Contact contact;

  public UserPayload userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "user_id", example = "fb17eecf-5ec6-4c13-a5bd-5ce1451472d0", required = false)
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public UserPayload basicInfo(UserBasiInfo basicInfo) {
    this.basicInfo = basicInfo;
    return this;
  }

  /**
   * Get basicInfo
   * @return basicInfo
  */
  @Valid 
  @Schema(name = "basic_info", required = false)
  public UserBasiInfo getBasicInfo() {
    return basicInfo;
  }

  public void setBasicInfo(UserBasiInfo basicInfo) {
    this.basicInfo = basicInfo;
  }

  public UserPayload contact(Contact contact) {
    this.contact = contact;
    return this;
  }

  /**
   * Get contact
   * @return contact
  */
  @Valid 
  @Schema(name = "contact", required = false)
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserPayload userPayload = (UserPayload) o;
    return Objects.equals(this.userId, userPayload.userId) &&
        Objects.equals(this.basicInfo, userPayload.basicInfo) &&
        Objects.equals(this.contact, userPayload.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, basicInfo, contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserPayload {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    basicInfo: ").append(toIndentedString(basicInfo)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
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

