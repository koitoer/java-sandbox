package com.koitoer.rx.chapter6;

import rx.Observable;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 4/5/17.
 */
public class BufferingDecision {

    private static final LocalTime BUSINESS_START = LocalTime.of(9,0);
    private static final LocalTime BUSINESS_END  = LocalTime.of(17,0);

    public static void main(String[] args) {
        Observable<Duration> inside = Observable.interval(1, TimeUnit.SECONDS)
                .filter(x -> isBusinessHour()).map(x -> Duration.ofMillis(100));

        Observable<Duration> outside = Observable.interval(5, TimeUnit.SECONDS)
                .filter(x -> !isBusinessHour()).map(x -> Duration.ofMillis(200));

        Observable<Duration> openings = Observable.merge(inside, outside);

        Observable<String> upstream = Observable.just("a","n");

        Observable<List<String>> samples = upstream.buffer(openings);
        Observable<List<String>> samples2= upstream.buffer(openings,
                duration -> Observable.empty().delay(duration.toMillis(), TimeUnit.MILLISECONDS));


    }

    private static Boolean isBusinessHour() {
        ZoneId zone = ZoneId.of("Europe/Warsaw");
        ZonedDateTime zdt = ZonedDateTime.now(zone);
        LocalTime localtime = zdt.toLocalTime();
        return !localtime.isBefore(BUSINESS_START) && !localtime.isAfter(BUSINESS_END);
    }


}
