package com.koitoer.java.let.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution518a {

    @Test
    public void test4() {
        int[] coins = new int[] { 1, 2, 5 };
        Assertions.assertThat(new Solution518a().change(5, coins)).isEqualTo(4);
    }


    public int change(int amount, int[] coins) {
        int numberOfCoins = coins.length;

        //Create the dp array
        int dp[][] = new int[numberOfCoins + 1][amount + 1];
        //Initialize the array.
        dp[0][0] = 1;

        for (int row = 1; row < numberOfCoins + 1; row++) {
            //Initialize the first column in 1
            dp[row][0] = 1;

            for (int col = 0; col < amount + 1; col++) {

                //This is the coin that we are adding.
                int currentCoin = coins[row - 1];

                //We go up one cell as was the result of not using the new coin
                int withoutThisCoin = dp[row - 1][col];

                //Then using the new coin what will be the value
                //If the index goes out of bounds, that means we can not use that coin and we will add zero.
                int withThisCoin = (col - currentCoin) < 0 ? 0 : dp[row][col - currentCoin];

                //Result with be adding both values
                dp[row][col] = withoutThisCoin + withThisCoin;
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
