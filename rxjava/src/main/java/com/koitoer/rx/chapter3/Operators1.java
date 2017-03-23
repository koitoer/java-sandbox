package com.koitoer.rx.chapter3;

import rx.Observable;
import rx.Subscription;

import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/18/17.
 */
public class Operators1 {

    enum Sound { DI, DAH}

    static  Observable<Sound> toMorseCode(char ch){
        switch (ch){
            case 'a': return Observable.just(Sound.DI, Sound.DAH);
            case 'b': return Observable.just(Sound.DAH, Sound.DI, Sound.DI, Sound.DI);
            case 'c': return Observable.just(Sound.DAH, Sound.DI, Sound.DAH, Sound.DI);
            case 't': return Observable.just(Sound.DAH);
                    default:
                        return Observable.empty();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Observable<Character> myObser = Observable.just('a','b','s','t');

        //Prcess that with map
        Observable<Observable<Sound>> sound = myObser.map(Character::toLowerCase).map(Operators1::toMorseCode);

        //Map to convert lowercase, then map will return the Observable<Sound> for each letter
        System.out.println(sound);
        //Consuming the observable, will give you more observables
        sound.subscribe((x) -> System.out.println("Sonido " + x));
        //Consuming the inner observable
        sound.subscribe((x) -> x.subscribe((y)-> System.out.println("Sonido real " + y)));


        //As we receive observables from map we should use flatMap

        Observable<Sound> finalSound = myObser.map(Character::toLowerCase).flatMap(Operators1::toMorseCode);
        finalSound.subscribe(System.out::println);

        //After 2 seconds emit all
        Observable<Integer> timerSound = Observable.timer(2, TimeUnit.SECONDS).flatMap(i -> Observable.just(1,2,3) );
        timerSound.subscribe(System.out::println);

        //Each one second emit
        Observable<String> stringSound = Observable.interval(1, TimeUnit.SECONDS).flatMap(i -> Observable.just("a","b","c") );
        Subscription subscription = stringSound.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);
        subscription.unsubscribe();

        System.out.println("End of interval");
        TimeUnit.SECONDS.sleep(5);

        //Delay will delay respectively each element emitted
        System.out.println("Start delay");
        Observable.just("Lorem", "ipsum", "dolor", "sit", "amet").delay(word -> Observable.timer(word.length(), TimeUnit.SECONDS))
                .subscribe(System.out::println);

        System.out.println("Start new form");
        TimeUnit.SECONDS.sleep(10);
        Observable.just("Lorem", "ipsum", "dolor", "sit", "amet").flatMap(word -> Observable.timer(word.length(),TimeUnit.SECONDS).map(x -> word))
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);


        //Flamap ensure the order
        System.out.println("Start flatmap no order");
        Observable.just(DayOfWeek.SUNDAY, DayOfWeek.MONDAY).flatMap(Operators1::loadRecords).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);


        System.out.println("Start flatmap with  order");
        Observable.just(DayOfWeek.SUNDAY, DayOfWeek.MONDAY).concatMap(Operators1::loadRecords).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);

        Observable.just(DayOfWeek.MONDAY, DayOfWeek.SUNDAY).concatMap(Operators1::loadRecords).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);

    }


    static Observable<String> loadRecords(DayOfWeek dow){
        switch (dow){
            case SUNDAY:
                return Observable.interval(90, TimeUnit.MILLISECONDS).take(5).map(i -> "Sun-"+i);
            case MONDAY:
                return Observable.interval(65, TimeUnit.MILLISECONDS).take(5).map(i -> "Mon-"+i);
        }
        return Observable.empty();
    }
}
