package com.AsandeWeb.TestApp.resource;

import com.AsandeWeb.TestApp.model.Invoice;
import com.AsandeWeb.TestApp.model.response.Response;
import com.AsandeWeb.TestApp.service.serviceImplementation.invoiceServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final invoiceServiceImplementation invoiceserviceImplementation;



    @GetMapping("")
    public ResponseEntity<Response> getAllInvoices() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Invoice", invoiceserviceImplementation.invoiceList(100)))
                        .message("invoices retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @GetMapping("getAll")
    public ResponseEntity<Response> fetchAllInvoices() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Invoice", invoiceserviceImplementation.invoiceList(100)))
                        .message("invoices retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping("/findBySupplierName/{supplierName}")
    public ResponseEntity<Response> getBySupplierName(@PathVariable  String supplierName){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Invoice", invoiceserviceImplementation.invoiceListBySupplier(supplierName)))
                        .message("Selected Supplier's Invoice retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }


    @GetMapping("/findByIdInvoiceNumber/{invoiceNumber}")
    public ResponseEntity<Response> getByInvoiceNumber(@PathVariable  String invoiceNumber){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Invoice", invoiceserviceImplementation.getInvoice(invoiceNumber)))
                        .message("Selected Invoice retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Invoice", invoiceserviceImplementation.create(invoice)))
                        .message("Invoice saved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }



    @GetMapping("/get/{invoiceNumber}")
    public ResponseEntity<Response> getInvoice(@PathVariable  String invoiceNumber) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Invoice", invoiceserviceImplementation.getInvoice(invoiceNumber)))
                        .message("Invoice retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteStockItem(@PathVariable  String invoiceNumber) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("deleted", invoiceserviceImplementation.delete(invoiceNumber)))
                        .message("stockItem deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

}

