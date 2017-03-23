package com.koitoer.rx.chapter2;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by mmena on 3/17/17.
 */
public class Example {

    public static void main(String[] args) {

        Observer<String> observer = new Observer<String>() {
            public void onCompleted() {
                System.out.println("No more");
            }

            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            public void onNext(String s) {
                System.out.println("onNext " + s);
                if(s.equals("S5")){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        System.out.println("Create the observable object");
        Observable<String> observable =  Observable.from(new String[]{"S1","S2","S3","S4","S5"});
        System.out.println("Creation nothing happens");
        observable.subscribe(observer);
        System.out.println("Already Subscribed");


        //Using a subscription to unsubscribe after the event
        System.out.println("Creating a subscription");
        Subscription subscription = observable.subscribe(System.out::println);
        System.out.println("Calling unsubscribe");
        subscription.unsubscribe();

        //Using a subscription to unsubscribe in the middle of events
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable throwable) {
                System.out.println("ERROR");
            }

            @Override
            public void onNext(String s) {
                if(s.equals("S4")){
                    this.unsubscribe();
                }
                System.out.println("Subscriber onNext" +s);
            }
        };

        System.out.println("Using a Subscriber");
        observable.subscribe(subscriber);
        System.out.println("We never print S5");


    }
}
