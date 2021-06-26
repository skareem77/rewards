package com.example.transaction.controller;

import com.example.transaction.model.Customer;
import com.example.transaction.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/customers")
    public List<Customer> findCustomerAll() {
        return rewardsService.getCustomerAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        Customer customer = rewardsService.getCustomerById(id);
        if (customer == null) return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);

    }
}
