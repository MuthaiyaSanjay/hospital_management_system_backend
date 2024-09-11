package com.medicare.model;

import java.net.URI;
import java.util.Objects;
import com.medicare.model.Contact;
import com.medicare.model.UserBasiInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Patients
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-12T00:43:21.532621800+05:30[Asia/Calcutta]")
public class Patients {

  @JsonProperty("id")
  private String id;

  @JsonProperty("basic_info")
  private UserBasiInfo basicInfo;

  @JsonProperty("contact")
  private Contact contact;

  public Patients id(String id) {
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

  public Patients basicInfo(UserBasiInfo basicInfo) {
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

  public Patients contact(Contact contact) {
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
    Patients patients = (Patients) o;
    return Objects.equals(this.id, patients.id) &&
        Objects.equals(this.basicInfo, patients.basicInfo) &&
        Objects.equals(this.contact, patients.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, basicInfo, contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Patients {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

