package com.koitoer.web.simple.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koitoer.web.simple.dto.Account;
import com.koitoer.web.simple.service.AccountService;

/**
 * Account REST interface
 */
@Component
@RequestMapping("/account/")
public class AccountResource {

    @Autowired
    private AccountService accountService;

    /**
     * Retrieve Account information
     *
     * @param accountId Account identifier
     * @return A valid Account
     */
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable(value = "accountId") String accountId) {
        return accountService.getAccountInformation(accountId);
    }
}
