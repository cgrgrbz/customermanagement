package com.cgrgrbz.customerapp.repo;

import com.cgrgrbz.customerapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
