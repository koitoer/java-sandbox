package android.reactive.koitoer.com.reactiveapp.service;

import android.reactive.koitoer.com.reactiveapp.dto.SearchResult;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by mmena on 4/16/17.
 */

public class CountService {

    private MeetupService meetupService;
    private GeoNameService geoNameService;

    public void execute() throws InterruptedException {

        meetupService = new MeetupService();
        meetupService.initApi();
        geoNameService = new GeoNameService();
        geoNameService.initApi();

        double warsawLat = 52.229841;
        double warsawLon = 21.011736;

        Observable<SearchResult> s = geoNameService.search("Kobylka");
        s.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);


        Observable<Long> totalPopulation  = meetupService.get(warsawLat, warsawLon)
                .flatMap(x -> geoNameService.populationOf(x))
                .reduce(0L, (x,y) -> x + y);

        totalPopulation.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);
    }
}
