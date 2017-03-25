package com.koitoer.rx.chapter4;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * Created by mmena on 3/25/17.
 */
public class MultithreadingExample {

    public static void main(String[] args) {

        //Declarative concurrency
        //Previously we use the ExecutorService with a Thread Pool
        //CompetableFuture is non blocking in Java8
        //Rx is concurrency-agnostic

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("MyPool-%d").build();
        //corePoolSize, maximumPoolSize, keepAliceTime, workQueue
        //Executors.newFixedThreadPool(10);
        Executor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1000), threadFactory);
        Scheduler scheduler = Schedulers.from(executor);


        Scheduler scheduler1 = Schedulers.immediate();
        Scheduler.Worker worker = scheduler1.createWorker();

        log("Main Start");
        worker.schedule(() -> {
            log( " Outer start ");
            sleepOneSecond();
            worker.schedule( () -> {
                log("Inner start");
                sleepOneSecond();
                log("Inner end");
            });
            log(" Outer end");
        });
        log("Main end");

        worker.unsubscribe();

        //This will first end all the pending task before start the next worker thread
        System.out.println("Example with trampoline");
        scheduler1 = Schedulers.trampoline();
        Scheduler.Worker worker1 = scheduler1.createWorker();

        log("Main Start");
        worker1.schedule(() -> {
            log( " Outer start ");
            sleepOneSecond();
            worker1.schedule( () -> {
                log("Inner start");
                sleepOneSecond();
                log("Inner end");
            });
            log(" Outer end");
        });
        log("Main end");

        worker.unsubscribe();
    }

    private static void sleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static void log(Object label){
        System.out.println(
                System.currentTimeMillis()  + "\t| " +
                  Thread.currentThread().getName() + "\t| " +
                  label
        );
    }
}
