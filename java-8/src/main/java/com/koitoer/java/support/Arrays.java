package com.koitoer.java.support;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Arrays {

    /**
     * length = to - from;
     * [ inclusive - exclusive ) as substring
     */
    @Test
    public void test() {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 5, 6, 7, 8, 9 };
        Assertions.assertThat(java.util.Arrays.copyOfRange(arr, 0, arr.length))
            .containsExactly(1, 2, 3, 4, 5, 5, 6, 7, 8, 9);

        Assertions.assertThat(java.util.Arrays.copyOfRange(arr, 8, arr.length))
            .containsExactly(8, 9);

        Assertions.assertThat(java.util.Arrays.copyOfRange(arr, 0, 2))
            .containsExactly(1, 2);
    }

}
