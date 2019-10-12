package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution904b {

    @Test
    public void test0() {
        Assertions.assertThat(new Solution904b().totalFruit(new int[] { 0, 1, 2, 2 })).isEqualTo(3);
    }

    @Test
    public void test() {
        Assertions.assertThat(new Solution904b().totalFruit(new int[] { 1, 0, 1, 4, 1, 4, 1, 2, 3 })).isEqualTo(5);
    }

    @Test
    public void test1() {
        Assertions.assertThat(new Solution904b().totalFruit(new int[] { 0, 1, 6, 6, 4, 4, 6 })).isEqualTo(5);
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution904b().totalFruit(new int[] { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 })).isEqualTo(5);

    }


    public int totalFruit(int[] tree) {

        int first = 0;
        int second = first;
        int maxTotal = 0;

        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != tree[second]) {
                if (second != 0  && tree[i] != tree[second - 1]) {
                    first = second;
                }
                second = i;
            }
            int cur = Math.abs(first - i) + 1;
            maxTotal = Math.max(maxTotal, cur);

        }

        return maxTotal;
    }


}
