package com.koitoer.web.simple.persistence;

import java.math.BigDecimal;

import com.koitoer.web.simple.exception.BankException;

/**
 * Repository to interact with your bank account
 */
public interface BankRepository {

    /**
     * Get current balance of an account
     *
     * @return Current Balance
     */
    BigDecimal getCurrentBalance(String accountId);

    /**
     * Deposit money in a specific account
     *
     * @param accountId The owner account ID
     * @param amount    The amount that you want to deposit
     * @return The current balance after the operation was completed
     * @throws BankException
     */
    BigDecimal depositMoney(String accountId, BigDecimal amount) throws BankException;

    /**
     * Withdraw money in a specific account
     *
     * @param accountId The owner account ID
     * @param amount    The amount that you want to withdraw
     * @return The current balance after the operation was completed
     * @throws BankException
     */
    BigDecimal withdrawMoney(String accountId, BigDecimal amount) throws BankException;

}
