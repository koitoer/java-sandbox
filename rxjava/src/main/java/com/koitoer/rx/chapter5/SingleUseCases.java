package com.koitoer.rx.chapter5;

import rx.Observable;
import rx.Single;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/31/17.
 */
public class SingleUseCases {

    public static void main(String[] args) {
        SingleUseCases singleUseCases =  new SingleUseCases();
        Single<String> document = Single.zip(
                singleUseCases.content(12),
                singleUseCases.likes(12),
                singleUseCases.updateRead(),
                (con, lks, vod) -> singleUseCases.buildhtml(con, lks));
        System.out.println(document);

        singleUseCases.content(12).timeout(10, TimeUnit.SECONDS);
        singleUseCases.content(12).onErrorReturn((ex) -> "no contnet" );
        singleUseCases.content(12).onErrorResumeNext((ex) -> Single.just("follow"));

        Single<String> single = Single.create(singleSubscriber -> {
            System.out.println("Subscribing");
            singleSubscriber.onSuccess("24");
        });

        //Make sense if there are certain missing operators
        Single<String> cachedSingle = single.toObservable().cache().toSingle();
        cachedSingle.subscribe(System.out::println);
        cachedSingle.subscribe(System.out::println);

        //Single use case when emit just one value or exceptions
        //Using observables if we dont have a stream will be overkill

        //Obervable when modeling some sort of UI events
        //Expect the value ocurrs o not before completition.

        //Cases
        Single<Integer> emptySingle = Observable.<Integer>empty().toSingle();
        try{
            emptySingle.subscribe(System.out::println);
        }catch (Exception e){
            //OnErrorNotImplementedException: Observable emitted no items
            System.out.println(e.getMessage());
        }

        try{
            Single<Integer> doubleSingle = Observable.just(1,2).toSingle();
            doubleSingle.subscribe(System.out::println);
        }catch (Exception e){
            //OnErrorNotImplementedException: Observable emitted too many elements.
            System.out.println(e.getMessage());
        }

        try{
            Single<Integer> ignored = Single.just(1).toObservable().ignoreElements().toSingle();
            ignored.subscribe(System.out::println);
        }catch (Exception e){
            //OnErrorNotImplementedException: Observable emitted no items.
            System.out.println(e.getMessage());
        }



    }

    private String buildhtml(String con, Integer lks) {
        return con.concat(lks.toString());
    }

    Single<String> content(int id){
        //Passing a lambda expression
        return Single.fromCallable(() -> {
           //Do some work in asyn using Schedulers.io like a database query
            return "This is my work done";
        });
    }

    Single<Integer> likes(int id){
        //Do an http asyn call to facebook
        return Single.just(100);
    }

    Single<Void> updateRead(){
        //only side effecr, no return value in Single
        return Single.just(null);
    }


}
