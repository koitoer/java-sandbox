package com.koitoer.rx.chapter4;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by mmena on 3/25/17.
 */
public class PoolService {

    public static ExecutorService poolA = Executors.newFixedThreadPool(10, threadFactory("Schedu-A-%d"));
    public static Scheduler schedulerA = Schedulers.from(poolA);

    public static ExecutorService poolB = Executors.newFixedThreadPool(10, threadFactory("Schedu-B-%d"));
    public static Scheduler schedulerB = Schedulers.from(poolB);

    public static ExecutorService poolC = Executors.newFixedThreadPool(10, threadFactory("Schedu-C-%d"));
    public static Scheduler schedulerC = Schedulers.from(poolC);

    private static ThreadFactory threadFactory(String pattern){
        return new ThreadFactoryBuilder().setNameFormat(pattern).build();
    }
}
