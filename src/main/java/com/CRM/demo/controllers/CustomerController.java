package com.CRM.demo.controllers;

import com.CRM.demo.model.Customer;
import com.CRM.demo.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class CustomerController {

    // perform dependency injection
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping({"/home", "/home.html", "/"})  // @RequestMapping - give it the path of the page you mapping to
    public String viewCustomers(Model model){

        // the model attribute name = (viewCustomerHomePage) - will act as an object which will render customerService.findAll();
        model.addAttribute("viewCustomerHomePage", customerService.findAll());

        // return to home Page
        return "/home.html";
    }


    @RequestMapping({"/mappingDeleteCustomer"}) // mapping this url as a link on Home page

    public String deleteCustomer(Model model,
                                 @RequestParam(value = "id", required = false) Long  id){

        // get customer by id
        Customer deleteCustomer = customerService.getById(id);

        // delete customer
        customerService.delete(deleteCustomer);

        // redirect to the home page
        return "redirect:/home.html";
    }

    //mapping this url as a link on Home page and update page
    // params from the form inputs firstName, lastName, email
    @RequestMapping({"/showCustomerToUpdate/{id}"})
    public String updateCustomerButton(@PathVariable(value = "id") Long id, Model model, Customer customer){

        model.addAttribute("showCustomerToUpdate", customerService.getById(id));

        // goes to the update template
        return "update.html";
    }


    // @ModelAttribute -  retrieved from the model = showCustomerToUpdate - bind the arguments of the model showCustomerToUpdate and pass them to the method
    // saves - add new customer from Customer.html and updates new customer from update.html
    @RequestMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){

        //save customer to the dataBase

        customerService.save(customer);

        // returns the home page
        return "redirect:/";

    }


    // receives request from the url link in the home.html page and return the customer.html page
    @RequestMapping("/showFormToAddNewCustomer")
    public String NewCustomer(Model model, Customer customer){

        return "customer.html";

    }

    // view user details
    @RequestMapping("viewUser/{id}")
    public String viewUser(@PathVariable(value = "id") Long id, Model model, Customer customer){

        model.addAttribute("viewSelectedUser", customerService.getById(id));

        return "viewCustomer.html";

    }


}
