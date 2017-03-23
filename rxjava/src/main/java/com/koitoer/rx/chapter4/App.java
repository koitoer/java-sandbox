package com.koitoer.rx.chapter4;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;
import rx.schedulers.Schedulers;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/22/17.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        PersonDao personDao = new PersonDao();

        System.out.println("List Behaviour");
        personDao.listPeople().stream().forEach(System.out::println);

        //Doing this will affect the interface
        System.out.println("RX Behaviour");
        personDao.listPeopleRx().forEach(System.out::println);

        //Doing this wont affect the interface
        System.out.println("List under RX Behaviour");
        personDao.listPeopleObservable().forEach(System.out::println);

        //Recommendation when exist
        personDao.bestBookFor(new Person("Koitoer"));
        //Recommendation when no exists
        personDao.bestBookFor(new Person("Mauricio"));

        System.out.println("Ways to implement Lazy Paging 1");
        //First page 1 element
        personDao.allPeople(1).take(1).forEach(System.out::println);
        //First page 2 elements
        personDao.allPeople(1).take(2).forEach(System.out::println);
        //First page but 3 element, need to go to the next page
        personDao.allPeople(1).take(3).forEach(System.out::println);

        System.out.println("Ways to implement Lazy Paging 2");
        personDao.lazyListPeopleRx().take(1).forEach(System.out::println);

        //Scenarios is contact two web services to buy a ticket NO RX

        Instant start = Instant.now();
        TicketStore ticketStore = new TicketStore();
        String flight = ticketStore.lookupFlight("777");
        String passenger = ticketStore.findPassenger(1L);
        String ticket = ticketStore.bookTicket(flight, passenger);
        System.out.println(ticketStore.sendEmail(ticket));
        Instant end = Instant.now();
        Duration duration1 = Duration.between(start, end);
        System.out.println("Process no RX takes  :" + duration1.getSeconds());

        start = Instant.now();
        Observable<String> flightObs = Observable.defer(() -> Observable.just(ticketStore.lookupFlight("777")));
        Observable<String> passengerObs = Observable.defer(() -> Observable.just(ticketStore.findPassenger(1)));
        Observable<String> ticketObs = Observable.zip(flightObs, passengerObs, (x,y) -> ticketStore.bookTicket(x,y));
                //flightObs.zipWith(passengerObs, (f,p)-> ticketStore.bookTicket(f,p));
        ticketObs.subscribe(ticketStore::sendEmail);
        end = Instant.now();
        duration1 = Duration.between(start, end);
        //Is still running sync just joining result of observables as all of them run in the main thread
        System.out.println("Process RX takes :" + duration1.getSeconds());

        //Making the process faster
        start = Instant.now();
        flightObs = Observable.defer(() -> Observable.just(ticketStore.lookupFlight("777"))).subscribeOn(Schedulers.io());
        passengerObs = Observable.defer(() -> Observable.just(ticketStore.findPassenger(1))).subscribeOn(Schedulers.io());;
        ticketObs = flightObs.zipWith(passengerObs, (String f, String p)-> Pair.of(f, p))
            .flatMap(pair -> ticketStore.bookTicket2(pair.getLeft(), pair.getRight()));
        System.out.println(ticketStore.sendEmail(ticketObs.toBlocking().single()));
        end = Instant.now();
        duration1 = Duration.between(start, end);
        //Is still running sync just joining result of the async observables
        System.out.println("Process RX in thread takes :" + duration1.getSeconds());

        //FlatMap as Asyn Chaining Ops
        //Use on error to avoid the exception kill the entire observable
        //doOnError only catch the error but propagate that
        List<String> tickets = Arrays.asList("ticket1", "ticket2", "ticket3" );
        Observable a = Observable.from(tickets)
                .flatMap(tick -> rxSendEmail(tick)
                .ignoreElements()
                .doOnError(e -> System.out.println("Failing to send"+ tick + e.getMessage()))
                .onErrorReturn(err -> tick)
                .subscribeOn(Schedulers.io()));

        a.toBlocking().subscribe(System.out::println);
        System.out.println("Process flatmap Async :" + duration1.getSeconds());
        TimeUnit.SECONDS.sleep(10);

    }

    private static Observable<String> rxSendEmail(String ticket) {
        return Observable.fromCallable(() -> sendEmail(ticket));
    }

    private static String sendEmail(String ticket) {
        if("ticket2".equals(ticket)){
            throw new RuntimeException("Ticket "+ticket + " has problems");
        }
        return "Email sent"+ticket;
    }
}
