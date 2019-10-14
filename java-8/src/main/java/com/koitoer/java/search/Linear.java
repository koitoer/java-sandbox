package com.koitoer.java.search;

import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Linear {

    @Test
    public void testLinear() {
        int arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        Assertions.assertThat(new Linear().linearSearch(arr, 5))
            .isEqualTo(4);
        Assertions.assertThat(new Linear().linearStream(arr, 5))
            .isEqualTo(4);
    }

    private int linearSearch(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return 0;
    }

    private int linearStream(int[] arr, int num) {
        return IntStream
            .range(0, arr.length)
            .filter(i -> arr[i] == num)
            .findFirst().getAsInt();
    }

}
