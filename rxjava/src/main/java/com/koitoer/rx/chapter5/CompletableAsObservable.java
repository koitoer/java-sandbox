package com.koitoer.rx.chapter5;

import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * Created by mmena on 3/30/17.
 */
public class CompletableAsObservable {

    public static void main(String[] args) {

        Observable<CompetableFutureExample.Agency> agencies =
                Observable.from(Arrays.asList(new CompetableFutureExample.Agency("Expedia"),
                        new CompetableFutureExample.Agency("Hotels.com")));

        Observable<String> user = rxFindById(1L);
        Observable<String> location = rxLocate();

        Observable<String> ticket = user.zipWith(location, (us, loc) ->
                agencies.flatMap(agency -> Util.observeFromCompetable(agency.searchAsync(us, loc))).first())
                .flatMap(x->x).flatMap(CompletableAsObservable::rxBook);

        Observable<String> ticket2 = user.zipWith(location, (usr, loc) -> Pair.of(usr,loc))
                //We have here an Obervable<Pair<User, Location>>
                .flatMap( pair -> agencies.flatMap(agency -> {
                    String ur = pair.getLeft();
                    String loc = pair.getRight();
                    return Util.observeFromCompetable(agency.searchAsync(ur, loc));
                })).first().flatMap(CompletableAsObservable::rxBook);
    }

    static Observable<String> rxFindById(long id){
        return Util.observeFromCompetable(CompetableFutureExample.findByIdAsync(id));
    }

    static Observable<String> rxLocate(){
        return Util.observeFromCompetable(CompetableFutureExample.locateAsyn());
    }

    static Observable<String> rxBook(String flight){
        return Util.observeFromCompetable(CompetableFutureExample.bookAsync(flight));
    }



    static class Util{

        /**
         * CompletableFuture is hot and cached
         * Start execution immediately
         * @param future
         * @param <T>
         * @return
         */
        static <T>Observable<T> observeFromCompetable(CompletableFuture<T> future){
            return Observable.create(subscriber -> {
                future.whenComplete((value, exception) -> {
                    if(exception != null){
                        subscriber.onError(exception);
                    }else {
                        subscriber.onNext(value);
                        subscriber.onCompleted();
                    }
                });
            });
        }
    }
}
