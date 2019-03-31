package com.koitoer.web.simple.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Data;

/**
 * Account information for an specific person.
 */
@Data
public class Account {

    /**
     * Account Id, user account identifier this will be unique
     */
    private String accountId;

    /**
     * Customer name
     */
    private String customerName;

    /**
     * What is the current Balance in your bank account
     */
    private BigDecimal currentBalance;

    /**
     * Track all the movements related to bank and stocks for specific user
     */
    private AccountHistory accountHistory;

    /**
     * What are the stocks that you already hold with the prices at the moment of the purchase
     */
    private List<Stock> ownStocks;

    /**
     * This is your personal stock watch list, stocks that you don't own but you follow.
     */
    private List<Stock> watchListstocks;

}
