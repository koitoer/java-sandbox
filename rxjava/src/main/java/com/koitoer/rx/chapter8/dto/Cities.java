package com.koitoer.rx.chapter8.dto;

import java.util.List;


/**
 * Created by mmena on 4/16/17.
 */
public class Cities {

    private List<City> results;

    public List<City> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "results=" + results +
                '}';
    }
}
