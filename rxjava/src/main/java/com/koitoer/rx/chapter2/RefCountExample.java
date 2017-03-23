package com.koitoer.rx.chapter2;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;
import twitter4j.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/18/17.
 */
public class RefCountExample {


    public static void main(String[] args) throws InterruptedException {
        Observable<Status> observable =  Observable.create(subscriber -> {
            System.out.println("Establish ...");
            TwitterStream twitterStream = new TwitterStreamFactory(TwitterExample.cbb).getInstance();
            twitterStream.addListener(new StatusListener() {
                @Override
                public void onStatus(Status status) {
                    subscriber.onNext(status);
                }

                @Override
                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

                }

                @Override
                public void onTrackLimitationNotice(int i) {

                }

                @Override
                public void onScrubGeo(long l, long l1) {

                }

                @Override
                public void onStallWarning(StallWarning stallWarning) {

                }

                @Override
                public void onException(Exception e) {
                    subscriber.onError(e);
                }
            });

            //Adds a Subscription to this Subscriber's list of subscriptions if this list is not marked as unsubscribed.
            //Creates and returns a Subscription that invokes the given Action0 when unsubscribed.
            subscriber.add(Subscriptions.create(() -> {
                System.out.println("Closing");
                twitterStream.shutdown();
            }));

            twitterStream.sample();
        });

        //TEST1
        /**
        Subscription subscription1 = observable.subscribe();
        System.out.println("Sub 1");
        Subscription subscription2  = observable.subscribe();
        System.out.println("Sub 2");

        TimeUnit.SECONDS.sleep(3);
        subscription1.unsubscribe();
        System.out.println("Drop Sub 1");
        subscription2.unsubscribe();
        System.out.println("Drop Sub 2");
         **/


        //Way of coordinate multiples Subscribers
        TimeUnit.SECONDS.sleep(3);
        ConnectableObservable<Status> connectableObservable = observable.publish();
        observable = connectableObservable.refCount();

        Subscription subscription1 = observable.subscribe((x) -> System.out.println(x));
        System.out.println("Sub 1");
        Subscription subscription2  = observable.subscribe();
        System.out.println("Sub 2");

        TimeUnit.SECONDS.sleep(3);
        subscription1.unsubscribe();
        System.out.println("Drop Sub 1");
        subscription2.unsubscribe();
        System.out.println("Drop Sub 2");


        System.out.println("Test Publish method");
        TimeUnit.SECONDS.sleep(5);
        ConnectableObservable<Status> connectableObservable2 = observable.publish();

        Subscription subscription3 = connectableObservable2.subscribe((x) -> System.out.println(x));
        System.out.println("Sub 3");
        Subscription subscription4  = connectableObservable2.subscribe();
        System.out.println("Sub 4");

        TimeUnit.SECONDS.sleep(10);
        System.out.println("Nothing happens until we register all the subscribers");
        TimeUnit.SECONDS.sleep(3);
        Subscription endSubscription = connectableObservable2.connect();
        System.out.println("START");

        TimeUnit.SECONDS.sleep(3);
        subscription3.unsubscribe();
        System.out.println("Drop Sub 3");
        subscription4.unsubscribe();
        System.out.println("Drop Sub 4");

        endSubscription.unsubscribe();


    }

}
