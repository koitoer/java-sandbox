package com.koitoer.rx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by mmena on 5/24/17.
 */
public class AppFut {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<String, String> map =  new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(11);

        List<String> sites = Arrays.asList("UK", "US", "MX", "CA");

        List<Future<?>> futures = sites.stream().map(x ->  executorService.submit(()-> AppFut.runProcess(x, map)))
            .collect(Collectors.toList());

        for(Future f : futures){
            f.get();
        }
        //CompletableFuture.allOf(completableFuture.toArray(new CompletableFuture[completableFuture.size()])).get();
    }


    public static void runProcess(String site, Map map){
        if(site.equals("US"))
            throw new RuntimeException("Error on us");
        System.out.println(Thread.currentThread().getName() + " : " + site);
        map.put(site, site.toLowerCase());
        try {
            TimeUnit.SECONDS.sleep((long)(2 * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
