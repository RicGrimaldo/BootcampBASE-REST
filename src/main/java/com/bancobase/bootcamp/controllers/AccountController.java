package com.bancobase.bootcamp.controllers;

import com.bancobase.bootcamp.dto.AccountDTO;
import com.bancobase.bootcamp.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts controller")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST})
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get all accounts of a costumer")
    public ResponseEntity<List<AccountDTO>> getAllCustomerAccounts(@PathVariable(value = "customerId") Long customerId) {
        List<AccountDTO> customers = this.accountService.getAllAccountsByCustomerId(customerId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
