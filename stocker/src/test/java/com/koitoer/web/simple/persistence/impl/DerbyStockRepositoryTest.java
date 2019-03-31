package com.koitoer.web.simple.persistence.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Predicate;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.koitoer.web.simple.dto.Stock;

/**
 * Verify Derby Stock repository works as expected
 */
public class DerbyStockRepositoryTest {

    private DerbyStockRepository derbyStockRepository;

    @Before
    public void setUp() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.DERBY)
            .addScript("db/sql/create-db.sql")
            .addScript("db/sql/insert-data.sql")
            .build();

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(db);

        derbyStockRepository = new DerbyStockRepository(namedParameterJdbcTemplate);
    }

    @Test
    public void latestQuote() throws Exception {

        Stock stock = derbyStockRepository.latestQuote("EXPE");
        Assertions.assertThat(stock).isNotNull();
        Assertions.assertThat(stock.getCompanyName()).isEqualTo("Expedia");
        Assertions.assertThat(stock.getClosePrice()).isEqualTo(BigDecimal.valueOf(123.44));
        Assertions.assertThat(stock.getOpenPrice()).isEqualTo(BigDecimal.valueOf(123.33));
        Assertions.assertThat(stock.getCurrentPrice()).isEqualTo(BigDecimal.valueOf(125.44));


        Condition nonZeroPrice = new Condition(new Predicate<BigDecimal>() {

            @Override public boolean test(BigDecimal value) {
                return value.compareTo(BigDecimal.ZERO) > 0;
            }
        }, "Verify if value is greater than zero");

        Assertions.assertThat(stock.getClosePrice()).is(nonZeroPrice);
        Assertions.assertThat(Arrays.asList(stock.getCurrentPrice(), stock.getClosePrice())).are(nonZeroPrice);

    }

}