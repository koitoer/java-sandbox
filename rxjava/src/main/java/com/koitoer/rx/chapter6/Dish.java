package com.koitoer.rx.chapter6;

import java.util.Arrays;

/**
 * Created by mmena on 4/10/17.
 */
public class Dish {

    private final byte[] oneKb = new byte[1_024];
    private final int id;

    Dish(int id){
        this.id = id;
        System.out.println("Created " + id);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                '}';
    }
}
