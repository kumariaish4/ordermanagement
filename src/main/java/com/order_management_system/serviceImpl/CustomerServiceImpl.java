package com.order_management_system.serviceImpl;

import com.order_management_system.entities.Attender;
import com.order_management_system.entities.Customer;
import com.order_management_system.entities.Order;
import com.order_management_system.exception.ResourceNotFoundException;
import com.order_management_system.payload.AttenderDto;
import com.order_management_system.payload.CustomerDto;
import com.order_management_system.payload.OrderDto;
import com.order_management_system.repository.CustomerRepository;
import com.order_management_system.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CustomerDto> getAllCustomers(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by
                (sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<Customer>listofCustomer=customers.getContent();
        return listofCustomer.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }



//    @Override
//    public Page<CustomerDto> getAllCustomers(Pageable pageable) {
//        Page<Customer> customerPage = customerRepository.findAll(pageable);
//        return customerPage.map(customer -> modelMapper.map(customer, CustomerDto.class));
//    }

    @Override
    public CustomerDto createCustomer(@Valid CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return modelMapper.map(customer, CustomerDto.class);
    }


    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
//        modelMapper.map(customerDto, existingCustomer);
        existingCustomer.setName(customerDto.getName());
        existingCustomer.setPhone(customerDto.getPhone());
        existingCustomer.setAddress(customerDto.getAddress());
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerDto.class);
    }


//    @Override
//    public CustomerDto updateCustomer(Long id, CustomerDto customerDto){
//        Customer customer = customerRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
//        modelMapper.map(customer, customerDto);
//        Customer savedCustomer =customerRepository.save(customer);
//        return new CustomerDto(savedCustomer);
//    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(customer);
    }
}
