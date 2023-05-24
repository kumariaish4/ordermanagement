package com.order_management_system.controller;

import com.order_management_system.entities.Customer;
import com.order_management_system.payload.CustomerDto;
import com.order_management_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getAllCustomers(@RequestParam(value = "pageNo", defaultValue = "0", required = false)
                                                 int  pageNo,
                   @RequestParam(value = "pageSize", defaultValue = "5", required = false) int  pageSize,
                  @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                  @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String  sortDir
    ) {

        return customerService.getAllCustomers(pageNo,pageSize,sortBy,sortDir);
    }


//    @GetMapping
//    public ResponseEntity<Page<CustomerDto>> getAllCustomers(Pageable pageable) {
//        Page<CustomerDto> customers = customerService.getAllCustomers(pageable);
//        return ResponseEntity.ok(customers);
//    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdCustomer.getId()).toUri();
        return ResponseEntity.created(location).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
//        CustomerDto customer = customerService.getCustomerById(id);
//
//        if (customer == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Update customer entity with values from DTO
//        customer.setName(customerDto.getName());
//        customer.setPhone(customerDto.getPhone());
//        customer.setAddress(customerDto.getAddress());
//
//        // Save the updated customer entity to the database
//        customer = customerService.updateCustomer(id,customerDto);
//
//        // Convert updated customer entity to DTO and return in the response
//        CustomerDto updatedCustomerDto = new CustomerDto(customer);
//        return ResponseEntity.ok(updatedCustomerDto);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
