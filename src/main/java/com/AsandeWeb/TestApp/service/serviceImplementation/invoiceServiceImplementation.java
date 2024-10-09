package com.AsandeWeb.TestApp.service.serviceImplementation;


import com.AsandeWeb.TestApp.model.Invoice;
import com.AsandeWeb.TestApp.repository.InvoiceRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
@Slf4j
@Transactional

public class invoiceServiceImplementation implements invoiceService {
    private final InvoiceRepo InvoiceRepo;
    @Override
    public Invoice create(Invoice invoice) {
        return InvoiceRepo.save(invoice);
    }

    @Override
    public Optional<Invoice> findByInvoiceNumber(String invoiceNo) {
        return Optional.ofNullable(InvoiceRepo.findByInvoiceNumber(invoiceNo));
    }
    @Override
    public Collection<Invoice> invoiceList(int limit) {
        return InvoiceRepo.findAll();
    }
    @Override
    public List<Invoice> invoiceListBySupplier(String supplierName) {
        List <Invoice> invoiceFromThisSupplier = InvoiceRepo.findBySupplierName(supplierName).stream().toList();
        return invoiceFromThisSupplier;
    }
    @Override
    public Invoice getInvoice(String invoiceNumber) {
        return InvoiceRepo.findByInvoiceNumber(invoiceNumber);
    }
    @Override
    public Boolean delete(String invoiceNumber) {
        return null;
    }

}
