package com.CRM.demo.bootstrap;

import com.CRM.demo.model.Customer;
import com.CRM.demo.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {

    private final CustomerService customerService;

    public LoadData(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Tebogo");
        newCustomer.setLastName("Mashika");
        newCustomer.setEmail("customer@gmail.com");

        customerService.save(newCustomer);

        System.out.println("Loaded New Customer");

    }
}
