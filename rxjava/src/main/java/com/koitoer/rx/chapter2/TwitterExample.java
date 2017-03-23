package com.koitoer.rx.chapter2;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/18/17.
 */
public class TwitterExample {

    static ConfigurationBuilder cb ;

    public static final Configuration cbb;

    static{
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("hKF4dbcgFSnB08AziM193z9zL")
                .setOAuthConsumerSecret("KmE4aqPmBCu2P7mv8TxW0rcaxrYp8To2mjpkEgyhRSaU87PL3b")
                .setOAuthAccessToken("221256066-9XwvodgNaZyMnC6QM7Nf8WWnYyaO0Wdj3vBOJVVQ")
                .setOAuthAccessTokenSecret("umMt8LvhRSuFwoi9LRcwlKexxiosa16Wb1YzJqE0U2CGk");
        cbb = cb.build();
    }

    public static void main(String[] args) throws InterruptedException {

        TwitterStream twitterStream = new TwitterStreamFactory(cbb).getInstance();
        twitterStream.addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                if(status.getText().contains("sex")){
                    System.out.println(status.getUser().getEmail() + "||" + status.getText().toLowerCase());
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
                System.out.println("On exception " + e.getMessage());
            }
        });

        twitterStream.sample();
        TimeUnit.SECONDS.sleep(10);
        twitterStream.shutdown();

        System.out.println("Using an observable behavior");
        Observable<Status> twitterObservable = observe();
        Subscription twitterSubsciption = twitterObservable.subscribe(status -> System.out.println("TEXT" + status.getText()));
        Subscription twitterSubsciption2 = twitterObservable.subscribe(status -> System.out.println("TEXTAA" + status.getText()));
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Enough lets un subscribe");
        twitterSubsciption.unsubscribe();
        twitterSubsciption2.unsubscribe();
        System.out.println("Enough lets un subscribe DONE");

    }


    static Observable<Status> observe(){
        return Observable.create(subscriber -> {
                TwitterStream twitterStream = new TwitterStreamFactory(cbb).getInstance();
                twitterStream.addListener(new StatusListener() {
                    @Override
                    public void onStatus(Status status) {
                        if(subscriber.isUnsubscribed()){
                            System.out.println("Unsubscription");
                            twitterStream.shutdown();
                        }
                        if(status.getText().contains("just")) {
                            subscriber.onNext(status);
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
                        subscriber.onError(e);
                    }
                });
            twitterStream.sample();
            subscriber.add(Subscriptions.create(twitterStream::shutdown));
        });
    }
}
