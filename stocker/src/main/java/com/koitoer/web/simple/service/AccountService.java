package com.koitoer.web.simple.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koitoer.web.simple.dto.Account;
import com.koitoer.web.simple.dto.AccountHistory;
import com.koitoer.web.simple.persistence.AccountRepository;

/**
 * Account Service Aggregation.
 */
@Service
public class AccountService {

    @Autowired
    private BankService bankService;

    @Autowired
    private StockService stockService;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Aggregate several responses to get the current Account information
     *
     * @param accountId The Account identifier
     * @return An Account object with all the information
     */
    public Account getAccountInformation(String accountId) {
        Account account = accountRepository.getAccountInformation(accountId);
        account.setAccountHistory(getAccountHistory());
        account.setCurrentBalance(getCurrentBalance(accountId));
        return account;
    }

    private BigDecimal getCurrentBalance(String accountId) {
        return bankService.getCurrentBalance(accountId);
    }

    private AccountHistory getAccountHistory() {
        return new AccountHistory();
    }


}
