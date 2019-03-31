package com.koitoer.web.simple.dto;

import java.util.List;

/**
 * History of all the operations that the user have completed.
 */
public class AccountHistory {

    /**
     * All the operations that a user has made with Stocks.
     */
    private List<StockOperation> stockOperationList;

    /**
     * All the bank operations that a user has made.
     */
    private List<BankRecord> bankRecordList;

}
