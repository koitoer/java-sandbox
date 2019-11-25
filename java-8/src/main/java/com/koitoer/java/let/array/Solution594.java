package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 594. Longest Harmonious Subsequence
 * We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 */
public class Solution594 {

    @Test
    public void test() {
        int a[] = new int[] { 1, 3, 2, 2, 5, 2, 3, 7 };
        Assertions.assertThat(new Solution594().findLHS(a)).isEqualTo(5);
        //[3,2,2,2,3]
    }

    public int findLHS(int[] nums) {
        return 0;
    }

}
