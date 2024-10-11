package com.eaglecare.service;

import com.eaglecare.entity.DeductionSummaryEntity;
import com.eaglecare.entity.InvoiceEntity;
import com.eaglecare.entity.PayRollEntity;
import com.eaglecare.exception.CustomException;
import com.eaglecare.model.Invoices;
import com.eaglecare.model.PayRoll;
import com.eaglecare.repository.InvoiceEntityRepo;
import com.eaglecare.repository.PayRollRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    InvoiceEntityRepo invoiceRepository;

    @Autowired
    PayRollRepo payRollRepo;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public Invoices saveInvoice(Invoices invoices) {
        InvoiceEntity invoice = modelMapper.map(invoices, InvoiceEntity.class);
        for (DeductionSummaryEntity deduction : invoice.getDeductionSummary()) {
            deduction.setInvoice(invoice);
        }
        InvoiceEntity savedInvoice = invoiceRepository.save(invoice);
        return modelMapper.map(savedInvoice, Invoices.class);
    }

    public List<Invoices> getAllInvoices(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<InvoiceEntity> invoicePage = invoiceRepository.findAll(pageable);
        return invoicePage.getContent().stream()
                .map(invoice -> modelMapper.map(invoice, Invoices.class))
                .toList();
    }

    public PayRoll getInvoiceById(long id) {
        Optional<PayRollEntity> optionalInvoice = payRollRepo.findById(id);
        return optionalInvoice.map(invoice -> modelMapper.map(invoice, PayRoll.class)).orElse(null);
    }

    @Transactional
    public Invoices updateInvoice(long id, Invoices invoices) {
        if (!invoiceRepository.existsById(id)) {
            throw new CustomException(HttpStatus.NOT_FOUND, id + " Not found Invoice");
        }
        InvoiceEntity invoiceToUpdate = modelMapper.map(invoices, InvoiceEntity.class);
        invoiceToUpdate.setId(id);
        for (DeductionSummaryEntity deduction : invoiceToUpdate.getDeductionSummary()) {
            deduction.setInvoice(invoiceToUpdate);
        }
        InvoiceEntity updatedInvoice = invoiceRepository.save(invoiceToUpdate);
        return modelMapper.map(updatedInvoice, Invoices.class);
    }

    @Transactional
    public boolean deleteInvoice(long id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    @Override
//    public ResponseEntity<Invoices> getInvoicesById(Long id) {
//            InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException("Invoice not found with id: " + id));
//            Invoices invoiceResponse = modelMapper.map(invoiceEntity, Invoices.class);
//            return modelMapper.map(invoiceResponse, Invoices.class);
//    }

}
