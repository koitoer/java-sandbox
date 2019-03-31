package com.koitoer.akka;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

/**
 * Created by mmena on 4/29/18.
 */
public class SimpleActor {

    static class Counter extends AbstractLoggingActor {

        static class Message{}

        private int counter = 0;

        {
            receive(ReceiveBuilder.match(Message.class, this::onMessage).build());
        }

        private void onMessage(Message message){
            counter++;
            log().info("Increased counter " + counter);
        }

        public static Props props(){
            return Props.create(Counter.class);
        }
    }

    public static void main(String[] args) {

        ActorSystem actorSystem = ActorSystem.create("example1");
        ActorRef actorRef = actorSystem.actorOf(Counter.props(), "counter");

        for(int j=0 ; j <=5 ; j++) {
            new Thread(() -> {
                for (int i = 0; i <= 5; i++) {
                    actorRef.tell(new Counter.Message(), ActorRef.noSender());

                }
            }).start();
        }

        System.out.println("Enter to end.");
    }
}
