package com.koitoer.rx.chapter6;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 4/10/17.
 */
public class BackPressureExample {

    //Feedback channel between consumer and producers, to define how much load they can take
    //Producers are capable of hrotting themselves, for consumer that does not have way
    //or control fequency  source is external or hot, that wont work.

    //Reactive streams

    public static void main(String[] args) throws InterruptedException {
        //Range use the same thread that the consumer
        Observable<Dish> dishes = Observable.range(1, 1_000_000).map(Dish::new);

        /**
        Subscription subscription = dishes.subscribe(x ->{
            System.out.println("Washing " + x);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(5);
        subscription.unsubscribe();

         **/

        Subscription subscription1 =  dishes.observeOn(Schedulers.io()).subscribe(x ->{
            System.out.println("Washing " + x);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        subscription1.unsubscribe();
        TimeUnit.SECONDS.sleep(5);

        BackPressureExample.myRange(1, 1_000_000)
                .map(Dish ::new)
                .observeOn(Schedulers.io())
                .subscribe(x -> {
                    System.out.println("Washing " + x);
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        TimeUnit.SECONDS.sleep(5);



    }

    static Observable<Integer> myRange(int from, int count){
        return Observable.create(subscriber -> {
            int i = from;
            while (i <from + count){
                 if(!subscriber.isUnsubscribed()){
                     subscriber.onNext(i++);
                 }   else {
                     return;
                 }
            }

            subscriber.onCompleted();
        });
    }

}
