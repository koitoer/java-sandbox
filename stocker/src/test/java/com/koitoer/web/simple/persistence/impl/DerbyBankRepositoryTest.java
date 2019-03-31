package com.koitoer.web.simple.persistence.impl;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.koitoer.web.simple.dto.BankRecord;

import static org.junit.Assert.*;

/**
 * Created by mmena on 11/26/17.
 */
public class DerbyBankRepositoryTest {

    private DerbyBankRepository derbyBankRepository;

    @Before
    public void setUp() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.DERBY)
            .addScript("db/sql/create-db.sql")
            .addScript("db/sql/insert-data.sql")
            .build();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(db);

        derbyBankRepository = new DerbyBankRepository(namedParameterJdbcTemplate);
    }
    @Test
    public void getCurrentBalance() throws Exception {
        BigDecimal bankRecord = derbyBankRepository.getCurrentBalance("1");
        Assertions.assertThat(bankRecord).isNotNull();
        Assertions.assertThat(bankRecord).isEqualTo(BigDecimal.valueOf(123.33));
    }

}