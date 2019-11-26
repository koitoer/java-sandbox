package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution52 {

    @Test
    public void test() {
        int[] a = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        Assertions.assertThat(new Solution52().maxSubArray(a)).isEqualTo(6);
    }

    @Test
    public void test2() {
        int[] a = new int[] { -2, 1 };
        Assertions.assertThat(new Solution52().maxSubArray(a)).isEqualTo(1);
    }

    public int maxSubArray(int[] nums) {
        int max = nums[0];

        if (nums.length == 1) {
            return max;
        }

        int maxTotal = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            max = nums[i];
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(max, sum);
            }
            maxTotal = Math.max(maxTotal, max);
            System.out.println("i-> " + i + " sum-> " + max);
        }

        return maxTotal;
    }

}
