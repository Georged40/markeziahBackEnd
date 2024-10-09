package com.AsandeWeb.TestApp.service.serviceImplementation;

import com.AsandeWeb.TestApp.model.Invoice;
import com.AsandeWeb.TestApp.model.Stock_Item;
import com.AsandeWeb.TestApp.repository.stockItemRepo;
import com.AsandeWeb.TestApp.service.stockItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@AllArgsConstructor
@Service
@Slf4j
@Transactional

public class stockItemServiceImplementation implements stockItemService {
    private final stockItemRepo stockItemRepo;

    @Override
     public Stock_Item create(Stock_Item stockItem) {
        log.info("Saving new stockItem: {}",stockItem.getDescription());
        return stockItemRepo.save(stockItem);
    }

    @Override
    public Stock_Item update(Integer id, Stock_Item stockItem) {
        return stockItemRepo.save(stockItem);
    }

    @Override
    public Stock_Item findByIDFromSQL(Integer id) {
        return stockItemRepo.findByIDFromSQL(id);
    }

    public Stock_Item reducedQty(Integer id,Stock_Item stockItem){
        log.info("Issuing the stockItems");
        Stock_Item holdQty = stockItemRepo.findById(id).get();
        double consumedValued = holdQty.getConsumption();
        double oldQty = holdQty.getQty();
        double price = holdQty.getPrice();
        double parLevel = holdQty.getParLevel();
        double providedQty = stockItem.getQty();
        double newQty = oldQty - providedQty;
        holdQty.setQty(newQty);
        holdQty.setValue(newQty * price);
        holdQty.setOrderQty(parLevel-newQty);
        holdQty.setConsumption(consumedValued += providedQty);
        holdQty.setConsumptionValue(holdQty.getConsumption()*price);
        stockItem.setQty(stockItem.getQty()-providedQty);
        double forSettingQty = providedQty;
        double priceValue = stockItem.getPrice();

        return stockItemRepo.save(holdQty);
    }

    public Stock_Item increasedQty(Integer id,Stock_Item stockItem){
        log.info("Receiving the stockItems");
        Stock_Item holdQty = stockItemRepo.findById(id).get();
        double price;
        if(stockItem.getPrice() == holdQty.getPrice()){
            log.info("Price Changed");
            price = holdQty.getPrice();
            double purchasedValued = holdQty.getPurchased();
            double oldQty = holdQty.getQty();
            double parLevel = holdQty.getParLevel();
            double providedQty = stockItem.getQty();
            double newQty = oldQty + providedQty;
            holdQty.setPurchased(purchasedValued += providedQty);
            holdQty.setPurchasedValue(holdQty.getPurchased()*price);
            holdQty.setQty(newQty);
            holdQty.setValue(newQty * price);
            holdQty.setOrderQty(parLevel-newQty);
        }else {
            price = stockItem.getPrice();
            double purchasedValued = holdQty.getPurchased();
            double oldQty = holdQty.getQty();
            double parLevel = holdQty.getParLevel();
            double providedQty = stockItem.getQty();
            double newQty = oldQty + providedQty;
            holdQty.setPrice(price);
            holdQty.setPurchased(purchasedValued += providedQty);
            holdQty.setPurchasedValue(holdQty.getPurchased()*price);
            holdQty.setQty(newQty);
            holdQty.setValue(newQty * price);
            holdQty.setOrderQty(parLevel-newQty);
        }

        return stockItemRepo.save(holdQty);
    }

    public Stock_Item stockCount(Integer id,Stock_Item stockItem){
        log.info("saving stock count");
        Stock_Item holdQty = stockItemRepo.findById(id).get();
        holdQty.setCounted(stockItem.getCounted());
        holdQty.setCountedValue(stockItem.getCounted() * holdQty.getPrice());
        return stockItemRepo.save(holdQty);
    }

    public Stock_Item checkBeforeModify(Stock_Item stockItem){
        int id = stockItem.getId();
        log.info("Updating the stockItems");
        Stock_Item holdQty = stockItemRepo.findById(id).get();
        if(holdQty.getQty()>0){
            log.info("modification not possible");
            return stockItemRepo.save(holdQty);
        }else {
            holdQty = stockItem;
            return stockItemRepo.save(holdQty);
        }
    }
    @Override
   public Collection<Stock_Item>list(int limit) {
        log.info("Fetching the stockItems but not supposed to");
        return stockItemRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Collection<Invoice> invoiceList(int limit) {
        return null;
    }

    @Override
    public Collection<Invoice> invoiceListBySupplier(String supplierName) {
        return null;
    }

    public Stock_Item orderStockItem(Stock_Item stockItem) throws IOException {
        String myHeadLine = "Item No" + "Item Description" + "     " + "UOM" + "        " + "Price" + "        " + "Order Qty" + "\n";
        int counter = 1;
        String baseFilePath ="C:\\Users\\ProBook\\Desktop\\Test-App\\src\\main\\java\\com\\AsandeWeb\\TestApp\\Reports\\orders" + counter +".txt";
        File currentFile = new File(baseFilePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile,true));
        Pattern pattern = Pattern.compile("|", Pattern.LITERAL);

        String label5Formatted = String.format("%-15s|", "Item No");
        String label1Formatted = String.format("%-15s|", "Description");
        String label2Formatted = String.format("%-15s|", "UOM");
        String label3Formatted = String.format("%-15s|", "Price");
        String label4Formatted = String.format("%-15s|", "Order Qty");
        String label6Formatted = String.format("%-15s|", "Value");


        String  createdItemsReportFieldHeading = label5Formatted + label1Formatted + label2Formatted + label3Formatted + label4Formatted + label6Formatted;

        while (currentFile.length() == 0){
            writer.write("\n" +createdItemsReportFieldHeading);

        }

        double itemNumberPassed = stockItem.getId();
        String orderItemDescription = stockItem.getDescription();
        double orderQty = stockItem.getQty();
        double price = stockItem.getPrice();
        String UOM = stockItem.getUom();
        double value = price * orderQty;

        String itemNumberFormated = String.format("%-15s|",itemNumberPassed);
        String descriptionFormated = String.format("%-15s|", orderItemDescription);
        String uomFormated = String.format("%-15s|", UOM);
        String priceFormated = String.format("%-15f|", price);
        String orderedQtyFormated = String.format("%-15f|", orderQty);
        String valueFormated = String.format("%-15f|", value);
        String orderedItem = itemNumberFormated + descriptionFormated + uomFormated+ priceFormated + orderedQtyFormated + valueFormated;


        writer.append("\n" + orderedItem);
        writer.close();
        counter++;
        return stockItem;
    }

    @Override
    public List<Stock_Item> getAllFromSQL() {
        return null;
    }

    @Override
    public Stock_Item get(Integer id) {
        log.info("Fetching stockItem with id : {}", id);
        return stockItemRepo.findById(id).get();
    }

    @Override
    public Boolean delete(Integer id) {
        log.info("Deleting the stockItem with ID : {}",id);
        stockItemRepo.deleteById(id);
        return true;
    }

    @Override
    public Stock_Item modify(Stock_Item item) {
        return stockItemRepo.save(item);
    }

    public Boolean delete(String description) {
        log.info("Deleting the stockItem by description : {}",description);
        stockItemRepo.delete(stockItemRepo.findByDescription(description));
        return true;
    }


}
