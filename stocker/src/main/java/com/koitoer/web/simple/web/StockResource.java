package com.koitoer.web.simple.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koitoer.web.simple.dto.Stock;
import com.koitoer.web.simple.service.StockService;

/**
 * Endpoint for Stocks
 */
@Component
@RequestMapping("/stock/")
public class StockResource {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/{symbol}", method = RequestMethod.GET)
    @ResponseBody
    public Stock getQuoteFromDatabase(@PathVariable("symbol") String symbol){
        return stockService.getQuote(symbol);
    }
}
