package com.koitoer.java.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * Created by mmena on 5/15/17.
 */
public class RetryExample {

    public static void main(String[] args) {
        int maxRetryAttempts = 5;
        Map<Class <? extends  Throwable>, Boolean> map =  new HashMap<>();
        map.put(IllegalArgumentException.class, true);
        map.put(IllegalStateException.class, false);


        RetryPolicy retryPolicy =  new SimpleRetryPolicy(maxRetryAttempts);
        RetryTemplate retryTemplate =  new RetryTemplate();
        retryTemplate.registerListener(new RetryListener() {

            @Override public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
                System.out.println("Close timers");

            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback,
                Throwable throwable) {
                    if(throwable instanceof IllegalArgumentException){
                        System.out.println("An exception has been places" + retryContext.getRetryCount());
                    }
            }
        });
        retryTemplate.setRetryPolicy(retryPolicy);
        String aux = retryTemplate.execute(retryContext -> {
            System.out.println(retryContext);
            String argument = "AA";
            if(retryContext.getRetryCount() == 2){
                argument = "B";
            }
            return RetryExample.getString(argument);
        });


        System.out.println(aux);
    }


    private static String getString(String a){
        if(a.equals("B")){
            throw new IllegalStateException("No handler");
        }

        if( a.equals("A")){
            return "AA";
        }else{
            throw new IllegalArgumentException("Illegal argument");
        }
    }
}
