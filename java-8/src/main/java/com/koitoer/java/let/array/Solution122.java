package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 122. Best Time to Buy and Sell Stock II
 * The trick here is O(n) we need to iterate over the array finding local minima and local maxima no matter how
 * many of they are.
 */
public class Solution122 {

    @Test
    public void test() {
        int a[] = { 7, 1, 5, 3, 6, 4 };
        Assertions.assertThat(new Solution122().maxProfit(a)).isEqualTo(7);
    }

    @Test
    public void test1() {
        int a[] = { 1, 2, 3, 4, 5 };
        Assertions.assertThat(new Solution122().maxProfit(a)).isEqualTo(4);
    }

    @Test
    public void test2() {
        int a[] = { 7, 6, 4, 3, 1 };
        Assertions.assertThat(new Solution122().maxProfit(a)).isEqualTo(0);
    }

    /**
     * We need to find minimum and then the associated maximum. Be aware the end of the array can not be reached.
     * Once you find those you can start over.
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        int n = prices.length;
        int min = -1;
        int max = -1;

        for (int i = 0; i < prices.length; i++) {
            if (min == -1) {
                if (i + 1 == n || prices[i] < prices[i + 1]) {
                    min = prices[i];
                }
            } else if (max == -1) {
                if (i + 1 == n || prices[i] > prices[i + 1]) {
                    max = prices[i];
                }
            }

            if (min != -1 && max != -1) {
                profit += max - min;
                max = min = -1;
            }
        }

        return profit;
    }

}
