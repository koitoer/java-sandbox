package com.koitoer.web.simple.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.koitoer.web.simple.dto.Account;
import com.koitoer.web.simple.dto.Stock;
import com.koitoer.web.simple.persistence.AccountRepository;

/**
 * Account repository implementation for Derby Database
 */
public class DerbyAccountRepository implements AccountRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Account> accountRowMapper = new RowMapper<Account>() {

        @Override public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setCustomerName(resultSet.getString("CUSTOMERNAME"));
            account.setOwnStocks(Arrays.asList(new Stock()));
            account.setWatchListstocks(Arrays.asList(new Stock()));
            return account;
        }
    };

    public DerbyAccountRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override public Account getAccountInformation(String accountId) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("accountId", accountId);
        List<Account> accountList =
            namedParameterJdbcTemplate.query("SELECT * FROM ACCOUNTS WHERE ACCOUNTID = :accountId", sqlParameterSource, accountRowMapper);
        return accountList.get(0);
    }

    @Override public void updateAccount(Account account) {

    }
}
