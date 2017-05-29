package android.reactive.koitoer.com.reactiveapp.api;

import android.reactive.koitoer.com.reactiveapp.dto.SearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mmena on 4/16/17.
 */
public interface GeoNames {


    @GET("/searchJSON")
    Observable<SearchResult> search(@Query("q") String query, @Query("maxRows") int maxRows,
                                    @Query("style") String style, @Query("username") String user);
}
