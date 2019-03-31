package com.koitoer.web.simple.persistence;

import java.time.LocalDate;
import java.util.List;

import com.koitoer.web.simple.dto.Stock;

/**
 * Repository that interacts with the Persistence Layer
 */
public interface StockRepository {

    Stock latestQuote(String symbol);

    List<Stock> getHistoryForStock(String symbol, LocalDate startDate, LocalDate endDate);

    List<Stock> quoteFromYourWatchList(String accountId);

}
