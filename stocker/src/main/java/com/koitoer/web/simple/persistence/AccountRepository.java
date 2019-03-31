package com.koitoer.web.simple.persistence;

import com.koitoer.web.simple.dto.Account;

/**
 * Account repository operations
 */
public interface AccountRepository {

    /**
     * Check if account exists and retrieve some personal information
     *
     * @return Valid Account object
     * @param accountId
     */
    Account getAccountInformation(String accountId);

    /**
     * Update an specific account with the new values coming in the object
     *
     * @param account Account with the new values
     */
    void updateAccount(Account account);

}
