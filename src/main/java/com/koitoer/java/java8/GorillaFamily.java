package com.koitoer.java.java8;

import java.time.LocalDate;
import java.util.function.Supplier;

/**
 * Created by mauricio.mena on 16/06/2016.
 */
public class GorillaFamily {

    public static void main(String[] args) {
        new GorillaFamily().everyonePlay(true);


        //Functional Interfaces
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();

        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();

        System.out.println(d1);
        System.out.println(d2);

    }

    String walk = "walk";
    void everyonePlay(boolean baby){
        String approach = "run";
        // approach = "a";  Vars in lambda should be final or effectively final
        play(() -> walk);
        play(() -> baby ? "hitch a ride": "run");
        play(() -> approach);


    }


    void play(Gorilla g){
        System.out.println(g.move());
    }
}

interface Gorilla { String move(); }
