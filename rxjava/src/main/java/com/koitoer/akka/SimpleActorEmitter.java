package com.koitoer.akka;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by mmena on 4/29/18.
 */
public class SimpleActorEmitter {

    static class Alarm extends AbstractLoggingActor {

        static class Activity{}

        static class Disable{
            private final String password;

            public Disable(String password){
                this.password = password;
            }
        }

        static class Enable{
            private final String password;

            public Enable(String password){
                this.password = password;
            }
        }


        private final String password;
        private final PartialFunction<Object, BoxedUnit> enabled;
        private final PartialFunction<Object, BoxedUnit> disabled;

        public Alarm(String password){
            this.password = password;
            enabled =
                ReceiveBuilder.match(Activity.class, this::onActivity)
                .match(Disable.class, this::onDisabled).build();

            disabled =
                ReceiveBuilder.match(Enable.class, this::onEnable)
                .build();

            receive(disabled);
        }

        private void onEnable(Enable enable) {
            if(password.equals(enable.password)){
                log().info("alamar enabled");
                getContext().become(enabled);
            }else{
                log().warning("password incorrect on enabled, try...");
            }
        }

        private void onDisabled(Disable disable) {
            if(password.equals(disable.password)){
                log().info("alamar disabled");
                getContext().become(disabled);
            }else{
                log().warning("password incorrect on disabled");
            }
        }

        private  void onActivity(Activity activity) {
            log().warning("oeoeoeeo alarm, alarm, alarm");
        }

        public static Props props(String password){
            return Props.create(Alarm.class, password);
        }
    }

    public static void main(String[] args) {

        ActorSystem actorSystem = ActorSystem.create("example2");
        ActorRef alarm  = actorSystem.actorOf(Alarm.props("cat"), "alarm");

        alarm.tell(new Alarm.Activity(), ActorRef.noSender());
        alarm.tell(new Alarm.Enable("dogs"), ActorRef.noSender());
        alarm.tell(new Alarm.Enable("cat"), ActorRef.noSender());

        alarm.tell(new Alarm.Activity(), ActorRef.noSender());
        alarm.tell(new Alarm.Disable("dogs"), ActorRef.noSender());
        alarm.tell(new Alarm.Disable("cat"), ActorRef.noSender());

        alarm.tell(new Alarm.Activity(), ActorRef.noSender());

        System.out.println("Enter to end.");

    }
}
