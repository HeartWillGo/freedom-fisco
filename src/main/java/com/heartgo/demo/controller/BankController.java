package com.heartgo.demo.controller;


import com.alibaba.fastjson.JSON;
import com.heartgo.demo.client.BankInfoClient;
import com.heartgo.demo.model.BackendResult;
import com.heartgo.demo.model.Bank;
import com.heartgo.demo.model.RESULT;
import com.heartgo.demo.service.BankService;
import com.heartgo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.heartgo.demo.model.RESULT.BANK_EMPTY_INFO;


@RestController
@RequestMapping(value = "", produces = "application/json")
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private UserService userService;

    @PostMapping("bindBank")
    public String saveBank(Bank bank) throws Exception {
        if (isParamEmpty(bank)) {
            return JSON.toJSONString(new BackendResult(BANK_EMPTY_INFO));
        }

        RESULT result = bankService.saveBankInfo(bank);
        return JSON.toJSONString(new BackendResult(result));
    }

    @GetMapping("queryBind")
    public String queryBank(String bankId) throws Exception {
        if (bankId == null || bankId.isEmpty()) {
            return JSON.toJSONString(new BackendResult(BANK_EMPTY_INFO));
        }
        Bank bank = bankService.queryBankInfo(bankId);
        if (null == bank)
            return null;

        return JSON.toJSONString(bank);
    }

    private boolean isParamEmpty(Bank bank) {
        return (null == bank.getBankId() ||
                bank.getBankId().isEmpty() ||
                null == bank.getBankAccount() ||
                bank.getBankAccount().isEmpty());
    }

}