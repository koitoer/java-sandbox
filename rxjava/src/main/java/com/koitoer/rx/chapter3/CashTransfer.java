package com.koitoer.rx.chapter3;

import java.math.BigDecimal;

/**
 * Created by mmena on 3/19/17.
 */
public class CashTransfer {

    private BigDecimal amount;

    public CashTransfer(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
