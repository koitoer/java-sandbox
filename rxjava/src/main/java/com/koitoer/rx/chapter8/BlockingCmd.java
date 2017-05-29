package com.koitoer.rx.chapter8;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.commons.io.IOUtils;
import rx.Observable;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 4/16/17.
 */
public class BlockingCmd extends HystrixCommand<String> {

    public BlockingCmd(){
        super(HystrixCommandGroupKey.Factory.asKey("Somegroup"));
    }

    @Override
    protected String run() throws Exception {
        final URL url = new URL("http://www.example.com");
        try (InputStream inputStream = url.openStream()){
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String string = new BlockingCmd().execute();
        System.out.println(string.substring(1,20));

        Future<String> future = new BlockingCmd().queue();
        System.out.println(future.get().substring(20,30));

        Observable<String> eager = new BlockingCmd().observe();
        eager.subscribe(System.out::println);

        //convert to a cold observable
        Observable<String> lazy = new BlockingCmd().toObservable();
        lazy.subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);

        Observable<String> retried = new BlockingCmd().toObservable()
                .doOnError(ex -> System.out.println(ex))
                .retryWhen(ex -> ex.delay(500, TimeUnit.MILLISECONDS))
                .timeout(3, TimeUnit.SECONDS);

        retried.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);


    }
}
