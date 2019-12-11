package com.koitoer.java.let.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution1043a {

    @Test
    public void test() {
        int[] A = new int[] { 1, 15, 7, 9, 2, 5, 10 };
        Assertions.assertThat(new Solution1043a().maxSumAfterPartitioning(A, 3)).isEqualTo(84);
    }

    @Test
    public void testa() {
        int[] A = new int[] { 1 };
        Assertions.assertThat(new Solution1043a().maxSumAfterPartitioning(A, 3)).isEqualTo(1);
    }

    public int maxSumAfterPartitioning(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        // dp[i] represents the maximum sum for array 0...i-1
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            // put i into a separate group
            dp[i] = dp[i - 1] + A[i - 1];
            int maxNum = A[i - 1];
            // combine i into a group with at most K numbers
            for (int j = i - 1; j >= i - K + 1; j--) {
                if (j < 1) {
                    break;
                }
                // get the maximum value of the group
                maxNum = Math.max(maxNum, A[j - 1]);
                dp[i] = Math.max(dp[j - 1] + maxNum * (i - j + 1), dp[i]);
            }
        }
        return dp[n];
    }
}
