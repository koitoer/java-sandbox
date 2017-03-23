package com.koitoer.rx.chapter4;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmena on 3/22/17.
 */
public class TicketStore {

    public String lookupFlight(String flightNo){
        try {
            System.out.println("Make wait flight " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Make wait fligh2");
        return "Fly#"+flightNo;
    }

    public String findPassenger(long id) {
        try {
            System.out.println("Make wait passenger" + Thread.currentThread().getName()) ;
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Make wait passenger2");
        return "passenger"+id;
    }

    public String bookTicket(String flight, String passenger){
        return "Ticket FOR:"+flight+":"+passenger;
    }

    public String sendEmail(String ticket){
        System.out.println("Sending email");
        return "SendingEmailForTicket ->"+ticket;
    }

    public Observable<String> bookTicket2(String left, String right) {
        return Observable.just("Ticket FOR:"+left+":"+right);
    }
}
