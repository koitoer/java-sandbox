package com.koitoer.rx.chapter3;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/18/17.
 */
public class Operators3 {

    public static void main(String[] args) throws InterruptedException {

        Observable<String> temps = Observable.just("1","2","3","4","5");
        Observable<String> winds = Observable.just("A","A","B","C");

        Observable<Weather> weatherObservable = Observable.zip(temps, winds, (x,y) -> new Weather(x,y));
        weatherObservable.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);

        //To do something similar with flatMap know as cartesian product
        Observable<Weather> weather = winds.flatMap(wind -> temps.map(temp -> new Weather(temp, wind)));
        weather.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);
    }
}
