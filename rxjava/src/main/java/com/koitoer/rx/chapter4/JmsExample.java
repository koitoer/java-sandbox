package com.koitoer.rx.chapter4;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import javax.jms.*;
import javax.management.JMException;

import static javax.jms.JMSContext.AUTO_ACKNOWLEDGE;

/**
 * Created by mmena on 3/25/17.
 */
public class JmsExample {

    /**
     * Create an observable that will emit events on they arrive by JMS
     * @param connectionFactory
     * @param topic
     * @return
     */
    public Observable<Message> observe(ConnectionFactory connectionFactory, Topic topic){
        return Observable.create(subscriber -> {
            try {
                subscriberThrowing(subscriber, connectionFactory, topic);
            } catch (JMSException e) {
                subscriber.onError(e);
            }
        });
    }

    private void subscriberThrowing(Subscriber<? super Message> subscriber,
                                    ConnectionFactory connectionFactory, Topic topic) throws JMSException {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(true, AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(subscriber::onNext);
        subscriber.add(onUnsubscribe(connection));
        connection.start();
    }

    private Subscription onUnsubscribe(Connection connection) {
        return Subscriptions.create(() -> {
           try{
               connection.close();
           } catch (Exception e){
               e.printStackTrace();
           }
        });
    }
}
