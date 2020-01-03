package com.koitoer.java.let.design;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution703 {

    @Test
    public void test() {
        int k = 3;
        int[] arr = { 4, 5, 8, 2 };
        KthLargest kthLargest = new KthLargest(k, arr);

        Assertions.assertThat(kthLargest.add(3)).isEqualTo(4);
        Assertions.assertThat(kthLargest.add(5)).isEqualTo(5);
        Assertions.assertThat(kthLargest.add(10)).isEqualTo(5);
        Assertions.assertThat(kthLargest.add(9)).isEqualTo(8);
        Assertions.assertThat(kthLargest.add(4)).isEqualTo(8);
    }

    @Test
    public void test2() {
        int k = 2;
        int[] arr = { 0 };
        KthLargest kthLargest = new KthLargest(k, arr);

        Assertions.assertThat(kthLargest.add(-1)).isEqualTo(-1);
        Assertions.assertThat(kthLargest.add(1)).isEqualTo(0);
        Assertions.assertThat(kthLargest.add(-2)).isEqualTo(0);
        Assertions.assertThat(kthLargest.add(-4)).isEqualTo(0);
        Assertions.assertThat(kthLargest.add(3)).isEqualTo(1);
    }

    @Test
    public void test3() {
        int k = 3;
        int[] arr = { 5, -1 };
        KthLargest kthLargest = new KthLargest(k, arr);

        Assertions.assertThat(kthLargest.add(2)).isEqualTo(-1);
        Assertions.assertThat(kthLargest.add(1)).isEqualTo(1);
        Assertions.assertThat(kthLargest.add(-1)).isEqualTo(1);
        Assertions.assertThat(kthLargest.add(3)).isEqualTo(2);
        Assertions.assertThat(kthLargest.add(4)).isEqualTo(3);
    }

    @Test
    public void test4() {
        int k = 7;

        //-10, 1 , 1, 1, 3, 3, 4, 4, 5, 9, 10

        int[] arr = { -10, 1, 3, 1, 4, 10, 3, 9, 4, 5, 1 };
        KthLargest kthLargest = new KthLargest(k, arr);

        Assertions.assertThat(kthLargest.add(3)).isEqualTo(3);
        Assertions.assertThat(kthLargest.add(2)).isEqualTo(3);

    }

}
