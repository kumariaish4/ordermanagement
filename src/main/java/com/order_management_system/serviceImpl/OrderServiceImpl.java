package com.order_management_system.serviceImpl;

import com.order_management_system.entities.Customer;
import com.order_management_system.entities.Order;
import com.order_management_system.entities.User;
import com.order_management_system.exception.ResourceNotFoundException;
import com.order_management_system.payload.OrderDto;
import com.order_management_system.repository.CustomerRepository;
import com.order_management_system.repository.OrderRepository;
import com.order_management_system.repository.UserRepository;
import com.order_management_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerRepository customerRepository,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }
    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return new OrderDto(order);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDTO) {
        Order order = new Order();
        setOrderFields(order, orderDTO);
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        setOrderFields(order, orderDto);
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        orderRepository.delete(order);
    }

    private void setOrderFields(Order order, OrderDto orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", orderDTO.getCustomerId()));
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", orderDTO.getUserId()));
        order.setCustomer(customer);
        order.setUser(user);
        order.setOrderTime(orderDTO.getOrderTime());
        order.setItem(orderDTO.getItem());
        order.setPrice(orderDTO.getPrice());
    }

}

