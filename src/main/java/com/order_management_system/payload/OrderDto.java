package com.order_management_system.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.order_management_system.entities.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
        private Long id;
        private Long customerId;
        private Long userId;
        private LocalDateTime orderTime;
        private String item;
        private Double price;


        public OrderDto(Order order) {
                this.id = order.getId();
                this.customerId = order.getCustomer().getId();
                this.userId = order.getUser().getId();
                this.orderTime = order.getOrderTime();
                this.item = order.getItem();
                this.price = order.getPrice();
        }

        @JsonCreator
        public OrderDto(@JsonProperty("id") Long id,
                        @JsonProperty("customerId") Long customerId,
                        @JsonProperty("userId") Long userId,
                        @JsonProperty("orderTime") LocalDateTime orderTime,
                        @JsonProperty("item") String item,
                        @JsonProperty("price") Double price) {
                this.id = id;
                this.customerId = customerId;
                this.userId = userId;
                this.orderTime = orderTime;
                this.item = item;
                this.price = price;


        }
}