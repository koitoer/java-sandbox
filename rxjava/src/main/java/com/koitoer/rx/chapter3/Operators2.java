package com.koitoer.rx.chapter3;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/18/17.
 */
public class Operators2 {


    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        Observable<Car> observableCar = Observable.merge(car.putTiers(), car.putWindows(), car.putEngine());
        observableCar.subscribe(System.out::println);
        System.out.println("Mi car " + car);
        TimeUnit.SECONDS.sleep(5);

        Car car2 = new Car();
        Observable<Car> observableCar2= Observable.merge(car2.putTiers(), car2.putWindows());
        observableCar2.subscribe(System.out::println);
        System.out.println("Mi car2 " + car2);
        TimeUnit.SECONDS.sleep(5);
    }

}
