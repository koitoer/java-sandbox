package com.koitoer.rx.chapter2;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subscriptions.Subscriptions;
import twitter4j.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/18/17.
 */
public class TwitterSubject {

    private final static PublishSubject<Status> publishSubject = PublishSubject.create();

    public final static TwitterStream twitterStream =  new TwitterStreamFactory(TwitterExample.cbb).getInstance();


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Using publishSubject to manage several Subscribers");
        TwitterSubject twitterSubject = new TwitterSubject();
        Subscription subscription1 = twitterSubject.observe().subscribe((i) -> System.out.println("Sub 1 " + i.getCreatedAt()));
        Subscription subscription2 = twitterSubject.observe().subscribe((i) -> System.out.println("Sub 2 " + i.getLang()));

        TimeUnit.SECONDS.sleep(2);

        subscription1.unsubscribe();
        subscription2.unsubscribe();
        System.out.println("End program");

        subscription1 = twitterSubject.observe().subscribe((i) -> System.out.println("Sub 3 " + i.getCreatedAt()));
        TimeUnit.SECONDS.sleep(2);
        subscription1.unsubscribe();


        System.out.println("Trying to close adding a subscriber to hear the onCompleted event");

        Subscriber subscriber = new Subscriber<Status>() {
            @Override
            public void onCompleted() {
                System.out.println("closing the stream");
                twitterStream.shutdown();
            }

            @Override
            public void onError(Throwable throwable) {}

            @Override
            public void onNext(Status status) {}
        };

        publishSubject.subscribe(subscriber);
        publishSubject.onCompleted();

    }


    public TwitterSubject(){

        twitterStream.addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("Has observers "  + publishSubject.hasObservers());
                if(publishSubject.hasObservers()){
                    publishSubject.onNext(status);
                }
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
                publishSubject.onError(e);
            }
        });

        twitterStream.sample();

    }


    public Observable<Status> observe(){
        return publishSubject;
    }
}
