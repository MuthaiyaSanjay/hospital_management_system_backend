package com.eaglecare.service;

import com.eaglecare.entity.*;
import com.eaglecare.exception.CustomException;
import com.eaglecare.model.*;
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
public class Userservice {

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
    DoctorService doctorService;

    @Autowired
    PayRollRepo payRollRepo;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public UserPayload save(UserPayload userPayload) {
        try {
            System.out.println(userPayload.toString());
            System.out.println("Enter into the doctor service");
            UserBasicInfoEntity userBasicInfoEntity = modelMapper.map(userPayload, UserBasicInfoEntity.class);
            UserBasicInfoEntity user = doctorService.dtoToEntity(userPayload.getBasicInfo());
            roleRepo.save(user.getRole());
            employmentDetailsRepo.save(user.getEmploymentDetails());
            userBasicInfoRepo.save(user);
            user.setEmploymentDetails(user.getEmploymentDetails());
            user.setRole(user.getRole());
            user.getEmploymentDetails().setPayroll(user.getEmploymentDetails().getPayroll());
            if (userBasicInfoEntity.getContact() != null) {
                ContactEntity contactEntity = userBasicInfoEntity.getContact();
                if (contactEntity.getAddress() != null) {
                    AddressEntity addressEntity = contactEntity.getAddress();
                    addressEntity = addressRepo.save(addressEntity);
                    contactEntity.setAddress(addressEntity);
                }
                contactEntity = contactRepo.save(contactEntity);
                user.setContact(contactEntity);
            }
            UserBasicInfoEntity savedUserBasicEntity = userBasicInfoRepo.save(user);
            return modelMapper.map(savedUserBasicEntity, UserPayload.class);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Exception " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Transactional
    public UserPayload update(String id, UserPayload userPayload) {
        System.out.println(userPayload.toString());
        System.out.println("Enter into the update doctor service");
        Optional<UserBasicInfoEntity> entity = userBasicInfoRepo.findById(Long.parseLong(id));
        if (entity.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find user id is " + id);
        }
        UserBasicInfoEntity doctorEntity = modelMapper.map(userPayload, UserBasicInfoEntity.class);
        UserBasicInfoEntity user = doctorService.dtoToEntity(userPayload.getBasicInfo());
        user.getRole().setId(Long.parseLong(userPayload.getBasicInfo().getRole().getId()));
        roleRepo.save(user.getRole());
        if (!(user.getEmploymentDetails().getPayroll() == null)) {
            user.getEmploymentDetails().getPayroll().setId(Long.parseLong(userPayload.getBasicInfo().getEmploymentDetails().getPayroll().getId()));
            payRollRepo.save(user.getEmploymentDetails().getPayroll());
        }
        user.getEmploymentDetails().setId(Long.parseLong(userPayload.getBasicInfo().getEmploymentDetails().getId()));
        employmentDetailsRepo.save(user.getEmploymentDetails());
        user.setUserId(Long.parseLong(id));
        userBasicInfoRepo.save(user);
        user.setEmploymentDetails(user.getEmploymentDetails());
        user.setRole(user.getRole());
        user.getEmploymentDetails().setPayroll(user.getEmploymentDetails().getPayroll());
        if (doctorEntity.getContact() != null) {
            ContactEntity contactEntity = doctorEntity.getContact();
            if (contactEntity.getAddress() != null) {
                AddressEntity addressEntity = contactEntity.getAddress();
                addressEntity = addressRepo.save(addressEntity);
                contactEntity.setAddress(addressEntity);
            }
            contactEntity = contactRepo.save(contactEntity);
            user.setContact(contactEntity);
        }
        UserBasicInfoEntity savedUserBasicEntity = userBasicInfoRepo.save(user);
        return modelMapper.map(savedUserBasicEntity, UserPayload.class);
    }

    @Transactional
    public UserPayload getUserById(long userId) {
        Optional<UserBasicInfoEntity> optionalUser = userBasicInfoRepo.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find User id: " + userId);
        }

        UserBasicInfoEntity userBasicInfo = optionalUser.get();
        UserPayload doc = new UserPayload();

        // Initialize the basic info if it's null
        if (doc.getBasicInfo() == null) {
            doc.setBasicInfo(new UserBasiInfo());
        }

        // Set user ID
        doc.setUserId(userBasicInfo.getUserId().toString());

        // Check if employment details are not null
        EmploymentDetailsEntity employmentDetails = userBasicInfo.getEmploymentDetails();
        if (employmentDetails != null) {
            // Map employment details
            doc.getBasicInfo().setEmploymentDetails(modelMapper.map(employmentDetails, EmploymentDetails.class));

            // Check if payroll is not null before accessing it
            PayRollEntity payRoll = employmentDetails.getPayroll();
            if (payRoll != null) {
                doc.getBasicInfo().getEmploymentDetails().setPayroll(modelMapper.map(payRoll, PayRoll.class));
            }
        }

        // Map basic user info
        doc.setBasicInfo(modelMapper.map(userBasicInfo, UserBasiInfo.class));

        // Check if contact is not null before mapping
        if (optionalUser.get().getContact() != null) {
            doc.setContact(modelMapper.map(optionalUser.get().getContact(), Contact.class));
        }

        System.out.println("values: " + doc);
        return doc;
    }


    public List<UserPayload> getAllUsers(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<UserBasicInfoEntity> userPage = userBasicInfoRepo.findAll(pageable);
        return userPage.getContent().stream()
                .map(this::convertToUsers)
                .collect(Collectors.toList());
    }

    private UserPayload convertToUsers(UserBasicInfoEntity userBasicInfo) {
        UserPayload userPayload = new UserPayload();
        Optional<UserBasicInfoEntity> optionalUser = userBasicInfoRepo.findById(userBasicInfo.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("UserBasicInfoEntity is null for doctor with id: " + userBasicInfo.getUserId());
        }
        UserBasicInfoEntity user = optionalUser.get();

        EmploymentDetailsEntity employmentDetails = user.getEmploymentDetails();
        if (employmentDetails == null) {
            userPayload.setUserId(userBasicInfo.getUserId().toString());
            userPayload.setBasicInfo(modelMapper.map(user, UserBasiInfo.class));
        } else {
            PayRollEntity payRoll = employmentDetails.getPayroll();
            userPayload.setUserId(userBasicInfo.getUserId().toString());
            userPayload.setBasicInfo(modelMapper.map(user, UserBasiInfo.class));
            userPayload.getBasicInfo().setEmploymentDetails(modelMapper.map(employmentDetails, EmploymentDetails.class));
            if (!(payRoll == null)) {
                userPayload.getBasicInfo().getEmploymentDetails().setPayroll(modelMapper.map(payRoll, PayRoll.class));
            }
        }

        if (!(user.getContact() == null)) {
            userPayload.setContact(modelMapper.map(user.getContact(), Contact.class));
        } else {
            Optional<DoctorEntity> doctor = doctorRepo.findById(user.getUserId());
            if (doctor.isPresent()) {
                DoctorEntity doctor1 = doctor.get();
                ContactEntity contactEntity = doctor1.getContact();
                userPayload.setContact(modelMapper.map(contactEntity, Contact.class));
            }
        }
        return userPayload;
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
        if (patient.getEmploymentDetails() != null) {
            EmploymentDetailsEntity employmentDetails = patient.getEmploymentDetails();
            if (employmentDetails.getPayroll() != null) {
                employmentDetailsRepo.save(employmentDetails);
                payRollRepo.delete(employmentDetails.getPayroll());
            }
            employmentDetailsRepo.delete(employmentDetails);
        }
        if (patient.getRole() != null) {
            roleRepo.delete(patient.getRole());
        }
        userBasicInfoRepo.delete(patient);
    }
}
