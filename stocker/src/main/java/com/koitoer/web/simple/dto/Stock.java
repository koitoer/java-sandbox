package com.koitoer.web.simple.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.koitoer.web.simple.util.CustomLocalDateTimeSerializer;

import lombok.Data;

/**
 * Stock DTO (Represent a stock with its information)
 */
@Data
public class Stock {

    /**
     * Stock symbol
     */
    public String symbol;

    /**
     * What is the company name for that stock
     */
    public String companyName;

    /**
     * What is the sector for that stock
     */
    public String sector;

    /**
     * What is the open price for this stock at the date of quote
     */
    public BigDecimal openPrice;

    /**
     * What is the close price for this stock at the date of quote
     */
    public BigDecimal closePrice;

    /**
     * Current price of the stock at the moment of quote.
     */
    public BigDecimal currentPrice;

    /**
     * What is the total volume of the stock at the quote time
     */
    public Integer totalVolume;

    /**
     * Timestamp of the quote.
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    public LocalDateTime updateTime;

}
