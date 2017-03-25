package com.koitoer.rx.chapter4;

import rx.Observable;

import java.util.concurrent.TimeUnit;

import static com.koitoer.rx.chapter4.MultithreadingExample.log;

/**
 * Created by mmena on 3/25/17.
 */
public class ObservableOnExample {

    public static void main(String[] args) throws InterruptedException {
        //If we have a sync code block we WONT do this
        //Observable is lazy and immutable
        Observable<String> obs = Observable.create(subscriber -> {

            log("Subscribed");
            Runnable code = () -> {
                subscriber.onNext("A");
                subscriber.onNext("B");
                subscriber.onCompleted();
            };

            new Thread(code, "Async").start();
        });


        obs.subscribe(MultithreadingExample::log);

        TimeUnit.SECONDS.sleep(5);

        //RXJava create a single Worker instance
        //SubscribeOn concurrency and behavior
        log("Starting");
        Observable<String> obs1 = simple();
        log("Created");
        obs1.subscribeOn(PoolService.schedulerA)
                //Practically ignored adding some overhead
                .subscribeOn(PoolService.schedulerB)
                .subscribe(
                        x -> log(x), Throwable::printStackTrace, () -> log("Completed")
                );
        log("Exiting");

        System.out.println("Init next example");

        log("Starting");
        Observable<String> obs2 = simple();
        log("Created");
        obs2.doOnNext(x -> log(x))
                .map(x -> x + '1')
                .doOnNext(x -> log(x))
                .map(x -> x + '2')
                .subscribeOn(PoolService.schedulerA)
                .doOnNext(x -> log(x))
                .subscribe(
                        x -> log(x), Throwable::printStackTrace, () -> log("Completed")
                );
        log("Exiting");

    }

    private static Observable<String> simple() {
        return Observable.create(subscriber -> {
            log("Subscribed");
            subscriber.onNext("A");
            subscriber.onNext("B");
            subscriber.onCompleted();
        });
    }
}
