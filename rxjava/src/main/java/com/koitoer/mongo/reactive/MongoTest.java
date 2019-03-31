package com.koitoer.mongo.reactive;

import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mongodb.reactivestreams.client.Success;

import rx.Observable;

/**
 * Created by mmena on 7/24/18.
 */
public class MongoTest {

    private MongoDatabase mongoDatabase;

    public void createMongoDatabase(){
        MongoClient mongoClient = MongoClients.create();
        mongoDatabase =  mongoClient.getDatabase("AkiraEntityView");
    }

    public void test() throws InterruptedException {
        createMongoDatabase();

        Document doc = new Document("name", "MongoDB")
            .append("type", "database")
            .append("count", 1)
            .append("info", new Document("x", 203).append("y", 102));



        MongoCollection mongoCollection = mongoDatabase.getCollection("MongoUnitData");

        Publisher publisher =mongoCollection.find().first();
        publisher.subscribe(new Subscriber<Document>() {
            @Override
            public void onSubscribe(final Subscription s) {
                s.request(1);  // <--- Data requested and the insertion will now occur
            }

            @Override
            public void onNext(final Document success) {
                System.out.println("Read" + success);
            }

            @Override
            public void onError(final Throwable t) {
                System.out.println(t);
                System.out.println("Failed");
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });

        TimeUnit.SECONDS.sleep(10);


    }

    public static void main(String[] args) throws InterruptedException {
        MongoTest mongoTest = new MongoTest();
        mongoTest.test();
        TimeUnit.SECONDS.sleep(20);
    }

    private class OperationSubscriber<T> implements Subscriber {

        @Override public void onSubscribe(Subscription subscription) {

        }

        @Override public void onNext(Object o) {
            System.out.println("next");
        }

        @Override public void onError(Throwable throwable) {

        }

        @Override public void onComplete() {
            System.out.println("nextC");

        }
    }
}
