package com.eaglecare.service;

import com.eaglecare.entity.DoctorEntity;
import com.eaglecare.entity.InvoiceEntity;
import com.eaglecare.entity.PaymentsEntity;
import com.eaglecare.exception.CustomException;
import com.eaglecare.exception.ResourceNotFoundException;
import com.eaglecare.model.Payments;
import com.eaglecare.repository.InvoiceEntityRepo;
import com.eaglecare.repository.PaymentsEntityRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PaymentsEntityRepo paymentsEntityRepo;

    @Autowired
    InvoiceEntityRepo invoiceEntityRepo;

    public Payments createPayment(Payments payments) {
        Optional<InvoiceEntity> invoice = invoiceEntityRepo.
                findById(Long.parseLong(payments.getInvoiceId().toString()));
        if(invoice.isEmpty()){
            throw new CustomException(HttpStatus.NOT_FOUND, "Invoice Id is NOT FOUND");
        }
        PaymentsEntity payments1 = modelMapper.map(payments, PaymentsEntity.class);
        PaymentsEntity payments2 = paymentsEntityRepo.save(payments1);
        return modelMapper.map(payments2, Payments.class);
    }

    public List<Payments> getAllPayments(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<PaymentsEntity> paymentsEntities = paymentsEntityRepo.findAll(pageable);
        return paymentsEntities.stream()
                .map(entity -> modelMapper.map(entity, Payments.class))
                .collect(Collectors.toList());
    }
    
    public Payments getPaymentById(Long id) {
        PaymentsEntity paymentsEntity = paymentsEntityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return modelMapper.map(paymentsEntity, Payments.class);
    }

    @Transactional
    public Payments patchPayment(Long id, Payments updates) {
        PaymentsEntity existingEntity = paymentsEntityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        // Update only the fields that are present in the 'updates' object
        if (updates.getAmount() != null) {
            existingEntity.setAmount(updates.getAmount());
        }
        if (updates.getPaymentDate() != null) {
            existingEntity.setPaymentDate(updates.getPaymentDate());
        }
        if (updates.getPaymentDueDate() != null) {
            existingEntity.setPaymentDueDate(updates.getPaymentDueDate());
        }
        if (updates.getStatus() != null) {
            existingEntity.setStatus(PaymentsEntity.StatusEnum.valueOf(updates.getStatus().getValue()));
        }
        if (updates.getInvoiceId() != null) {
            existingEntity.setInvoiceId(updates.getInvoiceId().doubleValue());
        }

        PaymentsEntity updatedEntity = paymentsEntityRepo.save(existingEntity);
        return modelMapper.map(updatedEntity, Payments.class);
    }

    @Transactional
    public Payments updatePayment(Long id, Payments payments) {
        Optional<InvoiceEntity> invoice = invoiceEntityRepo.
                findById(Long.parseLong(payments.getInvoiceId().toString()));
        if(invoice.isEmpty()){
            throw new CustomException(HttpStatus.NOT_FOUND, "Invoice Id is NOT FOUND");
        }
        PaymentsEntity existingEntity = paymentsEntityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        existingEntity.setAmount(payments.getAmount());
        existingEntity.setPaymentDate(payments.getPaymentDate());
        existingEntity.setPaymentDueDate(payments.getPaymentDueDate());
        existingEntity.setStatus(PaymentsEntity.StatusEnum.valueOf(payments.getStatus().getValue()));
        existingEntity.setInvoiceId(payments.getInvoiceId().doubleValue());
        PaymentsEntity updatedEntity = paymentsEntityRepo.save(existingEntity);
        return modelMapper.map(updatedEntity, Payments.class);
    }

    @Transactional
    public void deletePayment(Long id) {
        PaymentsEntity existingEntity = paymentsEntityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        paymentsEntityRepo.delete(existingEntity);
    }


    public Payments getPaymentsByInvoices(Long invoiceId) {
        PaymentsEntity payments = paymentsEntityRepo.findByInvoiceId(invoiceId);
        return modelMapper.map(payments, Payments.class);
    }
}
