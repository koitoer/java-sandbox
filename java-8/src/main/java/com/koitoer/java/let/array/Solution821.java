package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 821. Shortest Distance to a Character
 * We initialize in MAX value the distance.
 * We iterate until we found the char and the calculate the distance to all of the others.
 * We keep doing it until there are no more chars.
 * That will update the distance using the Min function.
 */
public class Solution821 {

    @Test
    public void test1() {
        Assertions.assertThat(new Solution821().shortestToChar("loveleetcode", 'e'))
            .containsExactly(3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0);
    }

    public int[] shortestToChar(String S, char C) {
        int[] a = new int[S.length()];

        for (int i = 0; i < S.length(); i++) {
            a[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < S.length(); i++) {
            char aux = S.charAt(i);
            if (aux == C) {
                updateArray(a, i);
            }
        }

        return a;
    }

    public void updateArray(int[] a, int index) {
        for (int i = 0; i < a.length; i++) {
            a[i] = Math.min(a[i], Math.abs(i - index));
        }
    }
}
