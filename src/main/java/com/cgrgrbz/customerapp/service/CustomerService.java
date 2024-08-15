package com.cgrgrbz.customerapp.service;

import com.cgrgrbz.customerapp.entity.Customer;
import jakarta.validation.Valid;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findById(int id);
    Customer save(@Valid Customer customer);
    void deleteById(int id);
}
