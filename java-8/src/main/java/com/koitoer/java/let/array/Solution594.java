package com.koitoer.java.let.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void testA() {
        int a[] = new int[] { 1, 1, 1, 1 };
        Assertions.assertThat(new Solution594().findLHS(a)).isEqualTo(0);
    }

    @Test
    public void testB() {
        int a[] = new int[] { 1, 2, 2, 1 };
        Assertions.assertThat(new Solution594().findLHS(a)).isEqualTo(4);
    }

    @Test
    public void testC() {
        int a[] = new int[] { 1, 2, 3, 4 };
        Assertions.assertThat(new Solution594().findLHS(a)).isEqualTo(2);
    }

    @Test
    public void testD() {
        int a[] = new int[] { 1, 3, 5, 7, 9, 11, 13, 15, 17 };
        Assertions.assertThat(new Solution594().findLHS(a)).isEqualTo(0);
    }

    @Test
    public void testE() {
        int a[] = new int[] { 1, 4, 1, 3, 1, -14, 1, -13 };
        Assertions.assertThat(new Solution594().findLHS(a)).isEqualTo(2);
    }

    public int findLHS(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int size = nums.length;
        int max = 0, p = 0, p2 = 0;
        Arrays.sort(nums);

        Set<Integer> b = new HashSet<>();

        while (p < size && p2 < size) {
            p2 = p + 1;
            if (!b.contains(nums[p])) {
                b.add(nums[p]);

                while (p2 < size) {
                    int diff = Math.abs(nums[p] - nums[p2]);
                    if (diff == 0) {
                        p2++;
                    } else if (diff <= 1) {
                        p2++;
                        max = Math.max(Math.abs(p2 - p), max);
                    } else {
                        break;
                    }
                }
            }
            //System.out.println("p-> " + p + " : p2-> " + p2 + " max-> " + max + " cur-> " + Math.abs(p2 - p));
            p++;
        }
        return max;
    }
}
