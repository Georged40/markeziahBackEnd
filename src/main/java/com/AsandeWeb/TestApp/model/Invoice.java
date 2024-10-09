package com.AsandeWeb.TestApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    String invoiceNumber;
    @NotEmpty(message = "Description cannot be empty")
    String supplierName;
    double totalAmount;
    String invoiceDate;
    String dateProcessed;

    public String checkProcessed(int invoiceNumber) {
        return supplierName;
    }
}
