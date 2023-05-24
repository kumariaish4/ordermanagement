package com.order_management_system.service;

import com.order_management_system.payload.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrder(Long id, OrderDto orderDto);
    void deleteOrder(Long id);
}
