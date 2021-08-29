package com.CRM.demo.services;

import com.CRM.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerService extends JpaRepository<Customer,Long> {


}
