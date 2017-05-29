package com.koitoer.java.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Created by mmena on 3/21/17.
 */
public class StreamPractice {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("A","b","c","koiter","","",".","a");

        long count = words.stream().filter(s ->  !s.isEmpty()).map(x -> 1L).count();
        System.out.println(count);

        count = words.stream().parallel().filter(s ->  !s.isEmpty()).map(x -> 1L).count();
        System.out.println(count);

        Optional<String> ws = words.stream().filter(s ->  !s.isEmpty()).reduce((a, b) -> a + " " + b);
        System.out.println(ws);

      //  ws = words.stream().filter(s ->  !s.isEmpty()).collect(Collectors.joining(" "));
       // System.out.println(ws);

    }

}
