package com.koitoer.rx.chapter8;

import com.koitoer.rx.chapter8.dto.Cities;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mmena on 4/16/17.
 */

public interface MeetupApi {

    @GET("/2/cities")
    Observable<Cities> listCities(@Query("lat") double lat, @Query("lon") double lon);
}
