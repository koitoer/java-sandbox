package com.koitoer.java.let.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution1043 {

    @Test
    public void test() {
        int[] A = new int[] { 1, 15, 7, 9, 2, 5, 10 };
        Assertions.assertThat(new Solution1043().maxSumAfterPartitioning(A, 3)).isEqualTo(84);
    }

    @Test
    public void testa() {
        int[] A = new int[] { 1 };
        Assertions.assertThat(new Solution1043().maxSumAfterPartitioning(A, 3)).isEqualTo(1);
    }

    public int maxSumAfterPartitioning(int[] A, int K) {
        int dp[] = new int[A.length];
        dp[0] = A[0];
        int maxValue = dp[0];

        for (int i = 1; i < A.length; i++) {

            for (int j = 1; j <= K; j++) {
                int leftPos = i - j;

                int left = 0;
                if (leftPos >= 0) {
                    left = dp[leftPos];
                }


                //Create groups and save the max value of the group.
                int rightMax = Integer.MIN_VALUE;
                for (int k = leftPos + 1; k <= i && k >= 0; k++) {
                    rightMax = Math.max(rightMax, A[k] * j);
                }

                //Store in dp the max value between the dp[i-1] vs the rightMax based on groups
                maxValue = Math.max(maxValue, left + rightMax);
            }
            dp[i] = maxValue;
        }

        return maxValue;
    }

}
