package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 198. House Robber
 * This go from 0 to N
 * We calculate from 1 house, from 2 house.
 * Then we just choose which is the max between the current value + i- 2 OR i-1 and stick with that valuee
 */
public class Solution198a {

    @Test
    public void test1() {
        int[] nums = { 2, 7, 9, 3, 1 };
        Assertions.assertThat(new Solution198a().rob(nums)).isEqualTo(12);

        int[] nums2 = { 1, 2, 3, 1 };
        Assertions.assertThat(new Solution198a().rob(nums2)).isEqualTo(4);

        int[] nums3 = { 2, 1, 1, 2 };
        Assertions.assertThat(new Solution198a().rob(nums3)).isEqualTo(4);
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int p[] = new int[nums.length];
        p[0] = nums[0];
        p[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            p[i] = Math.max(nums[i] + p[i - 2], p[i - 1]);
        }

        return p[nums.length - 1];
    }

}
