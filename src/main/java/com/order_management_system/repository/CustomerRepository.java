package com.order_management_system.repository;

import com.order_management_system.entities.Customer;
import com.order_management_system.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
