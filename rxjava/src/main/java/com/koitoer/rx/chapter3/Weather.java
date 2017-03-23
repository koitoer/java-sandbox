package com.koitoer.rx.chapter3;

/**
 * Created by mmena on 3/18/17.
 */
public class Weather {

    private String temp;
    private String wind;

    public Weather(String temp, String wind){
        this.temp =  temp;
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "Temp="+temp+":Wind="+wind;
    }
}

