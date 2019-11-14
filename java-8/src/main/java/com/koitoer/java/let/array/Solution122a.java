package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 122. Best Time to Buy and Sell Stock II
 * It will be always a positive slop, you just need to add that number.
 */
public class Solution122a {

    @Test
    public void test() {
        int a[] = { 7, 1, 5, 3, 6, 4 };
        Assertions.assertThat(new Solution122a().maxProfit(a)).isEqualTo(7);
    }

    @Test
    public void test1() {
        int a[] = { 1, 2, 3, 4, 5 };
        Assertions.assertThat(new Solution122a().maxProfit(a)).isEqualTo(4);
    }

    @Test
    public void test2() {
        int a[] = { 7, 6, 4, 3, 1 };
        Assertions.assertThat(new Solution122a().maxProfit(a)).isEqualTo(0);
    }

    /**
     * This is O(n) as well, we iterate over the array.
     * if prices in pos i > pos (i-1) , is greatest we star maxProfit.
     * then we just add pos i - pos(i-1)
     */
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
