package com.koitoer.java.let.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution518b {

    @Test
    public void test4() {
        int[] coins = new int[] { 1, 2, 5 };
        Assertions.assertThat(new Solution518b().change(5, coins)).isEqualTo(4);
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins)
            for (int currAmt = 1; currAmt <= amount; currAmt++)
                if (currAmt >= coin)
                    dp[currAmt] += dp[currAmt - coin];
        return dp[amount];
    }

}
