package com.koitoer.web.simple.persistence;

import java.util.List;

import com.koitoer.web.simple.dto.Stock;

/**
 * Daily reports based on your stock watchlist
 */
public interface ReportRepository {

    /**
     * From you watch list what are the N number of highest stocks
     *
     * @param accountId Your account number to retrieve your watch list
     * @param number    Number of stocks that you want to see in the list
     * @return List of the N top stock.
     */
    List<Stock> getTopStocks(String accountId, int number);

    /**
     * From you watch list what are the N number of lowest stocks
     *
     * @param accountId Your account number to retrieve your watch list
     * @param number    Number of stocks that you want to see in the list
     * @return List of the N bottom stock.
     */
    List<Stock> getBottomStocks(String accountId, int number);

}
