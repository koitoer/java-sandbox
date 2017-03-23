package com.koitoer.java.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by mauricio.mena on 16/06/2016.
 */
public class StreamExampleClass {

    //Stream in java is a sequence of data
    //Stream pipeline is the operations that runs on a stream to produce a result

    public static void main(String[] args) {
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> singleElement = Stream.of(1);
        Stream<Integer> fromArray = Stream.of(1,2,3);

        List<String> list  = Arrays.asList("a","b", "c");
        Stream<String> fromList =  list.stream();
        Stream<String> fromListParallel =  list.parallelStream();

        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, integer -> integer + 2 );

        //Reduction Count
        System.out.println(fromList.count());

        //Min and Max
        Stream<String> s =  Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((o1, o2) -> o1.length()-o2.length());
        min.ifPresent(System.out::println);

        Optional<?> minEmpty = Stream.empty().min((o1, o2) -> 0);
        System.out.println(minEmpty.isPresent());

        //Actions
        Stream<String> ss = Stream.of("a", "b", "c");
        Stream<String> infiniteStream = Stream.generate(() -> "chimp");
        ss.findAny().ifPresent(System.out::println);
        infiniteStream.findAny().ifPresent(System.out::println);


        List<String> list2 = Arrays.asList("monkey", "2", "chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
        System.out.println(list2.stream().anyMatch(pred)); // true
        System.out.println(list2.stream().allMatch(pred)); // false
        System.out.println(list2.stream().noneMatch(pred)); // false
        System.out.println(infinite.anyMatch(pred)); // true

        Stream<String> sw = Stream.of("Monkey", "Gorilla", "Bonobo");
        sw.forEach(System.out::print);    // MonkeyGorillaBonobo

        //Reduce methods combine into a single object

    }

}
