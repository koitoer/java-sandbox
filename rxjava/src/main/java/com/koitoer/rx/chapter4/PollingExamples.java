package com.koitoer.rx.chapter4;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/25/17.
 */
public class PollingExamples {

    public static void main(String[] args) {

        //This will pull the API each 10 ms and only emits if is a change there
        Observable.interval(10, TimeUnit.MILLISECONDS)
                .map(x -> getOrder())
                .distinctUntilChanged();
    }

    private static String getOrder() {
        return "Order ready !!";
    }
}
