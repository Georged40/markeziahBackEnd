package com.AsandeWeb.TestApp.repository;

import com.AsandeWeb.TestApp.model.Stock_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface stockItemRepo extends JpaRepository<Stock_Item,Integer> {

    public final List<Stock_Item> stockItemsList = new ArrayList<>();

    public List<Stock_Item> findAll();
    @Query(value = "select * from storesdb.stock_item where id = :idNum ", nativeQuery = true)
    public Stock_Item findByIDFromSQL(@Param("idNum") Integer id);
    Stock_Item findByDescription(String description);


}
