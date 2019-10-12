package com.koitoer.java.let.array;

import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution904d {

    @Test
    public void testA() {
        Assertions.assertThat(new Solution904d().totalFruit(new int[] { 0 })).isEqualTo(1);
    }

    @Test
    public void testB() {
        Assertions.assertThat(new Solution904d().totalFruit(new int[] { 0, 1 })).isEqualTo(2);
    }

    @Test
    public void test0() {
        Assertions.assertThat(new Solution904d().totalFruit(new int[] { 0, 1, 2, 2 })).isEqualTo(3);
    }

    @Test
    public void test() {
        Assertions.assertThat(new Solution904d().totalFruit(new int[] { 1, 0, 1, 4, 1, 4, 1, 2, 3 })).isEqualTo(5);
    }

    @Test
    public void test1() {
        Assertions.assertThat(new Solution904d().totalFruit(new int[] { 0, 1, 6, 6, 4, 4, 6 })).isEqualTo(5);
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution904d().totalFruit(new int[] { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 })).isEqualTo(5);
    }

    @Test
    public void test3() {
        Assertions.assertThat(new Solution904d().totalFruit(
            new int[] { 1, 9, 1, 8, 22, 0, 22, 19, 19, 2, 19, 6, 6, 19, 2, 20, 2, 9, 9, 9, 9, 16, 19, 16, 19, 11, 19, 0, 19, 19 }))
            .isEqualTo(5);
    }

    // 0, 1, 2, 2
    // 1, 0, 1, 4, 1, 4, 1, 2, 3
    public int totalFruitw(int[] tree) {

        if (tree == null || tree.length == 0) {
            return 0;
        }

        int max = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int a = 0;
        int b = 0;

        while (b < tree.length) {

            //If we don't have more the two fruits
            if (map.size() <= 2) {
                //Add the value to the map and the location
                map.put(tree[b], b++);
            }

            if (map.size() > 2) {
                int min = tree.length - 1;
                for (int value : map.values()) {
                    min = Math.min(min, value);
                }

                a = min + 1;
                map.remove(tree[min]);
            }

            max = Math.max(max, b - a);

        }

        return max;
    }

    public int totalFruit(int[] tree) {

        if (tree == null || tree.length == 0) {
            return 0;
        }

        int max = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int a = 0;

        for (int b = 0; b < tree.length; b++) {

            //If we don't have more the two fruits
            if (map.size() <= 2) {
                //Add the value to the map and the last know location.
                map.put(tree[b], b);
            }

            //When we have more than 2 fruits we need to discard one.
            if (map.size() > 2) {

                //a at minimum needs to be length - 1
                int min = tree.length - 1;

                //Iterate over all the positions to get the minimum to replace a.
                for (int value : map.values()) {
                    min = Math.min(min, value);
                }

                //Now we have the min pointer add one to move one position.
                a = min + 1;
                //Remove the number that get out of the window.
                map.remove(tree[min]);
            }

            //Position are 0 based add 1
            max = Math.max(max, b - a + 1);

        }

        return max;
    }
}
