package com.order_management_system.service;

import com.order_management_system.payload.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    //List<CustomerDto> getAllCustomers(int pageNo, int pageSize, String sortBy, String sortDir);
    List<CustomerDto> getAllCustomers(int pageNo,int pageSize,String sortBy,String sortDir);
    CustomerDto getCustomerById(Long id);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
}
