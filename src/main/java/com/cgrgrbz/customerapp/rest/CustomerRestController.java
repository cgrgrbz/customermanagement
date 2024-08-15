package com.cgrgrbz.customerapp.rest;

import com.cgrgrbz.customerapp.entity.Customer;
import com.cgrgrbz.customerapp.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Customer REST Controller", description = "provide basic CRUD operations")
public class CustomerRestController {

    CustomerService customerService;

    @Autowired
    public CustomerRestController (CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    @Operation(description = "method for listing all customers")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    @Operation(description = "method for listing the chosen customer")
    public Customer getCustomer(@PathVariable int customerId){
        Customer customer = customerService.findById(customerId);
        if(customer == null){
            throw new RuntimeException("Customer can't find by id: " + customerId);
        }
        return customer;
    }

    @PostMapping("/customers")
    @Operation(description = "method for adding new customer")
    public Customer save(@Valid @RequestBody Customer customer){
        customer.setId(0);
        return customerService.save(customer);
    }

    @PutMapping("/customers")
    @Operation(description = "method for updating customer info")
    public Customer update(@Valid @RequestBody Customer customer){
        return customerService.save(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    @Operation(description = "method for deleting the chosen customer")
    public String delete(@PathVariable int customerId){
        Customer customer = customerService.findById(customerId);
        if(customer == null){
            throw new RuntimeException("Customer can't deleted by id: " + customerId);
        }
        customerService.deleteById(customerId);
        return "Customer deleted by id : " + customerId;
    }


}
