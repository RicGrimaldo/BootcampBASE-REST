package com.bancobase.bootcamp.controllers;

import com.bancobase.bootcamp.dto.CustomerInfoDTO;
import com.bancobase.bootcamp.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@Tag(name = "Customers controller")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    @Operation(summary = "Get all customers and filter by name")
    public ResponseEntity<List<CustomerInfoDTO>> getCustomers(@RequestParam(required = false) String name){
        List<CustomerInfoDTO> customers = this.customerService.filterCustomersByName(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new customer")


}
