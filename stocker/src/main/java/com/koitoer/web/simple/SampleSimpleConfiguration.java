package com.koitoer.web.simple;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.koitoer.web.simple.persistence.AccountRepository;
import com.koitoer.web.simple.persistence.BankRepository;
import com.koitoer.web.simple.persistence.StockRepository;
import com.koitoer.web.simple.persistence.impl.DerbyAccountRepository;
import com.koitoer.web.simple.persistence.impl.DerbyBankRepository;
import com.koitoer.web.simple.persistence.impl.DerbyStockRepository;

/**
 * Spring configuration to define more beans
 */
@Configuration
public class SampleSimpleConfiguration {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
            .setType(EmbeddedDatabaseType.DERBY) //.H2 or .DERBY
            .addScript("db/sql/create-db.sql")
            .addScript("db/sql/insert-data.sql")
            .build();
        return db;
    }

    @Bean
    public StockRepository derbyStockRepository(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return new DerbyStockRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public BankRepository derbyBankRepository(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return new DerbyBankRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public AccountRepository derbyAccountRepository(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =  new NamedParameterJdbcTemplate(dataSource());
        return new DerbyAccountRepository(namedParameterJdbcTemplate);
    }

}
