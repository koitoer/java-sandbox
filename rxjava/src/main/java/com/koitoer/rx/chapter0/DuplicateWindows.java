package com.koitoer.rx.chapter0;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by mmena on 11/20/17.
 */
public class DuplicateWindows {

    public static void main(String[] args) throws InterruptedException {

        Observable a = Observable.range(1, 10);
        Observable b = Observable.interval(1000L, TimeUnit.MILLISECONDS).timeInterval();

        Observable c =Observable.from(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 19, 1,1,1,1,1,1, 2)).window(5)
            .distinctUntilChanged().flatMap((Func1<Observable<Integer>, Observable<?>>) integerObservable -> {
                System.out.println(integerObservable);
                return integerObservable;
            });

        c.subscribe(o -> System.out.println("Window duplicated -> " + o));

        a.subscribe(o -> System.out.println(o));



        TimeUnit.SECONDS.sleep(15);
    }

}
