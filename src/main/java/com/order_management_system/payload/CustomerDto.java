package com.order_management_system.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.order_management_system.entities.Customer;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CustomerDto {
    private Long id;
    private String name;
@Size(min=10,message="customer phone no should have at least 10 characters")
    private String phone;
    private String address;
    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.address = customer.getAddress();
    }
    public CustomerDto(){

    }
    @JsonCreator
    public CustomerDto(@JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("phone") String phone,
                       @JsonProperty("address") String address){
        this.id = id;
        this.name = name;
        this.phone=phone;
        this.address = address;
    }


}
