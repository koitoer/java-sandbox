package com.koitoer.java.java8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

/**
 * Created by mauricio.mena on 16/06/2016.
 */
public class FunctionalClassExamples {

    public static void main(String[] args) {
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();

        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();

        System.out.println(d1);
        System.out.println(d2);


        Supplier<StringBuilder> s11 = StringBuilder::new;
        Supplier<StringBuilder> s21 = () -> new StringBuilder();

        System.out.println(s11.get());
        System.out.println(s21.get());

        Supplier<ArrayList<String>> s12 = ArrayList<String>::new;
        System.out.println(s12);
        ArrayList<String> a1 = s12.get();
        System.out.println(a1);

        ///Consumers

        Consumer<String> c1 =  System.out::println;
        Consumer<String> c2 = x -> System.out.println(x);

        c1.accept("Annie");
        c2.accept("Annie");

        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> b1 =  map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);

        b1.accept("koitoer",1);
        b2.accept("koit",2);

        System.out.println(map);


        //Predicate
        //@FunctionalInterface public class Predicate<T> {
        //boolean test(T t);

        Predicate<String> p1 =  String::isEmpty;
        Predicate<String> p2 = x -> x.isEmpty();
        System.out.println(p1.test(""));
        System.out.println(p2.test(""));
        BiPredicate<String, String> bp1 =  String::startsWith;
        BiPredicate<String, String> bp2 = (string, prefix) -> string.startsWith(prefix);
        System.out.println(bp1.test("chicken", "chick"));
        System.out.println(bp2.test("chicken", "chick"));

        //Defaukt methods on functional interfaces
        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");

        Predicate<String> brownEggs = s -> s.contains("egg") && s.contains("brown");
        Predicate<String> otherEggs = s -> s.contains("egg") && ! s.contains("brown");

        Predicate<String> brownEggs2 = egg.and(brown);
        Predicate<String> otherEggs2 = egg.and(brown.negate());


        //Function and BiFunctions
        /*
        @FunctionalInterface public class Function<T, R> {
            R apply(T t);
        }
        @FunctionalInterface public class BiFunction<T, U, R> {
            R apply(T t, U u);
        }
        */

        Function<String, Integer> f1 =  String::length;
        Function<String, Integer> f2 = x -> x.length();
        System.out.println(f1.apply("cluck"));  // 5
        System.out.println(f2.apply("cluck"));  // 5
        BiFunction<String, String, String> bf1 =  String::concat;
        BiFunction<String, String, String> bf2 = (string, toAdd) -> string.concat(toAdd);
        System.out.println(bf1.apply("baby ", "chick")); // baby chick
        System.out.println(bf2.apply("baby ", "chick")); // baby chick


        //Unary and Binary operations
        /*
        @FunctionalInterface public class UnaryOperator<T>
                extends Function<T, T> { }
        @FunctionalInterface public class BinaryOperator<T>
                extends BiFunction<T, T, T> { }
        */


    }
}
