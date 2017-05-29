package com.koitoer.rx.chapter8;

import java.sql.Time;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;

/**
 * Created by mmena on 5/25/17.
 */
public class AppCall {

    public static void main(String[] args) throws Exception{

        Callable<Set<String>> call1 = new Callable<Set<String>>() {

            @Override public Set<String> call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " Call1");
                return ImmutableSet.of("c1","c10");
            }
        };

        Callable<Set<String>> call2 = new Callable<Set<String>>() {

            @Override public Set<String> call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " Call2");
                return ImmutableSet.of("c2","c20");
            }
        };

        Callable<Set<String>> call3 = new Callable<Set<String>>() {

            @Override public Set<String> call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " Call3");
                throw new RuntimeException("S2");
                //return ImmutableSet.of("c3","c30");
            }
        };

        List<Callable<Set<String>>> callableList = Arrays.asList(call1, call2, call3);




        /**
        List<FutureTask<Set<String>>> tasks = callableList.stream().map(x -> new FutureTask<>(x)).collect(Collectors.toList());

        CompletableFuture[] siteAsyncFutures = tasks.stream()
            .map(task -> CompletableFuture.runAsync(task))
            .toArray(CompletableFuture[]::new);
         **/

        final Set<String> stringSet = new HashSet<>();
        System.out.println("Observable approach");

        List<Observable<Set<String>>> observableList = callableList.stream()
            .map(x -> Observable.fromCallable(x).subscribeOn(Schedulers.io()).onErrorReturn(throwable -> new HashSet<>()))
            .collect(Collectors.toList());

        BlockingObservable<Set<String>> setBlockingObservable = Observable.merge(observableList).toBlocking();

        setBlockingObservable.subscribe(strings -> stringSet.addAll(strings));

        System.out.println(stringSet);

        CompletableFuture.runAsync(()->{
            System.out.println("Running async");
        });

        TimeUnit.SECONDS.sleep(10);


    }

}
