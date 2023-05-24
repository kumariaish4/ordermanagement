package com.order_management_system.repository;

import com.order_management_system.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //List<Order> findByManagerId(Long managerId);
}
