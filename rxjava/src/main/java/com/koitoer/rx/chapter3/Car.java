package com.koitoer.rx.chapter3;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/18/17.
 */
public class Car{

    public Car(){

    }

    private boolean tiers=false;
    private boolean windows;
    private boolean engine;

    public Observable<Car> putTiers() {
        tiers = true;
        return Observable.empty();
    }

    public Observable<Car> putWindows() {
        windows = true;
        return Observable.empty();
    }

    public Observable<Car>  putEngine() throws InterruptedException {
        engine = true;
        System.out.println("Sleeping putEngine");
        TimeUnit.SECONDS.sleep(5);
        return Observable.empty();
    }

    @Override
    public String toString() {
        return ""+tiers+windows+engine;
    }
}