package com.bluedemons2024.dolphintellect_backend.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")

public class UserController {

    @Autowired
    UserRepistory userRepistory;

//    @GetMapping
//    public List<UserEntity> getAllAccounts(){
//        return userRepistory.findAll();
//    }

    // TODO: Create functions to get user login


    // Register Account
    @PostMapping("/register")
    public void registerAccount(@RequestBody UserEntity account){
//        Account account = new Account();
        System.out.println(account);
        userRepistory.save(account);

//        return userRepistory.
    }





    // Login Account
//    @PostMapping("/login")
//    public void loginAccount(@RequestBody Account account){
//        userRepistory.findOne(account);
//    }





}
