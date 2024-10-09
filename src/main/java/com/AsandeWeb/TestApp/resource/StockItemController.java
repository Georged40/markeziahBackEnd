package com.AsandeWeb.TestApp.resource;

import com.AsandeWeb.TestApp.model.Stock_Item;
import com.AsandeWeb.TestApp.model.response.Response;
import com.AsandeWeb.TestApp.service.serviceImplementation.stockItemServiceImplementation;
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
@RequestMapping("/stockItems")
@RequiredArgsConstructor
public class StockItemController {
    private final stockItemServiceImplementation stockItemserviceImplementation;



    @GetMapping("")
    public ResponseEntity<Response> getStock_Items() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.list(100)))
                        .message("stockItems retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @GetMapping("getAll")
    public ResponseEntity<Response> getAllStockItems() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.list(100)))
                        .message("stockItems retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @GetMapping("/gafsql")
    public ResponseEntity<Response> getAllItemsFromSQL() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.getAllFromSQL()))
                        .message("stockItems retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }


    @GetMapping("/findByIdFromMYSQL/{id}")
    public ResponseEntity<Response> getByIdFromSQl(@PathVariable  int id){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.findByIDFromSQL(id)))
                        .message("stockItems retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @PostMapping("/save/received/{id}")
    public ResponseEntity<Response> receivedStockItem(@PathVariable int id,@RequestBody @Valid Stock_Item stockItem) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.increasedQty(id,stockItem)))
                        .message("stockItem received")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }
    @PostMapping("/save/counted/{id}")
    public ResponseEntity<Response> stockTake(@PathVariable int id,@RequestBody @Valid Stock_Item stockItem) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.stockCount(id,stockItem)))
                        .message("stockItem received")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }



    @PostMapping("/save/issued/{id}")
    public ResponseEntity<Response> issueStockItem(@PathVariable int id,@RequestBody @Valid Stock_Item stockItem) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.reducedQty(id,stockItem)))
                        .message("stockItem Issued")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }



    @PostMapping("/save")
    public ResponseEntity<Response> saveStockItem(@RequestBody @Valid Stock_Item stockItem) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.create(stockItem)))
                        .message("stockItem saved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @PutMapping("/modify")
    public ResponseEntity<Response> modifyItem(@RequestBody @Valid Stock_Item stockItem) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.checkBeforeModify(stockItem)))
                        .message("item modified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @PostMapping("/save/orderItem")
    public ResponseEntity<Response> orderStockItem(@RequestBody @Valid Stock_Item stockItem) throws IOException {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Stock_Item", stockItemserviceImplementation.orderStockItem(stockItem)))
                        .message("Order Item saved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @GetMapping("/get/{id}")
        public ResponseEntity<Response> getStockItem(@PathVariable  int id) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(LocalDateTime.now())
                            .data(Map.of("Stock_Item", stockItemserviceImplementation.get(id)))
                            .message("stockItem retrieved")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteStockItem(@PathVariable  int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("deleted", stockItemserviceImplementation.delete(id)))
                        .message("stockItem deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

}
