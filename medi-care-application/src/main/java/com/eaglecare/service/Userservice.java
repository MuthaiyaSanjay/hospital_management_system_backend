package com.medicare.service;

import com.medicare.entity.*;
import com.medicare.model.*;
import com.medicare.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class Userservice {
    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserBasicInfoRepo userBasicInfoRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private EmploymentDetailsRepo employmentDetailsRepo;

    @Autowired
    DoctorService doctorService;

    @Autowired
    private PayRollRepo payRollRepo;

    @Autowired
    private ProfessionalInformationRepo professionalInformationRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UserPayload save(UserPayload userPayload) {
        System.out.println(userPayload.toString());
        System.out.println("Enter into the doctor service");
        UserBasicInfoEntity userBasicInfoEntity = modelMapper.map(userPayload, UserBasicInfoEntity.class);

        UserBasicInfoEntity user = doctorService.dtoToEntity(userPayload.getBasicInfo());
        roleRepo.save(user.getRole());
        payRollRepo.save(user.getEmploymentDetails().getPayroll());
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
        UserPayload userPayload1 = modelMapper.map(savedUserBasicEntity, UserPayload.class);
        return userPayload1;
    }

    public UserPayload update(String id, UserPayload userPayload) {
        System.out.println(userPayload.toString());
        System.out.println("Enter into the update doctor service");
        Optional<UserBasicInfoEntity> entity = userBasicInfoRepo.findById(Long.parseLong(id));
        if (!entity.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find user id is " + id);
        }
        UserBasicInfoEntity doctorEntity = modelMapper.map(userPayload, UserBasicInfoEntity.class);
        UserBasicInfoEntity user = doctorService.dtoToEntity(userPayload.getBasicInfo());
        user.getRole().setId(Long.parseLong(userPayload.getBasicInfo().getRole().getId()));
        roleRepo.save(user.getRole());
        user.getEmploymentDetails().getPayroll().setId(Long.parseLong(userPayload.getBasicInfo().getEmploymentDetails().getPayroll().getId()));
        payRollRepo.save(user.getEmploymentDetails().getPayroll());
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
        UserPayload userPayload1 = modelMapper.map(savedUserBasicEntity, UserPayload.class);
        return userPayload1;
    }

    public UserPayload getUserById(long userId) {
        Optional<UserBasicInfoEntity> optionalUser = userBasicInfoRepo.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find User id is " + userId);
        }
        UserBasicInfoEntity userBasicInfo = optionalUser.get();
        UserPayload doc = new UserPayload();
        UserBasicInfoEntity user = userBasicInfo;
        if (user == null) {
            throw new IllegalStateException("UserBasicInfoEntity is null for user with id: " + user.getUserId());
        }
        EmploymentDetailsEntity employmentDetails = user.getEmploymentDetails();
        if (employmentDetails == null) {
            throw new IllegalStateException("EmploymentDetailsEntity is null for user of doctor with id: " + employmentDetails.getId());
        }
        PayRollEntity payRoll = employmentDetails.getPayroll();

        if (doc.getBasicInfo() == null) {
            doc.setBasicInfo(new UserBasiInfo());
        }
        doc.setUserId(optionalUser.get().getUserId().toString());
        doc.getBasicInfo().setEmploymentDetails(modelMapper.map(employmentDetails, EmploymentDetails.class));
        doc.getBasicInfo().getEmploymentDetails().setPayroll(modelMapper.map(payRoll, PayRoll.class));
        doc.setBasicInfo(modelMapper.map(user, UserBasiInfo.class));
        doc.setContact(modelMapper.map(optionalUser.get().getContact(), Contact.class));
        System.out.println("values " + doc);
        return doc;
    }

    public List<UserPayload> getAllUsers(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<UserBasicInfoEntity> userPage = userBasicInfoRepo.findAll(pageable);
        List<UserPayload> users = userPage.getContent().stream()
                .map(this::convertToUsers)
                .collect(Collectors.toList());
        return users;
    }

    private UserPayload convertToUsers(UserBasicInfoEntity userBasicInfo) {
        UserPayload userPayload = new UserPayload();
        Optional<UserBasicInfoEntity> optionalUser = userBasicInfoRepo.findById(userBasicInfo.getUserId());
        UserBasicInfoEntity user = optionalUser.get();
        if (user == null) {
            throw new IllegalStateException("UserBasicInfoEntity is null for doctor with id: " + userBasicInfo.getUserId());
        }

        EmploymentDetailsEntity employmentDetails = user.getEmploymentDetails();
        if (employmentDetails == null) {
            throw new IllegalStateException("EmploymentDetailsEntity is null for user of doctor with id: " + userBasicInfo.getUserId());
        }

        PayRollEntity payRoll = employmentDetails.getPayroll();
        userPayload.setUserId(userBasicInfo.getUserId().toString());
        userPayload.setBasicInfo(modelMapper.map(user, UserBasiInfo.class));
        userPayload.getBasicInfo().setEmploymentDetails(modelMapper.map(employmentDetails, EmploymentDetails.class));
        userPayload.getBasicInfo().getEmploymentDetails().setPayroll(modelMapper.map(payRoll, PayRoll.class));
        if (!(user.getContact() == null)) {
            userPayload.setContact(modelMapper.map(user.getContact(), Contact.class));
        } else {
            Optional<DoctorEntity> doctor = doctorRepo.findById(user.getUserId());
            DoctorEntity doctor1 = doctor.get();
            ContactEntity contactEntity = doctor1.getContact();
            userPayload.setContact(modelMapper.map(contactEntity, Contact.class));
        }
        return userPayload;
    }
}
