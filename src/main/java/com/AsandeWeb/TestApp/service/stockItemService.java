package com.AsandeWeb.TestApp.service;

import com.AsandeWeb.TestApp.model.Invoice;
import com.AsandeWeb.TestApp.model.Stock_Item;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface stockItemService {
    Stock_Item create(Stock_Item stockItem);
     Stock_Item update(Integer id,Stock_Item stockItem);
   public Stock_Item findByIDFromSQL(@Param("id") Integer id);
   Collection<Stock_Item> list(int limit);
    Collection<Invoice> invoiceList(int limit);
    Collection<Invoice> invoiceListBySupplier(@Param("supplierName") String supplierName);
List <Stock_Item> getAllFromSQL();
    Stock_Item get(Integer id);
    Boolean delete(Integer id);
    Stock_Item modify (Stock_Item item);
    Boolean delete(String description);
}
