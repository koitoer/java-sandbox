package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 53. Maximum Subarray
 * This is a classic Dp question, the dp will track the sum until the previous value
 */
public class Solution52a {

    @Test
    public void test() {
        int[] a = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        Assertions.assertThat(new Solution52a().maxSubArray(a)).isEqualTo(6);
    }

    @Test
    public void test2() {
        int[] a = new int[] { -2, 1 };
        Assertions.assertThat(new Solution52a().maxSubArray(a)).isEqualTo(1);
    }

    /**
     * This is dp you just need to follow the next equation.
     * dp[i] = max(dp[i-1] + n[i], n[i]);
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];

        if (nums.length == 1) {
            return max;
        }

        int maxTotal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1] + nums[i], nums[i]);
            maxTotal = Math.max(maxTotal, nums[i]);
        }
        return maxTotal;
    }

}
