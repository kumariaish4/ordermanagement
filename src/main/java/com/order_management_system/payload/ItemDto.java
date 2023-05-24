package com.order_management_system.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.order_management_system.entities.Customer;
import com.order_management_system.entities.Items;
import lombok.Data;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private double price;


    public ItemDto(Items item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price=item.getPrice();

    }
    public ItemDto(){

    }
    @JsonCreator
    public ItemDto(@JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("price") double price){
        this.id = id;
        this.name = name;
       this.price=price;
    }

}
