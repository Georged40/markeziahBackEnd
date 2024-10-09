package com.AsandeWeb.TestApp.repository;

import com.AsandeWeb.TestApp.model.Invoice;
import com.AsandeWeb.TestApp.model.Stock_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepo extends JpaRepository<Invoice,String> {

    public final List<Invoice> invoiceList = new ArrayList<>();

    public List<Invoice> findAll();
    public Invoice findByInvoiceNumber(String invoiceNumber);
    public List<Invoice> findBySupplierName(String supplierName);

}
