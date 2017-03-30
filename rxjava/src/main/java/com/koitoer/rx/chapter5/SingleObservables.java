package com.koitoer.rx.chapter5;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import rx.Single;
import rx.SingleSubscriber;
import rx.schedulers.Schedulers;

import java.time.Instant;

/**
 * Created by mmena on 3/30/17.
 */
public class SingleObservables {

    static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();


    static Single<Response> fetch(String address){
        return Single.create(singleSubscriber -> asyncHttpClient.prepareGet(address)
            .execute(handler(singleSubscriber)));
    }



    public static void main(String[] args) {
        Single<String> single = Single.just("Hello world");
        single.subscribe(System.out::println);

        Single<Instant> error = Single.error(new RuntimeException("error"));

        error.observeOn(Schedulers.io()).subscribe(
                        System.out::println,
                        Throwable::printStackTrace
        );

        //Single does not have filter as we dont want to filter the results
        Single<String> example = fetch("http://www.example.com").flatMap(SingleObservables::body);
        String b = example.toBlocking().value();
        System.out.println("Expresss : " + b );

        example = fetch("http://www.example.com").flatMap(SingleObservables::body2);
        String c = example.toBlocking().value();
        System.out.println("Expresss2 : " + c);
     }

    private static Single<String> body(Response response) {
        return Single.create(singleSubscriber -> {
           try{
               //This could throw and IOException that is why we wrap
               singleSubscriber.onSuccess(response.getResponseBody());
           } catch (Exception e){
               singleSubscriber.onError(e);
           }
        });
    }

    private static Single<String> body2(Response response){
        return Single.fromCallable(() -> response.getResponseBody());
    }

    private static AsyncCompletionHandler handler(SingleSubscriber<? super Response> singleSubscriber) {
        return new AsyncCompletionHandler() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                singleSubscriber.onSuccess(response);
                return response;
            }

            public void onThrowable(Throwable t){
                singleSubscriber.onError(t);
            }
        };
    }
}
