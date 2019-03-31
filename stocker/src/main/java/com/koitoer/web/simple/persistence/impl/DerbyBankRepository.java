package com.koitoer.web.simple.persistence.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.koitoer.web.simple.dto.BankOperation;
import com.koitoer.web.simple.dto.BankRecord;
import com.koitoer.web.simple.exception.BankException;
import com.koitoer.web.simple.persistence.BankRepository;

/**
 * Bank Repository implementation using Derby Database
 */
public class DerbyBankRepository implements BankRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<BankRecord> bankRecordRowMapper = new RowMapper<BankRecord>() {

        @Override public BankRecord mapRow(ResultSet resultSet, int i) throws SQLException {
            BankRecord bankRecord = new BankRecord();
            bankRecord.setAccountId(resultSet.getString("ACCOUNTID"));
            bankRecord.setBankOperation(BankOperation.valueOf(resultSet.getString("BANKOPERATION")));
            bankRecord.setAmount(resultSet.getBigDecimal("AMOUNT"));
            return bankRecord;
        }
    };

    public DerbyBankRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getCurrentBalance(String accountId) {

        MapSqlParameterSource sqlParamSource = new MapSqlParameterSource();
        sqlParamSource.addValue("accountId", accountId);

        List<BankRecord> userRecords =
            namedParameterJdbcTemplate.query("SELECT * FROM BANK WHERE ACCOUNTID = :accountId", sqlParamSource, bankRecordRowMapper);

        BigDecimal positive = userRecords.stream()
            .filter(x -> x.getBankOperation().equals(BankOperation.DEPOSIT))
            .map(x -> x.getAmount())
            .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        BigDecimal negative = userRecords.stream()
            .filter(x -> x.getBankOperation().equals(BankOperation.WITHDRAW))
            .map(x -> x.getAmount())
            .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        return positive.subtract(negative);
    }

    @Override public BigDecimal depositMoney(String accountId, BigDecimal amount) throws BankException {
        return null;
    }

    @Override public BigDecimal withdrawMoney(String accountId, BigDecimal amount) throws BankException {
        return null;
    }
}
