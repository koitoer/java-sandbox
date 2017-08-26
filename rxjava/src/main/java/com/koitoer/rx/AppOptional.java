package com.koitoer.rx;

import java.util.Optional;

/**
 * Created by mmena on 7/3/17.
 */
public class AppOptional {

    public static void main(String[] args) {

        Optional<String> optional = Optional.of("A");
        System.out.println(optional.orElse("B"));

        optional = Optional.ofNullable(null);
        System.out.println(optional.orElse("B"));

        optional = Optional.of(null);
        System.out.println(optional.orElse("B"));

    }

}
