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
  public static final String VIEW_CUSTOMER_LIST = "/viewListOfCustomers";
  public static final String DELETE_CUSTOMER = "/deleteCustomer{id}";
  public static final String VIEW_CUSTOMER = "/viewCustomer{id}";
  public static final String SAVE_CUSTOMER = "/saveCustomer";
  public static final String UPDATE_CUSTOMER = "/UpdateCustomer{id}";


  private final CustomerService customerService;

  public CustomerRestController(CustomerService customerService) {
    this.customerService = customerService;
  }

  // view all
  @GetMapping(VIEW_CUSTOMER_LIST)
  public List<Customer> viewCustomers(){

    return customerService.findAll();
  }

  // delete customer
  @PostMapping(DELETE_CUSTOMER)
  public String deleteCustomer(@RequestBody Customer  customer){

    // get customer by id
    Customer deleteCustomer = customerService.getById(customer.getId());

    customerService.delete(deleteCustomer);

    return "customer successfully deleted";
  }

  // view customer
  @GetMapping(VIEW_CUSTOMER)
  public Optional<Customer> viewCustomer(@RequestBody Customer customer){

    return customerService.findById(customer.getId());
  }

  // //save customer to the dataBase
  @RequestMapping(SAVE_CUSTOMER)
  public String saveCustomer(@RequestBody Customer customer){

    customerService.save(customer);

    return "successfully saved customer ";

  }

  // save customer
  @PostMapping(UPDATE_CUSTOMER)
  public String updateCustomer(@RequestBody Customer customer){

    Customer customer1 = customerService.getById(customer.getId());

    if (customer1 != null){
      customerService.save(customer);
    }


    // goes to the update template
    return "successfully updated customer ";
  }

}
