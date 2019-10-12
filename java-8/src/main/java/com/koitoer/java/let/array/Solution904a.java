package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution904a {

    @Test
    public void test0() {
        Assertions.assertThat(new Solution904a().totalFruit(new int[] { 0, 1, 2, 2 })).isEqualTo(3);
    }

    @Test
    public void test() {
        Assertions.assertThat(new Solution904a().totalFruit(new int[] { 1, 0, 1, 4, 1, 4, 1, 2, 3 })).isEqualTo(5);
    }

    @Test
    public void test1() {
        Assertions.assertThat(new Solution904a().totalFruit(new int[] { 0, 1, 6, 6, 4, 4, 6 })).isEqualTo(5);
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution904a().totalFruit(new int[] { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 })).isEqualTo(5);

    }

    public int totalFruit(int[] tree) {
        if (tree.length <= 1) {
            return tree.length;
        }

        int start = 0;
        int end = 0;//firstDifValue(tree[start], tree);
        int maxTotal = 0;
        int sValue = tree[start];
        int endValue = tree[end];

        for (int i = 0; i < tree.length; i++) {
            int newValue = tree[i];
            if (newValue != sValue && newValue != endValue) {
                start = end;
                sValue = tree[start];
            }

            end = i;
            if (sValue != tree[i]) {
                endValue = tree[i];
            }

            maxTotal = Math.max(maxTotal, Math.abs(end - start + 1));
        }

        return maxTotal;
    }

    private int firstDifValue(int value, int[] tree) {
        for (int i = 0; i < tree.length; i++) {
            if (value != tree[i])
                return i;
        }
        return tree.length;
    }

}
