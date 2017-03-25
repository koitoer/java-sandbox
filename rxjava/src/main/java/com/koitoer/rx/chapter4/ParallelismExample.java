package com.koitoer.rx.chapter4;


import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

import java.math.BigDecimal;

/**
 *
 * Using flatMap and merge to avoid block the thread with map expensive operations
 * Created by mmena on 3/25/17.
 */
public class ParallelismExample {

    public static void main(String[] args) {

        Observable<BigDecimal> totalPrice =
                Observable.just("bread", "egg", "milk", "cheese")
                .subscribeOn(PoolService.schedulerA)
                .map(prod -> doPurchase(prod, 1))
                .reduce(BigDecimal::add)
                .single();

        totalPrice.subscribe(System.out::println);

        //First approach
        Observable<BigDecimal> totalPrice2 =
                Observable.just("bread", "egg", "milk", "cheese")
                        .subscribeOn(PoolService.schedulerA)
                        .flatMap(prod -> purchase(prod, 1))
                        .reduce(BigDecimal::add)
                        .single();
        totalPrice2.subscribe(System.out::println);


        //Second and correct approach
        Observable<BigDecimal> totalPrice3 =
        Observable.just("bread", "egg", "milk", "cheese")
                .flatMap(prod -> purchase(prod, 1).subscribeOn(PoolService.schedulerA))
                        .reduce(BigDecimal::add)
                        .single();
        totalPrice3.subscribe(System.out::println);

        //Grouping and then apply concurrency
        Observable<BigDecimal> totalPrice4 =
                Observable.just("bread", "egg", "milk", "cheese", "egg", "egg", "egg", "milk")
                        .groupBy(x -> x)
                        .flatMap(
                                grouped -> grouped.count()
                                    .map(quantity -> {
                                      String productName = grouped.getKey();
                                      return Pair.of(productName, quantity);
                                    })
                        )
                        .flatMap( order -> purchase(order.getKey(), order.getValue())
                                            .subscribeOn(PoolService.schedulerA))
                        .reduce(BigDecimal::add)
                        .single();
        totalPrice4.subscribe(System.out::println);


    }

    static Observable<BigDecimal> purchase(String productName, int quantity){
        return Observable.fromCallable(() ->
            doPurchase(productName, quantity));
    }

    private static BigDecimal doPurchase(String productName, int quantity) {
        MultithreadingExample.log("Purchasing " + quantity + " " + productName);
        MultithreadingExample.log("Some real logic");
        MultithreadingExample.log("Done " + quantity + " " + productName);
        BigDecimal priceForProduct = new BigDecimal(Math.random());
        return priceForProduct;
    }

}
