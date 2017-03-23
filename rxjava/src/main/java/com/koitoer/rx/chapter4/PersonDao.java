package com.koitoer.rx.chapter4;

import rx.Observable;
import rx.observables.BlockingObservable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mmena on 3/22/17.
 */
public class PersonDao {

    private List<Person> people = Arrays.asList(new Person("Koitoer"), new Person("Mauricio"),
            new Person("Ptd"), new Person("Process"), new Person("lastPerson"));

    public List<Person> listPeople(){
        return people;
    }

    public Observable<Person> listPeopleRx(){
        return Observable.from(people);
    }

    public List<Person> listPeopleObservable(){
        Observable<List<Person>> listPeople = this.listPeopleRx().toList();
        BlockingObservable<List<Person>> blockingObservable = listPeople.toBlocking();
        return blockingObservable.single();
        //Single line listPeopleRx().toList().toBlocking().single();
    }

    /**
     * Inside observable is eager we defer the creation of this observable until somebody subscribes.
     * @return
     */
    public Observable<Person> lazyListPeopleRx(){
        return Observable.defer(() -> Observable.from(people));
    }


    /**
     * Get a default answer in case observable fails.
     * @param person
     */
    public void bestBookFor(Person person){
        //Defer the creation of eager observable so we can fail gracefully in onErrorResumeNext
        Observable<Book> recommended = Observable.defer(() -> recommend(person));
        Observable<Book> bestSeller = bestSeller();
        Observable<Book> bookObservable = recommended.onErrorResumeNext(bestSeller);
        Observable<String> title = bookObservable.map(x -> x.getTitle());
        title.subscribe(System.out::println);
    }

    public Observable<Person> allPeople(int initialPage){
        return Observable.defer(()-> this.listPeople(initialPage))
                .concatWith(Observable.defer(()-> this.listPeople(initialPage + 1)));
    }

    /**
     * Lazy retrieval of users 1
     * @param initialPage
     * @return
     */
    private Observable<Person> listPeople(int initialPage) {
        if(initialPage == 1) {
            System.out.println("First pagination executed");
            return Observable.just(new Person("A"), new Person("B"));
        }else{
            System.out.println("Out of items this is executed");
            return Observable.just(new Person("X"), new Person("Z"));
        }
    }

    /**
     * Other way to make a lazy call to the database
     * @return
     */
    private Observable<Person> listPeopleLazy() {
        Observable<List<Person>> allPages = Observable.range(0, Integer.MAX_VALUE)
                .map(x -> listPeople()).takeWhile(list -> !list.isEmpty());
        //return allPages.concatMap(Observable::from);
        return allPages.concatMapIterable(page -> page);

    }



    private Observable<Book> bestSeller() {
        return Observable.defer(() -> Observable.just(new Book("BestSeller")));
    }

    private Observable<Book> recommend(Person person) {
        if("Koitoer".equals(person.getName())){
            return Observable.just(new Book("Book"+person.getName()));
        }
        throw new RuntimeException("No recommendation");
    }


}
