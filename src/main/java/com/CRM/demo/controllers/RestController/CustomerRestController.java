package com.CRM.demo.controllers.RestController;


import com.CRM.demo.model.Customer;
import com.CRM.demo.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(CustomerRestController.BASE_URL)
public class CustomerRestController {

  public static final String BASE_URL = "rest-service/";

  private final CustomerService customerService;

  public CustomerRestController(CustomerService customerService) {
    this.customerService = customerService;
  }

  // view all
  @GetMapping("/viewListOfCustomers")
  public List<Customer> viewCustomers(){

    return customerService.findAll();
  }


  // delete customer
  @PostMapping("/deleteCustomer{id}")
  public String deleteCustomer(@RequestBody Customer  customer){

    // get customer by id
    Customer deleteCustomer = customerService.getById(customer.getId());

    customerService.delete(deleteCustomer);

    return "customer successfully deleted";
  }

  // view customer
  @GetMapping("/viewCustomer{id}")
  public Optional<Customer> viewCustomer(@RequestBody Customer customer){

    return customerService.findById(customer.getId());
  }

  // //save customer to the dataBase
  @RequestMapping("/saveCustomer")
  public String saveCustomer(@RequestBody Customer customer){

    customerService.save(customer);

    return "successfully saved customer ";

  }

  // save customer
  @PostMapping({"/UpdateCustomer{id}"})
  public String updateCustomer(@RequestBody Customer customer){

    Customer customer1 = customerService.getById(customer.getId());

    if (customer1 != null){
      customerService.save(customer);
    }


    // goes to the update template
    return "successfully updated customer ";
  }

}
