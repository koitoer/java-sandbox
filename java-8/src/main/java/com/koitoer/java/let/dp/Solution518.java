package com.koitoer.java.let.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution518 {

    @Test
    public void test4() {
        int[] coins = new int[] { 1, 2, 5 };
        Assertions.assertThat(new Solution518().change(5, coins)).isEqualTo(4);
    }

    public int change(int amount, int[] coins) {
        int numberOfCoins = coins.length;

        //Create the dp array
        int dp[][] = new int[numberOfCoins + 1][amount + 1];
        //Initialize the array.
        dp[0][0] = 1;

        for (int row = 1; row < numberOfCoins + 1; row++) {
            for (int col = 0; col < amount + 1; col++) {

                //If the column is zero we will set that to 1 for initialization
                if (col == 0) {
                    dp[row][col] = 1;

                //If there is coins that can be used we do the following.
                } else if (col - coins[row - 1] >= 0) {
                    int noUsingTheNewCoin = dp[row - 1][col];
                    int usingTheNewCoin = dp[row][col - coins[row - 1]];
                    dp[row][col] = noUsingTheNewCoin + usingTheNewCoin;

                //We don't have a coin that can be used because is larger.
                } else {
                    int noUsingTheNewCoin = dp[row - 1][col];
                    dp[row][col] = noUsingTheNewCoin;
                }
            }
        }

        print(dp);

        return dp[numberOfCoins][amount];
    }

    private void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
