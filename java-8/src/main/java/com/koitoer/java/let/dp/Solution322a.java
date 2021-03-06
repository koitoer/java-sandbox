package com.koitoer.java.let.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution322a {

    @Test
    public void test() {
        int[] coins = new int[] { 1, 2, 5 };
        Assertions.assertThat(new Solution322a().coinChange(coins, 11)).isEqualTo(3);
    }

    @Test
    public void testA() {
        int[] coins = new int[] { 1, 2 };
        Assertions.assertThat(new Solution322a().coinChange(coins, 12)).isEqualTo(6);
    }

    @Test
    public void test2() {
        int[] coins = new int[] { 2 };
        Assertions.assertThat(new Solution322a().coinChange(coins, 3)).isEqualTo(-1);
    }

    @Test
    public void test4() {
        int[] coins = new int[] { 186, 419, 83, 408 };
        Assertions.assertThat(new Solution322a().coinChange(coins, 6249)).isEqualTo(20);
    }

    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];

        for (int i = 0; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

}
