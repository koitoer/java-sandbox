package com.koitoer.rx.chapter5;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Created by mmena on 3/29/17.
 */
public class CompetableFutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Agency> agencies = Arrays.asList(new Agency("Expedia"), new Agency("Hotels.com"));
        String user =  findUser(1L);
        String geolocation = locate();

        ExecutorCompletionService<String> ecss = new ExecutorCompletionService<String>(pool);
        agencies.forEach(agency -> ecss.submit(agency.search(user, geolocation)));

        Future<String> firstFlight = ecss.poll(5, TimeUnit.SECONDS);
        String fligth = firstFlight.get();
        book(fligth);


        //Lets rewrite this with observables
        CompetableFutureExample comp = new CompetableFutureExample();
        CompletableFuture<String> userCom = comp.findByIdAsync(1L);
        CompletableFuture<String> locCom = comp.locateAsyn();

        CompletableFuture<String>  ticket =
                userCom.thenCombine(locCom,
                        (String user1, String location) ->
                                agencies.stream().map(agency -> agency.searchAsync(user1, location))
                                        .reduce((f1,f2) -> f1.applyToEither(f2, Function.identity())).get())
                        .thenCompose(Function.identity()).thenCompose(CompetableFutureExample::bookAsync);

        System.out.println(ticket.get());


        CompletableFuture<Long> timeFuture = CompletableFuture.supplyAsync(() -> 1L);
        CompletableFuture<ZoneId> zoneFuture = CompletableFuture.supplyAsync(() -> ZoneId.of("UTC"));

        CompletableFuture<Instant> instantCompletableFuture = timeFuture.thenApply(time -> Instant.ofEpochMilli(time));
        CompletableFuture<ZonedDateTime> zonedDateTimeCompletableFuture = instantCompletableFuture.thenCombine(zoneFuture,
                (instant, zoneId) -> ZonedDateTime.ofInstant(instant, zoneId));

        System.out.println(zonedDateTimeCompletableFuture.get());
     }

    private static void book(String flight) {
        System.out.println(flight);
    }

    private static String locate() {
        return "Mexico";
    }

    private static String findUser(long l) {
        return "Koitoer";
    }


    static CompletableFuture<String> findByIdAsync(long id){
        return CompletableFuture.supplyAsync(() -> "Koitoer");
    }

    static CompletableFuture<String> locateAsyn(){
        return CompletableFuture.supplyAsync(() -> "Mexico");
    }

    static CompletableFuture<String> bookAsync(String flight){
        return CompletableFuture.supplyAsync(() -> flight);
    }



    private String search(String user, String geolocation) {
        return "SS"+user+geolocation;
    }

    public static class Agency {

        private String name;

        public Agency(String name) {
            this.name = name;
        }

        public Agency() {

        }

        public Callable<String> search(String user, String geolocation) {
            return () -> user + geolocation;
        }


        public CompletableFuture<String> searchAsync(String user1, String location) {
            System.out.println(this.name);
            if(this.name.equals("Expedia")){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return CompletableFuture.supplyAsync(() -> "this.user"+user1+location+this.name);
        }
    }
}
