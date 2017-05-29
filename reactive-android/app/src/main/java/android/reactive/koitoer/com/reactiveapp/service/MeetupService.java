package android.reactive.koitoer.com.reactiveapp.service;

import android.reactive.koitoer.com.reactiveapp.api.MeetupApi;
import android.reactive.koitoer.com.reactiveapp.dto.Cities;
import android.reactive.koitoer.com.reactiveapp.dto.City;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;


/**
 * Created by mmena on 4/16/17.
 */

public class MeetupService {

    public MeetupApi meetupApi;

    public void initApi(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.meetup.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

         this.meetupApi = retrofit.create(MeetupApi.class);
    }

    public Observable<String> get(double lat, double lon){
        Observable<Cities> citiesObservable = meetupApi.listCities(lat, lon);
        Observable<City> cities = citiesObservable.concatMapIterable(x -> x.getResults());
        Observable<String> map = cities.filter(city -> city.distanceTo(lat, lon) < 50 )
                .map(City::getCity);

        return map;
    }
}
