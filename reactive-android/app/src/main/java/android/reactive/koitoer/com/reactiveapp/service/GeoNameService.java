package android.reactive.koitoer.com.reactiveapp.service;

import android.reactive.koitoer.com.reactiveapp.api.GeoNames;
import android.reactive.koitoer.com.reactiveapp.dto.Geoname;
import android.reactive.koitoer.com.reactiveapp.dto.SearchResult;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by mmena on 4/16/17.
 */

public class GeoNameService {

    public GeoNames geoNames;

    public void initApi(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.geonames.org/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.geoNames = retrofit.create(GeoNames.class);

    }


    public Observable<Integer> populationOf(String query){
        return search(query)
                .concatMapIterable(SearchResult::getGeonames)
                .map(Geoname::getPopulation)
                .filter(p -> p != null)
                .singleOrDefault(0)
                .doOnError(th -> System.out.println("Falling back to 0 from " + query + " : " + th.getMessage()))
                .onErrorReturn(th -> 0)
                .subscribeOn(Schedulers.io());
    }

    //http://api.geonames.org/searchJSON?username=koitoer&maxRows=10&q=Warsaw&style=LONG
    public Observable<SearchResult> search(String query) {
        System.out.println("Action query " + query);
        return geoNames.search(query , 1, "LONG", "koitoer");
    }

}
