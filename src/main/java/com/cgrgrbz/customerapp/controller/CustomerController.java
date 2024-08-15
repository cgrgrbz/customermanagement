package com.cgrgrbz.customerapp.controller;

import com.cgrgrbz.customerapp.entity.Customer;
import com.cgrgrbz.customerapp.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/pageForAdd")
    public String pageForAdd(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "save-customer";
    }

    @GetMapping("/pageForUpdate")
    public String pageForUpdate(@RequestParam("customerId") int id,Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute(customer);
        return "save-customer";
    }

    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "save-customer";
        } else {
            customerService.save(customer);
            // use a redirect to prevent duplicate submissions. We can use Post/Redirect/Get pattern
            return "redirect:/customers/list";
        }
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('SENIOR')")
    public String deleteCustomer(@RequestParam("customerId") int id){
        customerService.deleteById(id);
        return "redirect:/customers/list";
    }

    // InitBinder removes whitespaces.
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}
