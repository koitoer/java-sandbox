package com.koitoer.rx.testing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by mmena on 11/28/17.
 */
public class TimeTesting {

    private static final String[] WORDS = new String[]{"bad", "good", "night"};

    //@Rule
    //public final TestScheduleRule testScheduleRule = new TestScheduleRule();

    @Test
    public void testUsingScheduleRule(){

        TestSubscriber<String> subscriber = new TestSubscriber<>();

        Observable<String> observable = Observable.from(WORDS)
            .zipWith(Observable.interval(1, SECONDS),
            (string, index) -> String.format("%2d. %s", index, string));

        observable.subscribeOn(Schedulers.computation())
            .subscribe(subscriber);


        subscriber.assertNoValues();
        subscriber.assertNotCompleted();

         // when:
        //testScheduleRule.getTestScheduler().advanceTimeBy(1, SECONDS);

        // then:
        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        subscriber.assertValues(" 0. bad");

        // when:
        //testScheduleRule.getTestScheduler().advanceTimeTo(3, SECONDS);
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        subscriber.assertValueCount(3);
    }

    public static void main(String[] args) throws InterruptedException {
        TestSubscriber<String> subscriber = new TestSubscriber<>();

        Observable<String> observable = Observable.from(WORDS)
            .zipWith(Observable.interval(1, SECONDS),
                (string, index) -> String.format("%2d. %s", index, string));

        observable.subscribeOn(Schedulers.computation()).subscribe(new Action1<String>() {

            @Override public void call(String s) {
                System.out.println(s);
            }
        });


        observable.subscribeOn(Schedulers.computation()).subscribe(subscriber);

        System.out.println("Start await");

       // System.out.println(subscriber.getValueCount());

        Awaitility.await().timeout(10, SECONDS).pollInterval(Duration.FIVE_SECONDS)
          .until(subscriber::getValueCount, Matchers.greaterThan(1));

        System.out.println(subscriber.getValueCount());

        System.out.println("Start assertions");
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        Assert.assertThat(subscriber.getOnNextEvents(), Matchers.hasItem(" 0. bad"));

        System.out.println("Start assertions 2");
        TimeUnit.SECONDS.sleep(10);


    }


}
