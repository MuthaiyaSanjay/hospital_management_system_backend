package com.eaglecare.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "professional_information")
public class ProfessionalInformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medical_license_number", nullable = false, unique = true)
    private String medicalLicenseNumber;

    @Column(name = "years_of_experience", precision = 10, scale = 2)
    private BigDecimal yearsOfExperience;

    @Column(name = "qualifications")
    private String qualifications;

    public ProfessionalInformationEntity() {
    }

    public ProfessionalInformationEntity(Long id, String medicalLicenseNumber, BigDecimal yearsOfExperience, String qualifications) {
        this.id = id;
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.qualifications = qualifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public BigDecimal getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(BigDecimal yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}


