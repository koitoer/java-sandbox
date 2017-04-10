package com.koitoer.rx.chapter6;

import rx.Observable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by mmena on 4/5/17.
 */
public class BufferingEvents {

    public static void main(String[] args) throws InterruptedException {

        //Store 3 elements in the buffer
        Observable.range(1,7).buffer(3).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);

        //Moving window, conserve the N elements
        Observable.range(1,7).buffer(3,1).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);

        //Window can be larger so we can skip records
        //Moving window, conserve the N elements
        Observable.range(1,7).buffer(1,2).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);

        //Random
        Random random = new Random();
        Observable.defer(() ->  Observable.just(random.nextGaussian()))
                .repeat(100)
                .buffer(100,1)
                .map(BufferingEvents::averageOfList)
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);

        //Buffer by period of time
        Observable<String> names = Observable.just("A", "B", "C", "D", "E", "F", "7", "8", "9", "10");
        Observable<Long> delays = Observable.just(0.1, 0.6, 0.9, 1.1, 3.3, 3.4, 3.5, 3.6, 4.4, 4.8)
                .map(x -> (long)(x * 1000));

        Observable<String> delayedNames = names.zipWith(delays, (n,d) -> Observable.just(n)
                .delay(d, TimeUnit.MILLISECONDS)).flatMap(o-> o);
        delayedNames.buffer(1, TimeUnit.SECONDS).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);
    }

    private static double averageOfList(List<Double> doubles) {
        return doubles.stream().collect(Collectors.averagingDouble(x-> x));
    }
}
