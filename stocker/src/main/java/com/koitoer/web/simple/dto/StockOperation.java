package com.koitoer.web.simple.dto;

import java.math.BigDecimal;

/**
 * Dto that represent a operation with a stock
 */
public class StockOperation {

    /**
     * What type of movement was
     */
    private StockMovement stockMovement;

    /**
     * What was the transaction cost of the operation
     */
    private BigDecimal transactionCost;

    /**
     * Over which stock was the operation applied
     */
    private Stock stock;

    /**
     * Number of stocks involved in this operation
     */
    private Integer numberOfStocks;

}
