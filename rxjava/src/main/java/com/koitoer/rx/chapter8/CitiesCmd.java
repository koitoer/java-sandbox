package com.koitoer.rx.chapter8;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.koitoer.rx.chapter8.dto.Cities;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 4/16/17.
 */
public class CitiesCmd extends HystrixObservableCommand<Cities> {

    private final MeetupApi api;
    private final double lat;
    private final double lon;

    protected CitiesCmd(MeetupApi api, double lat, double lon) {
        super(HystrixCommandGroupKey.Factory.asKey("Meetup"));
        this.api = api;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    protected Observable<Cities> construct() {
        return api.listCities(lat, lon);
    }

    public static void main(String[] args) throws InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.meetup.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper)).build();

        MeetupApi meetupApi = retrofit.create(MeetupApi.class);

        CitiesCmd citiesCmd = new CitiesCmd(meetupApi, 19.432608, -99.133209);

        citiesCmd.toObservable().flatMapIterable(x -> x.getResults()).map(x -> x.getCity())
                .sorted().subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);

        MeetupApi meetupApi1 = BDDMockito.mock(MeetupApi.class);
        BDDMockito.given(meetupApi1.listCities(BDDMockito.anyDouble(), BDDMockito.anyDouble()))
                .willReturn(Observable.<Cities>error(new RuntimeException("Broken"))
                    .doOnSubscribe(() -> System.out.println("Invoking"))
                        .delay(2, TimeUnit.SECONDS));


        Observable.interval(50, TimeUnit.MILLISECONDS)
                .doOnNext(x -> System.out.println("Requesting "))
                .flatMap(x -> new CitiesCmd(meetupApi1, 52.22984, 21.011736).toObservable()
                        .onErrorResumeNext(ex -> Observable.empty()),5).subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);

    }
}
