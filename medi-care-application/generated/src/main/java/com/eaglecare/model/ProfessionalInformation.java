package com.eaglecare.model;

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
 * ProfessionalInformation
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class ProfessionalInformation {

  @JsonProperty("id")
  private String id;

  @JsonProperty("medical_license_number")
  private String medicalLicenseNumber;

  @JsonProperty("years_of_experience")
  private BigDecimal yearsOfExperience;

  @JsonProperty("qualifications")
  private String qualifications;

  public ProfessionalInformation id(String id) {
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

  public ProfessionalInformation medicalLicenseNumber(String medicalLicenseNumber) {
    this.medicalLicenseNumber = medicalLicenseNumber;
    return this;
  }

  /**
   * Only required for doctors
   * @return medicalLicenseNumber
  */
  
  @Schema(name = "medical_license_number", example = "8975469532", description = "Only required for doctors", required = false)
  public String getMedicalLicenseNumber() {
    return medicalLicenseNumber;
  }

  public void setMedicalLicenseNumber(String medicalLicenseNumber) {
    this.medicalLicenseNumber = medicalLicenseNumber;
  }

  public ProfessionalInformation yearsOfExperience(BigDecimal yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
    return this;
  }

  /**
   * Get yearsOfExperience
   * @return yearsOfExperience
  */
  @Valid 
  @Schema(name = "years_of_experience", example = "10.6", required = false)
  public BigDecimal getYearsOfExperience() {
    return yearsOfExperience;
  }

  public void setYearsOfExperience(BigDecimal yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
  }

  public ProfessionalInformation qualifications(String qualifications) {
    this.qualifications = qualifications;
    return this;
  }

  /**
   * Get qualifications
   * @return qualifications
  */
  
  @Schema(name = "qualifications", example = "MBBS.MS", required = false)
  public String getQualifications() {
    return qualifications;
  }

  public void setQualifications(String qualifications) {
    this.qualifications = qualifications;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfessionalInformation professionalInformation = (ProfessionalInformation) o;
    return Objects.equals(this.id, professionalInformation.id) &&
        Objects.equals(this.medicalLicenseNumber, professionalInformation.medicalLicenseNumber) &&
        Objects.equals(this.yearsOfExperience, professionalInformation.yearsOfExperience) &&
        Objects.equals(this.qualifications, professionalInformation.qualifications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, medicalLicenseNumber, yearsOfExperience, qualifications);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProfessionalInformation {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    medicalLicenseNumber: ").append(toIndentedString(medicalLicenseNumber)).append("\n");
    sb.append("    yearsOfExperience: ").append(toIndentedString(yearsOfExperience)).append("\n");
    sb.append("    qualifications: ").append(toIndentedString(qualifications)).append("\n");
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

