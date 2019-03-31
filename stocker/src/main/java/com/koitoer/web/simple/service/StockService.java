package com.koitoer.web.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koitoer.web.simple.dto.Stock;
import com.koitoer.web.simple.persistence.StockRepository;

/**
 * Created by mmena on 11/26/17.
 */
@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock getQuote(String symbol){
        return stockRepository.latestQuote(symbol);
    }

}
