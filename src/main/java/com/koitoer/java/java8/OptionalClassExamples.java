package com.koitoer.java.java8;

import java.util.Optional;

/**
 * Created by mauricio.mena on 16/06/2016.
 */
public class OptionalClassExamples {

    public static Optional<Double> average(int... scores) {
          if (scores.length == 0) return Optional.empty();
          int sum = 0;
          for (int score: scores) sum += score;
          return Optional.of((double) sum / scores.length);
    }


    public static void main(String[] args) {
        System.out.println(average(90, 100)); // Optional[95.0]
        System.out.println(average());        // Optional.empty

        Optional<Double> opt = average(90, 100);
        if (opt.isPresent())
            System.out.println(opt.get()); // 95.0


        Optional<Double> opt2 = average();
        //System.out.println(opt2.get());  // java.util.NoSuchElementException: No value present


        Optional<Double> opt3 = average();
        System.out.println(opt3.orElse(Double.NaN));
        System.out.println(opt3.orElseGet(() -> Math.random()));
        System.out.println(opt3.orElseThrow(() -> new IllegalStateException()));



    }
}
