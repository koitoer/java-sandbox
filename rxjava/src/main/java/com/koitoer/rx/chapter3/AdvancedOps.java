package com.koitoer.rx.chapter3;

import rx.Observable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/19/17.
 */
public class AdvancedOps {

    public static void main(String[] args) throws InterruptedException {

        Observable<Integer> progress = Observable.just(10,14,12,13,14,16);
        progress.scan((total, chunk) -> total+chunk).forEach(System.out::println);
        TimeUnit.SECONDS.sleep(3);

        Observable<CashTransfer> transferObservable = Observable.just(new CashTransfer(10.0),
                new CashTransfer(15.0), new CashTransfer(25.0));

        Observable<BigDecimal> total = transferObservable.reduce(BigDecimal.ZERO,
                (totalSoFar, transfer) -> totalSoFar.add(transfer.getAmount()));

        total.subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(2);


        System.out.println("Collect examples");
        Observable<List<Integer>> all = Observable.range(10,20)
                .reduce(new ArrayList<>(), (list, item) -> {
                    list.add(item);
                    return list;
                });
        all.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);

        System.out.println("Collect examples 2");
        Observable<List<Integer>> all2 = Observable.range(10,20)
                .collect(ArrayList::new, List::add);
        all2.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);

        System.out.println("Collect examples 3");
        Observable<String> all3 = Observable.range(10,20)
                .collect(StringBuilder::new, (sb, item) -> sb.append(item).append(",")).map(StringBuilder::toString);
        all3.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);
    }
}
