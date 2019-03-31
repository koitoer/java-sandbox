package com.koitoer.web.simple.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.koitoer.web.simple.dto.Stock;
import com.koitoer.web.simple.persistence.StockRepository;


/**
 * Implementation of Stock Repository that will interact with the Derby Database.
 */
public class DerbyStockRepository implements StockRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DerbyStockRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private RowMapper<Stock> stockRowMapper = new RowMapper<Stock>() {

        @Override public Stock mapRow(ResultSet resultSet, int i) throws SQLException {
            Stock stock = new Stock();
            stock.setSymbol(resultSet.getString("SYMBOL"));
            stock.setCompanyName(resultSet.getString("COMPANYNAME"));
            stock.setClosePrice(resultSet.getBigDecimal("CLOSEPRICE"));
            stock.setOpenPrice(resultSet.getBigDecimal("OPENPRICE"));
            stock.setCurrentPrice(resultSet.getBigDecimal("CURRENTPRICE"));
            stock.setTotalVolume(resultSet.getInt("TOTALVOLUME"));
            stock.setUpdateTime(resultSet.getTimestamp("UPDATETIME").toLocalDateTime());
            stock.setSector(resultSet.getString("SECTOR"));
            return stock;
        }
    };

    @Override public Stock latestQuote(String symbol) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("symbol", symbol);
        List<Stock> stock  = namedParameterJdbcTemplate.query("SELECT * FROM STOCKS WHERE SYMBOL = :symbol", paramSource,  stockRowMapper);
        return stock.get(0);
    }

    @Override public List<Stock> getHistoryForStock(String symbol, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override public List<Stock> quoteFromYourWatchList(String accountId) {
        return null;
    }
}
