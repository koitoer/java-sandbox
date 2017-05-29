package com.koitoer.rx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;

/**
 * Created by mmena on 5/9/17.
 */
public class AppRes {

    public static void main(String[] args) throws InterruptedException {

        String[] list = new String[] { "US", "UK", "MX"};

        System.out.println("Start the iteration");

        Map<String, Double> siteList = new HashMap<>();

        Observable<String> observable = Observable.from(list);

        Observable<String> mapObservable = observable.flatMap(item -> Observable.just(item).subscribeOn(Schedulers.io()));



        BlockingObservable<String> blockingObservable = mapObservable.map(site -> {
            System.out.println(site);
            if(site.equals("US")){
                System.out.println("ERRORR");
                //throw new IllegalStateException("SSSitesss");

            }
            return site;
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {

            @Override public Observable<? extends String> call(Throwable throwable) {
                return Observable.just("A");
            }
        }).subscribeOn(Schedulers.io()).toBlocking();

        blockingObservable.subscribe(new Subscriber<String>() {

            @Override public void onCompleted() {
                System.out.println("Completed" + Thread.currentThread().getName());

            }

            @Override public void onError(Throwable e) {
                System.out.println("errorr - Runtime " + Thread.currentThread().getName());
                this.onNext("SS");


            }

            @Override public void onNext(String s) {
                System.out.println("Expected string " + s);

            }
        });

        /**
        blockingObservable.subscribe(new Action1<String>() {

            @Override public void call(String s) {
                System.out.println("Expected string " + s);
            }
        }, new Action1<Throwable>() {

            @Override public void call(Throwable throwable) {
                if(throwable instanceof RuntimeException)
                    System.out.println("errorr - Runtime");

            }
        }, new Action0() {

            @Override public void call() {
                System.out.println("Completed");
            }
        });
        **/


        /**
        BlockingObservable blockingObservable = Observable.from(list).flatMap(item -> Observable.just(item)
            .subscribeOn(Schedulers.io())
            .map(site -> {
                System.out.println(Thread.currentThread().getName() + " : " + site);
                try {
                    TimeUnit.SECONDS.sleep(5);
                    if(site.equals("US")){
                        System.out.println("Site not allowed");
                        throw new RuntimeException("Site bad");
                    }
                } catch (InterruptedException e) {
                    new RuntimeException(e);
                }
                String sites = site + "s";
                return Pair.of(sites, Math.random());
            })).toBlocking();


        mapObservable.subscribe(new Action1<Pair<String, Double>>() {

                                         @Override public void call(Pair<String, Double> stringDoublePair) {
                                             siteList.put(stringDoublePair.getKey(), stringDoublePair.getValue());
                                         }
                                     }, new Action1<Throwable>() {

                                         @Override public void call(Throwable throwable) {
                                             if (throwable instanceof RuntimeException) {
                                                 System.out.println("A runtime caught");
                                             }
                                         }
                                     }, new Action0() {

                                         @Override public void call() {
                                             System.out.println("Completed the call");
                                         }
                                     }

        );
         **/




        System.out.println("Iteration complete");
        System.out.println(siteList);

        TimeUnit.SECONDS.sleep(10);
        System.out.println("TimeOut complete");


    }

    private static Observable<Void> method(String input) {
        System.out.println(input);
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }


}
