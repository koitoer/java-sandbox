package com.koitoer.rx.testing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by mmena on 4/3/19.
 */
public class Multi {

    public static void main(String[] args) throws InterruptedException {

        List partitionsOfThisNode = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int partitionSize = 2;
        List<List<Integer>> partitions = new LinkedList<>();
        for (int i = 0; i < partitionsOfThisNode.size(); i += partitionSize) {
            partitions.add(partitionsOfThisNode.subList(i,
                Math.min(i + partitionSize, partitionsOfThisNode.size())));
        }

        System.out.println(partitions);

        List<Observable<Void>> observableList = partitions.stream().map(x ->
            Observable.fromCallable(new Callable<Void>() {

                @Override public Void call() throws Exception {
                    System.out.println(x);
                    function(x);
                    return null;
                }
            }).subscribeOn(Schedulers.io())).collect(Collectors.toList());


        Observable.merge(observableList).toBlocking().subscribe();

        System.out.println("Complete");

    }

    public static void function(List<Integer> elements) {
        System.out.println("function");
        for (int i = 0; i < elements.size(); i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(elements.get(i));
        }
    }
}
