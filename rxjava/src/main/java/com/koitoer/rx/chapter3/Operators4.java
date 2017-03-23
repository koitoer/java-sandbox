package com.koitoer.rx.chapter3;

import rx.Observable;
import rx.Subscription;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/19/17.
 */
public class Operators4 {

    public static void main(String[] args) throws InterruptedException {

        Observable<Long> red = Observable.interval(10, TimeUnit.MILLISECONDS);
        Observable<Long> green = Observable.interval(15, TimeUnit.MILLISECONDS);

        Subscription subscription = red.subscribe();
        Subscription subscription1 = green.subscribe();

        Observable.zip(red.timestamp(), green.timestamp(),
                (r,g) -> r.getTimestampMillis() - g.getTimestampMillis())
                .forEach(System.out::println);

        TimeUnit.SECONDS.sleep(5);

        subscription.unsubscribe();
        subscription1.unsubscribe();

        System.out.println("Test 2 with combineLatest");
        TimeUnit.SECONDS.sleep(2);
        Observable observable =  Observable.combineLatest(Observable.interval(17, TimeUnit.MILLISECONDS).map(x -> "S"+x),
                Observable.interval(17, TimeUnit.MILLISECONDS).map(x -> "F"+x),
                (s,f) -> f + ":" + s);

        Subscription subscription2 = observable.subscribe(x -> System.out.println(x));
        TimeUnit.SECONDS.sleep(3);
        subscription2.unsubscribe();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("Test 3 with combineLatest");
        Observable observable2 =Observable.combineLatest(Observable.interval(17, TimeUnit.MILLISECONDS).map(x -> "S"+x),
                Observable.interval(10, TimeUnit.MILLISECONDS).map(x -> "F"+x),
                (s,f) -> f + ":" + s);

        Subscription subscription3 = observable2.subscribe(x -> System.out.println(x));
        TimeUnit.SECONDS.sleep(3);
        subscription3.unsubscribe();
        TimeUnit.SECONDS.sleep(3);

        System.out.println("Test 4 for amb()");
        Observable observable3 = Observable.amb(stream(100,17, "S"), stream(200, 10, "F"));
        Subscription subscription4 = observable3.subscribe(x -> System.out.println(x));
        TimeUnit.SECONDS.sleep(3);
        subscription3.unsubscribe();
        TimeUnit.SECONDS.sleep(3);
    }


    static Observable<String> stream(int initialDelay, int interval, String name){
        return Observable.interval(initialDelay, interval, TimeUnit.MILLISECONDS)
                .map(x -> name+x)
                .doOnUnsubscribe(() -> System.out.println("Un-Subscribe to " + name))
                .doOnSubscribe(() -> System.out.println("Subscribe to " + name));
    }

}
