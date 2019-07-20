package com.heartgo.demo.controller;


import com.heartgo.demo.client.BankInfoClient;
import com.heartgo.demo.model.Bind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "", produces = "application/json")
public class BindController {

    @Autowired
    private BankInfoClient bankInfoClient;

    @PostMapping("saveBind")
    public void saveBank(@RequestBody Bind bind) throws Exception{

        bankInfoClient.saveBankInfo(bind);

    }

    @GetMapping("queryBind")
    public String queryUser(String bankId) throws Exception{
        return  bankInfoClient.queryBankInfo(bankId);

    }

}