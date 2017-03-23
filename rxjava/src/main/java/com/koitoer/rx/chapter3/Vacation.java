package com.koitoer.rx.chapter3;

import rx.Observable;

import java.time.LocalDate;

/**
 * Created by mmena on 3/19/17.
 */
public class Vacation {

    private final String where;
    private final LocalDate when;

    Vacation(String where, LocalDate when){
        this.where = where;
        this.when = when;
    }

    public Observable<Weather> weather(){
        return Observable.empty();
    }

    public Observable<String> cheapFlights(String from) {
        return Observable.empty();
    }

    public Observable<String> cheapHotel(){
        return Observable.empty();
    }

}
