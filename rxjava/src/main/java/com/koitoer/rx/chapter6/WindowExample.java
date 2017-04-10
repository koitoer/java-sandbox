package com.koitoer.rx.chapter6;

import rx.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 4/5/17.
 */
public class WindowExample {

    public static void main(String[] args) {
        //Receive and put into fixed size lists
        //Receive time unit, fixed periods of time
        //Receive custom observable, marking start-end of batch

        Observable<String> keyEvents = Observable.just("1click", "2click");
        //Making a list for all the incoming events
        Observable<List<String>> observableEvents  =keyEvents.buffer(1, TimeUnit.SECONDS);
        Observable<Integer> eventsPerSecond = observableEvents.map(List::size);

        //Not need to create a list, this is a stream of streams,
        Observable<Observable<String>> windows = keyEvents.window(1, TimeUnit.SECONDS);
        //No internal buffering just get the stream each second.
        Observable<Integer> eventsPerSec = windows.flatMap(e ->  e.count());
    }
}
