package com.koitoer.rx.chapter4;

import rx.Observable;

import java.util.UUID;

import static com.koitoer.rx.chapter4.MultithreadingExample.*;

/**
 * Created by mmena on 3/25/17.
 */
public class SubscribeOnObserveOn {

    public static void main(String[] args) {
        log("Starting");

        Observable<String> obs = Observable.create(subscriber -> {
            log("Subscribed");
            subscriber.onNext("A");
            subscriber.onNext("B");
            subscriber.onNext("C");
            subscriber.onNext("D");
            subscriber.onCompleted();
        });

        log("Created");

        obs.subscribeOn(PoolService.schedulerA)
                .flatMap(record -> store(record).subscribeOn(PoolService.schedulerB))
                .observeOn(PoolService.schedulerC)
                .subscribe(
                        x -> log("got " + x),
                        Throwable::printStackTrace,
                        () -> log("Completed")
                );
        log("Exiting");
    }

    private static Observable<UUID> store(String record) {
        return Observable.create(subscriber -> {
            log("Storing "  + record);
            subscriber.onNext(UUID.randomUUID());
            subscriber.onCompleted();
        });
    }


}
