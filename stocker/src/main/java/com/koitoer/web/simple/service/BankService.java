package com.koitoer.web.simple.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koitoer.web.simple.persistence.BankRepository;

/**
 * Bank Service - This will interact with the bank data store to manage all the bank operations.
 */
@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public BigDecimal getCurrentBalance(String accountId){
        BigDecimal currentBalance = bankRepository.getCurrentBalance(accountId);
        return currentBalance;
    }


}
