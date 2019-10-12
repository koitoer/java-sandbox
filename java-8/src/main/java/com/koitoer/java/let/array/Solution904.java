package com.koitoer.java.let.array;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution904 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution904().totalFruit(new int[] { 1, 0, 1, 4, 1, 4, 1, 2, 3 })).isEqualTo(5);
    }

    public int totalFruit(int[] tree) {
        if (tree.length <= 1) {
            return tree.length;
        }

        int maxTotal = 1;
        Set<Integer> set = new HashSet();

        for (int i = 0; i < tree.length; i++) {
            int maxLocal = 0;
            for (int j = i; j < tree.length; j++) {
                maxLocal++;
                set.add(tree[j]);
                if (set.size() > 2) {
                    break;
                }
                maxTotal = Math.max(maxLocal, maxTotal);
            }
            set = new HashSet();

        }

        return maxTotal;
    }

}
