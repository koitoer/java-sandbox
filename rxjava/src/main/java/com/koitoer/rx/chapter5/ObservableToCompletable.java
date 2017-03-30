package com.koitoer.rx.chapter5;

import rx.Observable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by mmena on 3/30/17.
 */
public class ObservableToCompletable {

    public static void main(String[] args) {

        //Observable<T> to CompletableFuture<T> -- One item emitted

        //Observable<T> to CompletableFuture<List<T>> -- Several item emitted
    }

    /**
     * Observable to Competable
     * @param observable
     * @param <T>
     * @return
     */
    static <T>CompletableFuture<T> toFuture(Observable<T> observable){
        CompletableFuture<T> promise = new CompletableFuture<T>();
        //Take in consideration we subscribe here to the cold observable
        observable.single().subscribe(
                promise::complete,
                promise::completeExceptionally
        );
        return promise;
    }

    static <T> CompletableFuture<List<T>> toFutureList(Observable<T> observable){
        return toFuture(observable.toList());
    }

}
