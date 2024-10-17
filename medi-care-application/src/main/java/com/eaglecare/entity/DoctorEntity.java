package com.eaglecare.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "doctors")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserBasicInfoEntity basicInfo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private ContactEntity contact;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<AppointmentEntity> appointments;

    @OneToOne(cascade = CascadeType.ALL) // Same for other relationships if applicable
    private ProfessionalInformationEntity professionalInformation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserBasicInfoEntity getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(UserBasicInfoEntity basicInfo) {
        this.basicInfo = basicInfo;
    }

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public ProfessionalInformationEntity getProfessionalInformation() {
        return professionalInformation;
    }

    public void setProfessionalInformation(ProfessionalInformationEntity professionalInformation) {
        this.professionalInformation = professionalInformation;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", basicInfo=" + basicInfo +
                ", contact=" + contact +
                ", professionalInformation=" + professionalInformation +
                '}';
    }
}
