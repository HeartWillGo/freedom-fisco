package com.heartgo.demo.service;

import com.heartgo.demo.client.BankInfoClient;
import com.heartgo.demo.model.Bank;
import com.heartgo.demo.model.RESULT;
import org.springframework.beans.factory.annotation.Autowired;

public class BankService {

    @Autowired
    private BankInfoClient bankInfoClient;

    public RESULT saveBankInfo(Bank bank) {
        return bankInfoClient.saveBankInfo(bank);
    }

    public Bank queryBankInfo(String bankId) {
        return bankInfoClient.queryBankInfo(bankId);
    }
}
