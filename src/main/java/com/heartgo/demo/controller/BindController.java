package com.heartgo.demo.controller;


import com.alibaba.fastjson.JSON;
import com.heartgo.demo.client.BankInfoClient;
import com.heartgo.demo.model.Bank;
import com.heartgo.demo.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "", produces = "application/json")
public class BindController {

    @Autowired
    private BankInfoClient bankInfoClient;

    @PostMapping("saveBind")
    public String saveBank(Bank bank) throws Exception {
        CommonResult res = new CommonResult();
        if (null == bank.getBankId() || bank.getBankId().isEmpty() || null == bank.getBankAccount() || bank.getBankAccount().isEmpty()) {
            res.setCode(-1);
            res.setMsg("userId or bank account is empty.");
            return JSON.toJSONString(res);
        }
        if (bankInfoClient.saveBankInfo(bank)) {
            res.setCode(200);
            res.setMsg("OK");
        } else {
            res.setCode(-1);
            res.setMsg("bank failed.");
        }
        return JSON.toJSONString(res);
    }

    @GetMapping("queryBind")
    public String queryUser(String bankId) throws Exception {
        CommonResult commonResult = new CommonResult();
        if (bankId == null || bankId.isEmpty()) {
            commonResult.setCode(-1);
            commonResult.setMsg("bankId is empty");
            return JSON.toJSONString(commonResult);
        }

        String bankinfo = bankInfoClient.queryBankInfo(bankId);
        if (bankinfo == null || bankinfo.isEmpty()) {
            commonResult.setCode(-1);
            commonResult.setMsg("entry doesn't exist.");
            return JSON.toJSONString(commonResult);
        }
        commonResult.setCode(200);
        commonResult.setMsg(bankinfo);
        return JSON.toJSONString(commonResult);
    }

}