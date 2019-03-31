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
public class Akira2Cmd extends HystrixCommand<String> {

    public Akira2Cmd(){
        super(HystrixCommandGroupKey.Factory.asKey("akira-group"));
    }

    @Override
    protected String run() throws Exception {
        final URL url = new URL("http://opas-rates-engine-production.us-east-1-vpc-d9087bbe.slb-internal.prod.aws.away.black/arg/v1/debug/getUUIDData/db6020f8-2b3f-4420-925f-a611cdcf938c");
        try (InputStream inputStream = url.openStream()){
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String string = new Akira2Cmd().execute();
        System.out.println(string.substring(1,20));

        Future<String> future = new Akira2Cmd().queue();
        System.out.println(future.get().substring(20,30));

        Observable<String> eager = new Akira2Cmd().observe();
        eager.subscribe(System.out::println);

        //convert to a cold observable
        Observable<String> lazy = new Akira2Cmd().toObservable();
        lazy.subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);

        Observable<String> retried = new Akira2Cmd().toObservable()
                .doOnError(ex -> System.out.println(ex))
                .retryWhen(ex -> ex.delay(500, TimeUnit.MILLISECONDS))
                .timeout(3, TimeUnit.SECONDS);

        retried.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);


    }
}
