package com.medicare.service;

import com.medicare.entity.AppointmentEntity;
import com.medicare.entity.DoctorEntity;
import com.medicare.entity.UserBasicInfoEntity;
import com.medicare.model.Appointment;
import com.medicare.model.Doctor;
import com.medicare.model.Patients;
import com.medicare.model.UserBasiInfo;
import com.medicare.repository.AppointmentRepo;
import com.medicare.repository.DoctorRepo;
import com.medicare.repository.UserBasicInfoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private UserBasicInfoRepo userBasicInfoRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    public Appointment createAppointment(Appointment appointment) {
        System.out.println("Creating Appointment: " + appointment);

        Optional<UserBasicInfoEntity> userOptional = userBasicInfoRepo.findById(Long.parseLong(appointment.getPatient().getId()));
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("Patient not found with ID: " + appointment.getPatient().getBasicInfo().getUserId());
        }
        UserBasicInfoEntity userEntity = userOptional.get();

        // Retrieve and validate doctor
        Optional<DoctorEntity> doctorOptional = doctorRepo.findById(Long.parseLong(appointment.getDoctor().getId()));
        if (!doctorOptional.isPresent()) {
            throw new EntityNotFoundException("Doctor not found with ID: " + appointment.getDoctor().getId());
        }
        DoctorEntity doctorEntity = doctorOptional.get();
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        if (!(appointment.getId() == null)){
            appointmentEntity.setId(Long.parseLong(appointment.getId()));
        }
        appointmentEntity.setAppointmentDate(appointment.getAppointmentDate());
        appointmentEntity.setAppointmentTime(appointment.getAppointmentTime());
        appointmentEntity.setPatient(userEntity);
        appointmentEntity.setDoctor(doctorEntity);
        appointmentEntity = appointmentRepo.save(appointmentEntity);
        Appointment appointment1 =new Appointment();
        appointment1 = modelMapper.map(appointmentEntity, Appointment.class);
        return appointment1;
    }
    public Appointment findById(Long id) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepo.findById(id);
        if (appointmentOptional.isEmpty()) {
            throw new EntityNotFoundException("Appointment not found with ID: " + id);
        }
        AppointmentEntity appointmentEntity = appointmentOptional.get();
        System.out.println("modelMapper Values : " +appointmentEntity.toString());
        return modelMapper.map(appointmentEntity, Appointment.class);
    }

    @Autowired
    Userservice userservice;

    public void deleteById(long id) {
        if (!appointmentRepo.existsById(id)) {
            throw new EntityNotFoundException("Appointment not found with ID: " + id);
        }
        appointmentRepo.deleteById(id);
        System.out.println("Deleted appointment with ID: " + id);
    }
    public List<Appointment> getAllAppointments(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<AppointmentEntity> appointmentPage = appointmentRepo.findAll(pageable);
        List<Appointment> appointments = appointmentPage.getContent().stream()
                .map(this::convertToAppointment)
                .collect(Collectors.toList());
        return appointments;
    }

    private Appointment convertToAppointment(AppointmentEntity appointmentEntity) {
        Appointment appointment = new Appointment();

        // Map appointment fields
        appointment.setId(appointmentEntity.getId().toString());
        appointment.setAppointmentDate(appointmentEntity.getAppointmentDate());
        appointment.setAppointmentTime(appointmentEntity.getAppointmentTime());

        // Convert and map patient details
        UserBasicInfoEntity patient = appointmentEntity.getPatient();
        if (patient == null) {
            throw new IllegalStateException("UserBasicInfoEntity is null for appointment with id: " + appointmentEntity.getId());
        }

        appointment.setPatient(modelMapper.map(patient, Patients.class));

        // Convert and map doctor details
        DoctorEntity doctor = appointmentEntity.getDoctor();
        if (doctor == null) {
            throw new IllegalStateException("DoctorEntity is null for appointment with id: " + appointmentEntity.getId());
        }
        appointment.setDoctor(modelMapper.map(doctor, Doctor.class));

        return appointment;
    }
}
