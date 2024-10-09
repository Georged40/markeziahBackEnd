package com.AsandeWeb.TestApp.service.serviceImplementation;


import com.AsandeWeb.TestApp.model.Invoice;
import com.AsandeWeb.TestApp.model.Stock_Item;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface invoiceService {
    Invoice create(Invoice invoice);
    Optional<Invoice> findByInvoiceNumber(String invoiceNo);
    Collection<Invoice> invoiceList(int limit);
    List <Invoice> invoiceListBySupplier(String supplierName);
    Invoice getInvoice(String invoiceNumber);
    Boolean delete(String invoiceNumber);



}
