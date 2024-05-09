package com.bluedemons2024.dolphintellect_backend.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")

public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    // TODO: Create functions to get user login


    // Register Account
    @PostMapping("/register")
    public void registerAccount(@RequestBody Account account){
//        Account account = new Account();
        System.out.println(account);
        accountRepository.save(account);

//        return accountRepository.
    }




//    // Login Account
//    @PostMapping("/login")
//    public void loginAccount(@RequestBody Account account){
//        accountRepository.findOne(account);
//    }



}
