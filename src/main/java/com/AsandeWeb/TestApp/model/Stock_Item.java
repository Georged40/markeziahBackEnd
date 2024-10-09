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
public class Stock_Item {
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
   @Column(unique = true)
   @NotEmpty(message = "Description cannot be empty")
    String description;
    String uom;
    double qty;
    double price;
    double value;
    double parLevel;
    double orderQty;
    double purchased;
    double purchasedValue;
    double counted;
    double countedValue;
    double consumption;
    double consumptionValue;


    public double getQty() {
        return qty;
    }
    public Stock_Item(Stock_Item item){
        this.setDescription(item.getDescription());
        this.setParLevel(item.getParLevel());
        this.setUom(item.getUom());
        this.setPrice(item.getPrice());
        this.setOrderQty(item.getOrderQty());
        this.setQty(item.getQty());
        this.setValue(item.getValue());
}


}
