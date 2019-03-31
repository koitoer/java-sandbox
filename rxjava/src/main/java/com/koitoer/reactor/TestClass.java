package com.koitoer.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Created by mmena on 9/23/18.
 */
public class TestClass {

    public static void main(String[] args) throws InterruptedException {

        /**
        Flux<Integer> ints = Flux.range(1, 4)
            .map(i -> {
                if (i <= 3) return i;
                throw new RuntimeException("Got to 4");
            });
        ints.subscribe(i -> System.out.println(i),
            error -> System.err.println("Error: " + error));
         **/



        //Test 2
        Scheduler s = Schedulers.newParallel("a",4);
        final Flux<Integer> flux = Flux
            .range(1, 10)
            .map(i -> 10 + i)
            .publishOn(s)
            .map(integer -> {
                System.out.println(Thread.currentThread().toString() + "->" + integer);
                return integer;
            });

        new Thread(() -> flux.subscribe(System.out::println)).start();


        Thread.sleep(10000);
    }

}
