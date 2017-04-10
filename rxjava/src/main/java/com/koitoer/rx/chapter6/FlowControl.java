package com.koitoer.rx.chapter6;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 4/5/17.
 */
public class FlowControl {

    public static void main(String[] args) throws InterruptedException {

        //Periodic Samples
        long startime = System.currentTimeMillis();
        Observable.interval(7, TimeUnit.MILLISECONDS)
                .timestamp()
                .sample(1, TimeUnit.SECONDS)
                .map(ts -> ts.getTimestampMillis() - startime + " ms: " + ts.getValue())
                .take(5)
                .subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);

        Observable<String> names = Observable.just("A", "B", "C", "D", "E", "F", "7", "8", "9", "10");
        Observable<Long> delays = Observable.just(0.1, 0.6, 0.9, 1.1, 3.3, 3.4, 3.5, 3.6, 4.4, 4.8)
                .map(x -> (long)(x * 1000));

        Observable<String> delayedNames = names.zipWith(delays, (n,d) -> Observable.just(n)
                .delay(d, TimeUnit.MILLISECONDS)).flatMap(o-> o);

        delayedNames.sample(1, TimeUnit.SECONDS).subscribe(System.out::println);
        delayedNames.sample(Observable.interval(1, TimeUnit.SECONDS)).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);

        //Get the first or the last one in the interval window.
        delayedNames.throttleFirst(1, TimeUnit.SECONDS).subscribe(System.out::println);
        delayedNames.throttleLast(1, TimeUnit.SECONDS).subscribe(System.out::println);


    }
}
