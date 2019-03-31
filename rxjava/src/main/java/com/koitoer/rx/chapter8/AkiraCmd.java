package com.koitoer.rx.chapter8;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import rx.Observable;

/**
 * Created by mmena on 4/16/17.
 */
public class AkiraCmd extends HystrixCommand<String> {

    public AkiraCmd(){
        super(HystrixCommandGroupKey.Factory.asKey("akira-group"));
    }

    @Override
    protected String run() throws Exception {
        final URL url = new URL("http://akira-prod-vip.homeaway.live:82/api/aggregatorDebug/UnitRateAlgorithm/de64e819-5436-44fc-86df-52e1f07b8b1a");
        try (InputStream inputStream = url.openStream()){
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String string = new AkiraCmd().execute();
        System.out.println(string.substring(1,20));

        Future<String> future = new AkiraCmd().queue();
        System.out.println(future.get().substring(20,30));

        Observable<String> eager = new AkiraCmd().observe();
        eager.subscribe(System.out::println);

        //convert to a cold observable
        Observable<String> lazy = new AkiraCmd().toObservable();
        lazy.subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);

        Observable<String> retried = new AkiraCmd().toObservable()
                .doOnError(ex -> System.out.println(ex))
                .retryWhen(ex -> ex.delay(500, TimeUnit.MILLISECONDS))
                .timeout(3, TimeUnit.SECONDS);

        retried.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);


    }
}
