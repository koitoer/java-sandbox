package com.koitoer.web.simple.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * Dto which responsibility is track all the bank related operations.
 */
@Data
public class BankRecord {

    /**
     * Account ID for this specific bank record
     */
    private String accountId;

    /**
     * Record ID for this specific operation
     */
    private String recordId;

    /**
     * What kind of operation this record is
     */
    private BankOperation bankOperation;

    /**
     * What is the amount of this bank record
     */
    private BigDecimal amount;

    /**
     * Timestamp of the this specific record
     */
    private LocalDateTime timestamp;

}
