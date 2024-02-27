package com.example.demo.Controller;

import com.example.demo.Service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerInfoController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("get-customer")
    public void getCustomer() {
        customerService.getInfo();
    }
}
