package com.koitoer.rx.chapter0;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mmena on 11/8/17.
 */
public class RxObserver {

    public static void main(String[] args) throws InterruptedException {
        Observable.just("a","n","e", "f", "g", "h", "s", "h")
            .observeOn(Schedulers.from(Executors.newFixedThreadPool(5)))
            .map(new Func1<String, Object>() {

                @Override public Object call(String s) {
                    try {
                        TimeUnit.SECONDS.sleep(randInt(1,3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }
            }).flatMap(x -> Observable.just(x))
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(5)))
            //.observeOn(Schedulers.from(Executors.newFixedThreadPool(5)))
            .subscribe(new Action1<Object>() {

                @Override public void call(Object o) {
                    System.out.println(o + " " + Thread.currentThread().getName());
                }
            });


        //You can only direct emissions of an Observable from one single thread to another single thread
        // each item emitted need to wait until it finish the chain
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        Observable<Integer> lengths = source
            .subscribeOn(Schedulers.computation())
            .map(String::length);

        lengths.subscribe(sum -> System.out.println("Received " + sum +
            " on thread " + Thread.currentThread().getName()));


        //But after that the observeOn() redirected the emissions to a computation thread
        Observable<Integer> source2 = Observable.range(1,3);

        source2.map(i -> i * 100)
            //.subscribeOn(Schedulers.from(Executors.newFixedThreadPool(5)))
            .observeOn(Schedulers.from(Executors.newFixedThreadPool(5)))
            .doOnNext(i -> System.out.println("Emitting " + i
                + " on thread " + Thread.currentThread().getName()))
            //change the computation to the next thread
            .observeOn(Schedulers.computation())
            .map(i -> i * 10)
            .doOnNext(i -> System.out.println("Emitting2 " + i
                + " on thread " + Thread.currentThread().getName()))
            // switch again to another thread
            .observeOn(Schedulers.io())
            .map(i -> i * 10)
            //.subscribeOn(Schedulers.from(Executors.newFixedThreadPool(5)))
            .map(i -> i * 2)
            .subscribe(i -> System.out.println("Received " + i + " on thread "
                + Thread.currentThread().getName()));


        TimeUnit.SECONDS.sleep(10);
    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
