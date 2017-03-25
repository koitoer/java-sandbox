package com.koitoer.rx.chapter4;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/23/17.
 */
public class ZipExample {

    public static void main(String[] args) {
        Observable<String> obs1 = Observable.defer(() -> Observable.just(getOdd()))
                .doOnError((e) -> System.out.println(e.getMessage())).subscribeOn(Schedulers.io());
        Observable<String> obs2 = Observable.defer(() -> Observable.just(getPair())
                .doOnError((e) -> System.out.println(e.getMessage()))).subscribeOn(Schedulers.io());

        System.out.println("Start");
        Observable<String> obs = Observable.zip(obs1, obs2, (value1, value2) -> value1.concat(value2))
                .doOnError(ex -> System.out.println("*** ZIP operation error ***"));


        System.out.println(obs.toBlocking().single());
        System.out.println("End");
    }

    private static String getPair() {
        System.out.println("Execute the pair " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(5);
            throw new RuntimeException("ss");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(2, 4, 6).toString();
    }

    private static String getOdd() {
        System.out.println("Executing " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
            throw new RuntimeException("ssssppp");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(1, 3, 5).toString();
    }
}
