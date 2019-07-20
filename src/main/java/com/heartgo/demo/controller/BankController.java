package com.heartgo.demo.controller;


import com.heartgo.demo.client.BankInfoClient;
import com.heartgo.demo.client.UserInfoClient;
import com.heartgo.demo.model.Bank;
import com.heartgo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "", produces = "application/json")
public class BankController {

    @Autowired
    private BankInfoClient bankInfoClient;
    @PostMapping("saveBank")
    public void saveBank(@RequestBody Bank bank) throws Exception{

        bankInfoClient.saveBankInfo(bank);

    }

    @GetMapping("queryBank")
    public String queryUser(String bankId) throws Exception{
        return  bankInfoClient.queryBankInfo(bankId);

    }



}