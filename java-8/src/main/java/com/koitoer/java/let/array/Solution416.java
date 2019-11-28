package com.koitoer.java.let.array;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 416. Partition Equal Subset Sum
 * Two pointer NOT accepted solution.
 */
public class Solution416 {

    @Test
    public void test() {
        int a[] = new int[] { 1, 5, 11, 5 };
        Assertions.assertThat(new Solution416().canPartition(a)).isTrue();
    }

    @Test
    public void testB() {
        int a[] = new int[] { 1, 3, 4, 4 };
        Assertions.assertThat(new Solution416().canPartition(a)).isFalse();
    }

    @Test
    public void testC() {
        int a[] = new int[] { 3, 3, 3, 4, 5 };
        Assertions.assertThat(new Solution416().canPartition(a)).isTrue();
    }

    @Test
    public void testD() {
        int a[] = new int[] { 1, 1, 1, 1 };
        Assertions.assertThat(new Solution416().canPartition(a)).isTrue();
    }

    @Test
    public void testE() {
        int a[] = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        Assertions.assertThat(new Solution416().canPartition(a)).isTrue();
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Arrays.sort(nums);
        int p1 = 0;
        int sum1 = 0;
        int p2 = nums.length - 1;
        int sum2 = 0;

        sum1 += nums[p1];
        sum2 += nums[p2];

        do {
            if (sum1 < sum2) {
                p1++;
                sum1 += nums[p1];
            } else if (sum2 < sum1) {
                p2--;
                sum2 += nums[p2];
            } else {
                if (usedAll(p1, p2, nums.length)) {
                    return true;
                }
                p1++;
                sum1 += nums[p1];
            }

        } while (p1 < p2);

        return false;

    }

    private boolean usedAll(int p1, int p2, int length) {
        int cur = (p1 + 1) + length - p2;
        return length == cur;
    }
}
