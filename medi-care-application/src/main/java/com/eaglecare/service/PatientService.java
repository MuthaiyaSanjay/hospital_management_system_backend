package com.eaglecare.service;

import com.eaglecare.entity.AddressEntity;
import com.eaglecare.entity.ContactEntity;
import com.eaglecare.entity.UserBasicInfoEntity;
import com.eaglecare.model.Contact;
import com.eaglecare.model.Patients;
import com.eaglecare.model.UserBasiInfo;
import com.eaglecare.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PatientService {

    @Autowired
    ContactRepo contactRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UserBasicInfoRepo userBasicInfoRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    DoctorService doctorService;

    @Autowired
    ModelMapper modelMapper;

    public Patients save(Patients patients) {
        System.out.println(patients.toString());
        System.out.println("Enter into the doctor service");
        UserBasicInfoEntity userBasicInfoEntity = modelMapper.map(patients.getBasicInfo(), UserBasicInfoEntity.class);
        ContactEntity contactEntity1 = modelMapper.map(patients.getContact(), ContactEntity.class);
        UserBasicInfoEntity user = doctorService.dtoToEntity(patients.getBasicInfo());
        roleRepo.save(user.getRole());
        userBasicInfoRepo.save(user);
        user.setRole(user.getRole());
        if (contactEntity1 != null) {

            if (contactEntity1.getAddress() != null) {
                AddressEntity addressEntity = contactEntity1.getAddress();
                addressEntity = addressRepo.save(addressEntity);
                contactEntity1.setAddress(addressEntity);
            }
            contactEntity1 = contactRepo.save(contactEntity1);
            user.setContact(contactEntity1);
        }
        UserBasicInfoEntity savedUserBasicEntity = userBasicInfoRepo.save(user);
        UserBasiInfo userBasiInfo = modelMapper.map(savedUserBasicEntity, UserBasiInfo.class);
        Contact contact = modelMapper.map(contactEntity1, Contact.class);
        Patients patients1 = new Patients();
        patients1.setId(userBasiInfo.getUserId());
        patients1.setContact(contact);
        patients1.setBasicInfo(userBasiInfo);
        return patients1;
    }

    public Patients update(String id, Patients patients) {
        System.out.println(patients.toString());
        System.out.println("Enter into the doctor service");

        ContactEntity contactEntity1 = modelMapper.map(patients.getContact(), ContactEntity.class);
        UserBasicInfoEntity user = doctorService.dtoToEntity(patients.getBasicInfo());
        user.getRole().setId(Long.parseLong(patients.getBasicInfo().getRole().getId()));
        roleRepo.save(user.getRole());
        user.setUserId(Long.parseLong(id));
        userBasicInfoRepo.save(user);
        user.setRole(user.getRole());
        contactEntity1.setId(Long.parseLong(patients.getContact().getId()));
        if (contactEntity1 != null) {

            if (contactEntity1.getAddress() != null) {
                AddressEntity addressEntity = contactEntity1.getAddress();
                addressEntity = addressRepo.save(addressEntity);
                contactEntity1.setAddress(addressEntity);
            }
            contactEntity1 = contactRepo.save(contactEntity1);
            user.setContact(contactEntity1);
        }
        UserBasicInfoEntity savedUserBasicEntity = userBasicInfoRepo.save(user);
        UserBasiInfo userBasiInfo = modelMapper.map(savedUserBasicEntity, UserBasiInfo.class);
        Contact contact = modelMapper.map(contactEntity1, Contact.class);
        Patients patients1 = new Patients();
        patients1.setId(userBasiInfo.getUserId());
        patients1.setContact(contact);
        patients1.setBasicInfo(userBasiInfo);
        return patients1;
    }

    public Patients getByPatientId(String id) {
        Optional<UserBasicInfoEntity> optionaPatient = userBasicInfoRepo.findById(Long.parseLong(id));

        if (optionaPatient.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find Patient id is " + id);
        }
        UserBasicInfoEntity user = optionaPatient.get();
        Patients patients = new Patients();
        patients.setId(optionaPatient.get().getUserId().toString());
        patients.setBasicInfo(modelMapper.map(user, UserBasiInfo.class));
        if(!((optionaPatient.get().getContact()) == null)){
            patients.setContact(modelMapper.map(optionaPatient.get().getContact(), Contact.class));
        }
        System.out.println("values " + patients);
        return patients;
    }

    public List<Patients> getAllPatients(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        // Fetch only users with the "patient" role
        Page<UserBasicInfoEntity> patientPage = userBasicInfoRepo.findByRoleName("Patient", pageable);

        return patientPage.getContent().stream()
                .map(this::convertToPatient)
                .collect(Collectors.toList());
    }


    private Patients convertToPatient(UserBasicInfoEntity userBasicInfoEntity) {
        Patients patients = new Patients();

        patients.setId(userBasicInfoEntity.getUserId().toString());
        patients.setBasicInfo(modelMapper.map(userBasicInfoEntity, UserBasiInfo.class));

        ContactEntity contactEntity = userBasicInfoEntity.getContact();
        if (contactEntity != null) {
            patients.setContact(modelMapper.map(contactEntity, Contact.class));
        } else {
            patients.setContact(null);
        }
        return patients;
    }

    @Transactional
    public void deleteById(String id) {
        Optional<UserBasicInfoEntity> optionalPatient = userBasicInfoRepo.findById(Long.parseLong(id));
        if (optionalPatient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with id: " + id);
        }

        UserBasicInfoEntity patient = optionalPatient.get();
        if (patient.getContact() != null) {
            ContactEntity contactEntity = patient.getContact();
            if (contactEntity.getAddress() != null) {
                contactRepo.save(contactEntity);
                addressRepo.delete(contactEntity.getAddress());
            }
            contactRepo.delete(contactEntity);
        }
        if (patient.getRole() != null) {
            roleRepo.delete(patient.getRole());
        }
        userBasicInfoRepo.delete(patient);
    }
}
