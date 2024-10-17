package com.eaglecare.service;

import com.eaglecare.entity.*;
import com.eaglecare.exception.CustomException;
import com.eaglecare.model.*;
import com.eaglecare.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    ContactRepo contactRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UserBasicInfoRepo userBasicInfoRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    EmploymentDetailsRepo employmentDetailsRepo;

    @Autowired
    PayRollRepo payRollRepo;

    @Autowired
    ProfessionalInformationRepo professionalInformationRepo;

    @Autowired
    ModelMapper modelMapper;

    public Doctor saveDoctor(Doctor doctor) {
        try {
            System.out.println("Enter into the doctor service");
            DoctorEntity doctorEntity = modelMapper.map(doctor, DoctorEntity.class);
            UserBasicInfoEntity user = dtoToEntity(doctor.getBasicInfo());
            roleRepo.save(user.getRole());
            if (!(user.getEmploymentDetails().getPayroll() == null)) {
                payRollRepo.save(user.getEmploymentDetails().getPayroll());
            }
            employmentDetailsRepo.save(user.getEmploymentDetails());
            userBasicInfoRepo.save(user);
            user.setEmploymentDetails(user.getEmploymentDetails());
            user.setRole(user.getRole());
            user.getEmploymentDetails().setPayroll(user.getEmploymentDetails().getPayroll());
            doctorEntity.setBasicInfo(user);

            if (doctorEntity.getProfessionalInformation() != null) {
                ProfessionalInformationEntity professionalInfoEntity = doctorEntity.getProfessionalInformation();
                professionalInfoEntity = professionalInformationRepo.save(professionalInfoEntity);
                doctorEntity.setProfessionalInformation(professionalInfoEntity);
            }
            if (doctorEntity.getContact() != null) {
                ContactEntity contactEntity = doctorEntity.getContact();
                if (contactEntity.getAddress() != null) {
                    AddressEntity addressEntity = contactEntity.getAddress();
                    addressEntity = addressRepo.save(addressEntity);
                    contactEntity.setAddress(addressEntity);
                }
                contactEntity = contactRepo.save(contactEntity);
                doctorEntity.setContact(contactEntity);
            }
            DoctorEntity savedDoctorEntity = doctorRepo.save(doctorEntity);
            return modelMapper.map(savedDoctorEntity, Doctor.class);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(" Exception " + e.getMessage());
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Exception " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    public Doctor updateDoctor(String id, Doctor doctor) {
        System.out.println("Enter into the doctor service");
        Optional<DoctorEntity> entity = doctorRepo.findById(Long.parseLong(id));
        if (!entity.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find doctor id is " + id);
        }
        DoctorEntity doctorEntity = modelMapper.map(doctor, DoctorEntity.class);
        UserBasicInfoEntity user = dtoToEntity(doctor.getBasicInfo());
        user.getRole().setId(Long.parseLong(doctor.getBasicInfo().getRole().getId()));
        roleRepo.save(user.getRole());
        user.getEmploymentDetails().getPayroll().setId(Long.parseLong(doctor.getBasicInfo().getEmploymentDetails().getPayroll().getId()));
        payRollRepo.save(user.getEmploymentDetails().getPayroll());
        user.getEmploymentDetails().setId(Long.parseLong(doctor.getBasicInfo().getEmploymentDetails().getId()));
        employmentDetailsRepo.save(user.getEmploymentDetails());
        user.setUserId(Long.parseLong(doctor.getBasicInfo().getUserId()));
        userBasicInfoRepo.save(user);
        user.setEmploymentDetails(user.getEmploymentDetails());
        user.setRole(user.getRole());
        user.getEmploymentDetails().setPayroll(user.getEmploymentDetails().getPayroll());
        doctorEntity.setBasicInfo(user);

        if (doctorEntity.getProfessionalInformation() != null) {
            ProfessionalInformationEntity professionalInfoEntity = doctorEntity.getProfessionalInformation();
            professionalInfoEntity = professionalInformationRepo.save(professionalInfoEntity);
            doctorEntity.setProfessionalInformation(professionalInfoEntity);
        }
        if (doctorEntity.getContact() != null) {
            ContactEntity contactEntity = doctorEntity.getContact();
            if (contactEntity.getAddress() != null) {
                AddressEntity addressEntity = contactEntity.getAddress();
                addressEntity = addressRepo.save(addressEntity);
                contactEntity.setAddress(addressEntity);
            }
            contactEntity = contactRepo.save(contactEntity);
            doctorEntity.setContact(contactEntity);
        }
        DoctorEntity savedDoctorEntity = doctorRepo.save(doctorEntity);
        Doctor responseDoctor = modelMapper.map(savedDoctorEntity, Doctor.class);
        return responseDoctor;
    }

    public UserBasicInfoEntity dtoToEntity(@Valid UserBasiInfo basicInfo) {
        UserBasicInfoEntity userBasicEntity = new UserBasicInfoEntity();

        userBasicEntity.setFirstName(basicInfo.getFirstName());
        userBasicEntity.setLastName(basicInfo.getLastName());

        if (basicInfo.getRole() != null) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setDescription(basicInfo.getRole().getDescription());
            roleEntity.setName(basicInfo.getRole().getName());
            userBasicEntity.setRole(roleEntity);
        }

        userBasicEntity.setGender(GenderEnum.valueOf(basicInfo.getGender().name()));

        if (basicInfo.getEmploymentDetails() != null) {
            EmploymentDetailsEntity employmentDetailsEntity = new EmploymentDetailsEntity();
            employmentDetailsEntity.setJoiningDate(basicInfo.getEmploymentDetails().getJoiningDate());
            employmentDetailsEntity.setRelievingDate(basicInfo.getEmploymentDetails().getRelievingDate());
            employmentDetailsEntity.setDepartment(basicInfo.getEmploymentDetails().getDepartment());
            employmentDetailsEntity.setSalary(basicInfo.getEmploymentDetails().getSalary());

            if (basicInfo.getEmploymentDetails().getPayroll() != null) {
                PayRollEntity payrollEntity = new PayRollEntity();
                payrollEntity.setDeductions(basicInfo.getEmploymentDetails().getPayroll().getDeductions());
                payrollEntity.setPaymentDate(basicInfo.getEmploymentDetails().getPayroll().getPaymentDate());
                payrollEntity.setGrossAmount(basicInfo.getEmploymentDetails().getPayroll().getGrossAmount());
                payrollEntity.setNetAmount(basicInfo.getEmploymentDetails().getPayroll().getNetAmount());
                employmentDetailsEntity.setPayroll(payrollEntity);
            }
            userBasicEntity.setEmploymentDetails(employmentDetailsEntity);
        }
        return userBasicEntity;
    }

    public Doctor getDoctorsById(long doctorId) {
        Optional<DoctorEntity> optionalDoctor = doctorRepo.findById(doctorId);

        if (!optionalDoctor.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find doctor id is " + doctorId);
        }
        DoctorEntity doctor = optionalDoctor.get();
        Doctor doc = new Doctor();
        UserBasicInfoEntity user = doctor.getBasicInfo();
        if (user == null) {
            throw new IllegalStateException("UserBasicInfoEntity is null for doctor with id: " + doctorId);
        }
        EmploymentDetailsEntity employmentDetails = user.getEmploymentDetails();
        if (employmentDetails == null) {
            throw new IllegalStateException("EmploymentDetailsEntity is null for user of doctor with id: " + doctorId);
        }
        PayRollEntity payRoll = employmentDetails.getPayroll();

        if (doc.getBasicInfo() == null) {
            doc.setBasicInfo(new UserBasiInfo());
        }
        doc.setId(optionalDoctor.get().getId().toString());
        doc.getBasicInfo().setEmploymentDetails(modelMapper.map(employmentDetails, EmploymentDetails.class));
        doc.getBasicInfo().getEmploymentDetails().setPayroll(modelMapper.map(payRoll, PayRoll.class));
        doc.setBasicInfo(modelMapper.map(user, UserBasiInfo.class));
        doc.setProfessionalInformation(modelMapper.map(optionalDoctor.get().getProfessionalInformation(), ProfessionalInformation.class));
        doc.setContact(modelMapper.map(optionalDoctor.get().getContact(), Contact.class));
        System.out.println("values " + doc);
        return doc;
    }

    public void deleteDoctorId(long doctorId) {
        userBasicInfoRepo.deleteById(doctorId);
        Optional<UserBasicInfoEntity> doctor = userBasicInfoRepo.findById(doctorId);
        System.out.println(" doctors " + doctor);
    }

    public List<Doctor> getAllDoctors(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<DoctorEntity> doctorPage = doctorRepo.findAll(pageable);
        List<Doctor> doctors = doctorPage.getContent().stream()
                .map(this::convertToDoctor)
                .collect(Collectors.toList());
        return doctors;
    }

    private Doctor convertToDoctor(DoctorEntity doctorEntity) {
        Doctor doc = new Doctor();
        UserBasicInfoEntity user = doctorEntity.getBasicInfo();

        if (user == null) {
            throw new IllegalStateException("UserBasicInfoEntity is null for doctor with id: " + doctorEntity.getId());
        }

        EmploymentDetailsEntity employmentDetails = user.getEmploymentDetails();
        if (employmentDetails == null) {
            throw new IllegalStateException("EmploymentDetailsEntity is null for user of doctor with id: " + doctorEntity.getId());
        }
        PayRollEntity payRoll = employmentDetails.getPayroll();
        doc.setId(doctorEntity.getId().toString());
        doc.setBasicInfo(modelMapper.map(user, UserBasiInfo.class));
        doc.getBasicInfo().setEmploymentDetails(modelMapper.map(employmentDetails, EmploymentDetails.class));
        doc.getBasicInfo().getEmploymentDetails().setPayroll(modelMapper.map(payRoll, PayRoll.class));
        doc.setProfessionalInformation(modelMapper.map(doctorEntity.getProfessionalInformation(), ProfessionalInformation.class));
        doc.setContact(modelMapper.map(doctorEntity.getContact(), Contact.class));
        return doc;
    }
}
