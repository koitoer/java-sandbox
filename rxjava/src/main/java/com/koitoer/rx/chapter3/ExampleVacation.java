package com.koitoer.rx.chapter3;

import rx.Observable;

import java.time.LocalDate;

/**
 * Created by mmena on 3/19/17.
 */
public class ExampleVacation {

    public static void main(String[] args) {

        //Observable for the next 10 days
        Observable<LocalDate> nextTenDays = Observable.range(1,10).map(i -> LocalDate.now().plusDays(i));

        Observable<Vacation> possibleVacations =
                //Cities where you want to go
                Observable.just("Warsaw","London","Paris")
                        //Create the Observable for All dates and all depart cities
                .flatMap(city -> nextTenDays.map(date -> new Vacation(city,date)))
                        //Take the previous observable and for each vacation
                .flatMap(vacation ->
                        Observable.zip(
                                //check if is sunny
                                vacation.weather().filter(weather -> weather.equals("sunny")),
                                //check if is cheap flight from nyc
                                vacation.cheapFlights("NYC"),
                                //check if hotel is cheap
                                vacation.cheapHotel(),
                                //if all of them emit, you will have the selected vacations.
                                //If no item is emitted for the downstream observable
                                (w,f,v) -> vacation
                        )
                );
    }
}
