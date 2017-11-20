package com.koitoer.rx.chapter0;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 11/8/17.
 */
public class CompletableFutureExercise {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture.runAsync(() -> System.out.println("0 " + Thread.currentThread().getName()));
        CompletableFuture.supplyAsync(() -> {
            System.out.println(("1 " + Thread.currentThread().getName()));
            return "Koitoer";
        }).thenRunAsync(() -> System.out.println("2 " + Thread.currentThread().getName()))
            .thenRunAsync(() -> System.out.println("3 " +Thread.currentThread().getName()));

        System.out.println("1 . Timer stop....");

        System.out.println(CompletableFuture.supplyAsync(() -> {
            System.out.println(("4 " + Thread.currentThread().getName()));
            return "Koitoer";
        }).get());

        System.out.println("2 . Timer stop....");
        TimeUnit.SECONDS.sleep(10);
    }

}
