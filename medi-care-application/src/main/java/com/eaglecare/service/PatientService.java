package com.medicare.service;

import com.medicare.entity.AddressEntity;
import com.medicare.entity.ContactEntity;
import com.medicare.entity.UserBasicInfoEntity;
import com.medicare.model.Contact;
import com.medicare.model.Patients;
import com.medicare.model.UserBasiInfo;
import com.medicare.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PatientService {

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
}
