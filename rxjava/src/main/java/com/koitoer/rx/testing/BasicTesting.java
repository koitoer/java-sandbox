package com.koitoer.rx.testing;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by mmena on 11/28/17.
 */
public class BasicTesting {

    public static void main(String[] args) throws InterruptedException {
        Observable a = Observable.from(Arrays.asList("a", "b", "b", "d"));
        TestSubscriber testSubscriber = TestSubscriber.create();

        a.subscribe(testSubscriber);

        testSubscriber.unsubscribe();

        Assertions.assertThat(testSubscriber.getOnNextEvents()).isEqualTo(Arrays.asList("a", "b", "b", "d"));
        Assertions.assertThat(testSubscriber.getValueCount()).isEqualTo(4);
        Assertions.assertThat(testSubscriber.getCompletions()).isEqualTo(1);
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertUnsubscribed();

        TimeUnit.SECONDS.sleep(5);
    }

}
