package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution904c {

    @Test
    public void testA() {
        Assertions.assertThat(new Solution904c().totalFruit(new int[] { 0 })).isEqualTo(1);
    }

    @Test
    public void testB() {
        Assertions.assertThat(new Solution904c().totalFruit(new int[] { 0, 1 })).isEqualTo(2);
    }

    @Test
    public void test0() {
        Assertions.assertThat(new Solution904c().totalFruit(new int[] { 0, 1, 2, 2 })).isEqualTo(3);
    }

    @Test
    public void test() {
        Assertions.assertThat(new Solution904c().totalFruit(new int[] { 1, 0, 1, 4, 1, 4, 1, 2, 3 })).isEqualTo(5);
    }

    @Test
    public void test1() {
        Assertions.assertThat(new Solution904c().totalFruit(new int[] { 0, 1, 6, 6, 4, 4, 6 })).isEqualTo(5);
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution904c().totalFruit(new int[] { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 })).isEqualTo(5);

    }

    @Test
    public void test3(){
        Assertions.assertThat(new Solution904c().totalFruit(new int[] {1,9,1,8,22,0,22,19,19,2,19,6,6,19,2,20,2,9,9,9,9,16,19,16,19,11,19,0,19,19})).isEqualTo(5);
    }

    public int totalFruit(int[] tree) {

        Bucket b = new Bucket();
        for (int i = 0; i < tree.length; i++) {
            b.addInt(tree[i], i, tree);
        }

        return b.maxTotal;
    }

}

class Bucket {

    public int maxTotal = 0;

    int maxStep = 0;

    int apos = 0;

    int bpos = 0;

    int a = Integer.MIN_VALUE;

    int b = Integer.MIN_VALUE;

    public void addInt(int n, int pos, int[] tree) {
        add(n, pos);
        maxStep = currentFruits();
        maxTotal = Math.max(maxTotal, maxStep);
    }

    //0, 1, 2, 2
    // 1,9,1,8,22,0,22,19,19,2,19,6,6,19,2,20,2,9,9,9,9,16,19,16,19,11,19,0,19,19
    private void add(int n, int pos) {

        if (a == Integer.MIN_VALUE) {
            a = n;
            //apos = pos;
            return;
        } else if (a == n) {
            //apos = pos;
            return;
        }

        if (b == Integer.MIN_VALUE) {
            b = n;
            //bpos = pos;
            return;
        } else if (b == n) {
            //bpos = pos;
            return;
        }

        if (apos < bpos) {
            a = n;
            apos = pos;
        } else {
            b = n;
            bpos = pos;
        }

    }

    private int currentFruits() {
        return Math.abs(apos - bpos);
    }

}

