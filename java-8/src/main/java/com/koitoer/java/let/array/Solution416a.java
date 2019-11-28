package com.koitoer.java.let.array;

import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution416a {

    @Test
    public void test() {
        int a[] = new int[] { 1, 5, 11, 5 };
        Assertions.assertThat(new Solution416a().canPartition(a)).isTrue();
    }

    @Test
    public void testB() {
        int a[] = new int[] { 1, 3, 4, 4 };
        Assertions.assertThat(new Solution416a().canPartition(a)).isFalse();
    }

    @Test
    public void testC() {
        int a[] = new int[] { 3, 3, 3, 4, 5 };
        Assertions.assertThat(new Solution416a().canPartition(a)).isTrue();
    }

    @Test
    public void testD() {
        int a[] = new int[] { 1, 1, 1, 1 };
        Assertions.assertThat(new Solution416a().canPartition(a)).isTrue();
    }

    @Test
    public void testE() {
        int a[] = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        Assertions.assertThat(new Solution416a().canPartition(a)).isTrue();
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;

        if (sum % 2 != 0) {
            return false;
        }

        HashMap<String, Boolean> state = new HashMap<>();
        boolean r = canPartition(nums, 0, 0, sum, state);
        return r;
    }

    private boolean canPartition(int[] nums, int index, int currentSum, int total, HashMap<String, Boolean> state) {

        String current = index + " " + currentSum;
        if (state.get(current) != null) {
            return state.get(current);
        }

        if (currentSum == total / 2) {
            return true;
        }
        if (currentSum * 2 > total || index > nums.length - 1) {
            return false;
        }

        boolean foundPartition = canPartition(nums, index + 1, currentSum, total, state) ||
            canPartition(nums, index + 1, currentSum + nums[index], total, state);

        state.put(current, foundPartition);

        return foundPartition;
    }

    /**

     public boolean canPartition(int[] nums) {
     int sum = 0;
     for (int n : nums)
     sum += n;
     if (1 == (sum & 1))
     return false;
     int v = (sum >> 1);
     boolean[] dp = new boolean[v + 1];
     dp[0] = true;
     for (int n : nums) {
     for (int i = v; i >= n; --i)
     dp[i] |= dp[i - n];
     if (dp[v])
     return true;
     }
     return dp[v];
     }

     public boolean canPartition(int[] nums) {
     int total = 0;
     for (int i : nums) {
     total += i;
     }
     if (total % 2 != 0)
     return false;
     int half = total / 2;
     boolean dp[] = new boolean[half + 1];
     dp[half] = true;
     for (int i = 0; i < nums.length; i++) {
     if (nums[i] > half)
     return false;
     if (dp[nums[i]]) {
     return true;
     } else {
     for (int j = 1; j <= half; j++) {
     if (dp[j] && j - nums[i] > 0) {
     dp[j - nums[i]] = true;
     }
     }
     dp[half - nums[i]] = true;
     }

     }
     return false;
     }
     **/
}
