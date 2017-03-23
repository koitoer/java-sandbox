package com.koitoer.rx.chapter2;



import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/18/17.
 */
public class ObservableCreation {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Line 0");
        Observable.range(1,10).subscribe(System.out::print);
        System.out.println("Line 1");
        Observable.range(1,10).subscribe(i -> System.out.print(i));
        System.out.println("Line 2");
        Observable<Integer> ints = Observable.create(new Observable.OnSubscribe<Integer>(){
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                log("Create");
                subscriber.onNext(10);
                subscriber.onNext(100);
                subscriber.onCompleted();
                log("Complete");
            }
        });
        ints.subscribe(System.out::print);

        System.out.println("Line 3 - Managing multiple subscriber");
        ints.subscribe(integer -> System.out.println("AA " + integer));
        ints.subscribe(integer -> System.out.println("BB " + integer));


        System.out.println("Line 3 - Managing multiple subscriber with cache");
        Observable<Integer> observableWithCache = ints.cache();
        observableWithCache.subscribe(integer -> System.out.println("AA " + integer));
        observableWithCache.subscribe(integer -> System.out.println("BB " + integer));

        System.out.println("Line 4 - Infinite Stream");
        Observable<BigInteger> naturalNumber = Observable.create(subscriber -> {
            //Spawn a thread to unblock the main thread
            Runnable r = () -> {
                BigInteger i = BigInteger.ZERO;
                //Check the state before send more events
                while(!subscriber.isUnsubscribed()){
                    i = i.add(BigInteger.ONE);
                    subscriber.onNext(i);
                }
            };
            new Thread(r).start();
        });

        Subscription subscription = naturalNumber.subscribe(x -> log(x.toString()));
        logn("We will wait some sec");
        Thread.sleep(1);
        subscription.unsubscribe();
        logn("We unsubscribe");


        System.out.println("Line 5 - Observable fromCallable to avoid try-catch");
        Observable<String> myOnNext = Observable.fromCallable(() -> "AAA");
        Observable<String> myError = Observable.fromCallable(() -> functionThrowException("AAA"));
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable throwable) {
                logn("There is an error over there " + throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                logn("No error the string is " + s);
            }
        };

        myOnNext.subscribe(subscriber);
        myError.subscribe(subscriber);


        System.out.println("Line 6 - Timer and Interval");
        Observable.timer(1, TimeUnit.SECONDS).subscribe(zero -> logn(zero.toString()));
        Thread.sleep(3000);

    }

    public static String functionThrowException(String id) throws Exception {
        throw new Exception(id);
    }

    public static void logn(String s){
        System.out.println(s);
    }

    public static void log(String s){
        System.out.println(s);
    }
}
