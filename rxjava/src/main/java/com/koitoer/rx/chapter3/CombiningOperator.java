package com.koitoer.rx.chapter3;

import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static rx.Observable.just;

/**
 * Created by mmena on 3/21/17.
 */
public class CombiningOperator {

    public static void main(String[] args) throws InterruptedException {

        Observable<String> myObservable =  just("a","b","B","z");

        //Concat wait until completion of the first and subscribe to the next
        Observable.concat(myObservable.doOnSubscribe(()-> System.out.println("Sub1")).take(1),
                myObservable.doOnSubscribe(()-> System.out.println("Sub1")).takeLast(1))
                .forEach(System.out::println);

        System.out.println("Example with");
        Observable observable = myObservable.concatWith(myObservable);
        observable.forEach(System.out::println);


        Observable<String> alice = speak("To be, or not to be: that is the question", 100);
        Observable<String> bob = speak("Though this be madness, yet there is mething in 't'", 90);
        Observable<String> jane = speak("There are more things in heaven than Earth", 100);


        //Emit it does not matter the order
        Observable.merge(
                alice.map(w -> "Alice " + w),
                bob.map(w -> "Bob " + w),
                jane.map(w -> "Jane " + w)
        ).forEach(System.out::println);

        TimeUnit.SECONDS.sleep(10);

        //First then and then
        Observable.concat(
                alice.map(w -> "Alice " + w),
                bob.map(w -> "Bob " + w),
                jane.map(w -> "Jane " + w)
        ).forEach(System.out::println);

        TimeUnit.SECONDS.sleep(12);

        //switchOnNext subscribe to a list of observables and each time a new observable arrives, it will switch
        System.out.println("Example switchOnNext !!");
        System.out.println("Example switchOnNext !!");
        System.out.println("Example switchOnNext !!");

        TimeUnit.SECONDS.sleep(5);

        Random rnd = new Random();
        Observable<Observable<String>> quotes = Observable.just(
                alice.map(w -> "Alice ->" + w).doOnUnsubscribe(() -> System.out.println("Discard Alice")),
                bob.map(w -> "Bob ->" + w).doOnUnsubscribe(() -> System.out.println("Discard Bob")),
                jane.map(w -> "Jane ->" + w).doOnUnsubscribe(() -> System.out.println("Discard Jane"))
                .flatMap(innerObs -> Observable.just(innerObs))
                .delay(rnd.nextInt(5), TimeUnit.SECONDS)
        );

        Observable.switchOnNext(quotes).subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(10);

    }


    static Observable<String> speak (String quote, long millis){
        String[] tokens = quote.replaceAll("[:,]","").split(" ");
        Observable<String> words = Observable.from(tokens);
        Observable<Long> absoluteDelay = words.map(String::length)
                                            .map(len -> len * millis)
                                            .scan((total, current) ->  total + current);
        return words
                .zipWith(absoluteDelay.startWith(0L), Pair::of)
                .flatMap(pair -> just(pair.getLeft())
                .delay(pair.getRight(), TimeUnit.MILLISECONDS));
    }
}
