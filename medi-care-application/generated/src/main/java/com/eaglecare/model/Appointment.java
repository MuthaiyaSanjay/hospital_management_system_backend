package com.eaglecare.model;

import java.net.URI;
import java.util.Objects;
import com.eaglecare.model.Doctor;
import com.eaglecare.model.Patients;
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
 * Appointment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
public class Appointment {

  @JsonProperty("id")
  private String id;

  @JsonProperty("appointment_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date appointmentDate;

  @JsonProperty("appointment_time")
  private String appointmentTime;

  @JsonProperty("patient")
  private Patients patient;

  @JsonProperty("doctor")
  private Doctor doctor;

  public Appointment id(String id) {
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

  public Appointment appointmentDate(Date appointmentDate) {
    this.appointmentDate = appointmentDate;
    return this;
  }

  /**
   * Appointment Booking Date
   * @return appointmentDate
  */
  @Valid @Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z$") 
  @Schema(name = "appointment_date", example = "2024-11-05T00:00Z", description = "Appointment Booking Date", required = false)
  public Date getAppointmentDate() {
    return appointmentDate;
  }

  public void setAppointmentDate(Date appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  public Appointment appointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
    return this;
  }

  /**
   * Get appointmentTime
   * @return appointmentTime
  */
  
  @Schema(name = "appointment_time", example = "10:00 AM", required = false)
  public String getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

  public Appointment patient(Patients patient) {
    this.patient = patient;
    return this;
  }

  /**
   * Get patient
   * @return patient
  */
  @Valid 
  @Schema(name = "patient", required = false)
  public Patients getPatient() {
    return patient;
  }

  public void setPatient(Patients patient) {
    this.patient = patient;
  }

  public Appointment doctor(Doctor doctor) {
    this.doctor = doctor;
    return this;
  }

  /**
   * Get doctor
   * @return doctor
  */
  @Valid 
  @Schema(name = "doctor", required = false)
  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Appointment appointment = (Appointment) o;
    return Objects.equals(this.id, appointment.id) &&
        Objects.equals(this.appointmentDate, appointment.appointmentDate) &&
        Objects.equals(this.appointmentTime, appointment.appointmentTime) &&
        Objects.equals(this.patient, appointment.patient) &&
        Objects.equals(this.doctor, appointment.doctor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, appointmentDate, appointmentTime, patient, doctor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Appointment {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    appointmentDate: ").append(toIndentedString(appointmentDate)).append("\n");
    sb.append("    appointmentTime: ").append(toIndentedString(appointmentTime)).append("\n");
    sb.append("    patient: ").append(toIndentedString(patient)).append("\n");
    sb.append("    doctor: ").append(toIndentedString(doctor)).append("\n");
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

