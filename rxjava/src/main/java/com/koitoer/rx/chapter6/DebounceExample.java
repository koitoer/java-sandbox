package com.koitoer.rx.chapter6;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by mmena on 4/5/17.
 */
public class DebounceExample {

    public static void main(String[] args) throws InterruptedException {
        //Values emited in fixed 50ms
        Observable<BigDecimal> prices = Observable.interval(50, TimeUnit.MILLISECONDS)
                //delay someone from 0 - 100ms randomly
                .flatMap(DebounceExample::randomDelay)
                //debounce tolerance goes down when price increase
                .map(DebounceExample::randomStockPrice)
                .map(BigDecimal::valueOf)
                //.throttleWithTimeout discard events follow shortly by another event
                .debounce(x -> {
                    boolean goodPrice = x.compareTo(BigDecimal.valueOf(160)) > 0;
                    return Observable.empty().delay(goodPrice ? 10: 100, TimeUnit.MILLISECONDS);
                });

        Subscription r = prices.subscribe(new Subscriber<BigDecimal>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            @Override
            public void onNext(BigDecimal bigDecimal) {
                System.out.println(bigDecimal);
            }
        });
        TimeUnit.SECONDS.sleep(10);
        r.unsubscribe();


        System.out.println("Next step");
        try {
            //Returns an Observable that emits a sequential number every specified interval of time. Observable<Long>
            Observable<Long> a = Observable.interval(99, TimeUnit.MILLISECONDS)
                    //emitted by the source ObservableSource that are followed by newer items before a timeout
                    // value expires Observable<Long>
                    .debounce(100, TimeUnit.MILLISECONDS)
                    // but applies a timeout policy for each emitted item.
                    //f the next item isn't emitted within the specified timeout duration starting from its predecessor
                    .timeout(1, TimeUnit.SECONDS);
            a.doOnError(throwable -> System.out.println("error"));
            a.subscribe(System.out::println);
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            System.out.println("timeout exception");
        }

        //Making this a hot observable
        ConnectableObservable<Long> upstrea = Observable.interval(99, TimeUnit.MILLISECONDS).publish();

        //In case of failure of debounce we take the first one
        upstrea.debounce(100, TimeUnit.MILLISECONDS).timeout(1, TimeUnit.SECONDS, upstrea.take(1));

        upstrea.connect();

    }

    static Observable<Long> randomDelay(long x){
        return Observable.just(x).delay((long) (Math.random() * 100), TimeUnit.MILLISECONDS);
    }

    static double randomStockPrice(long x){
        return 100 + Math.random() * 10 +
                (Math.sin(x/100.0)) * 60;
    }
}
