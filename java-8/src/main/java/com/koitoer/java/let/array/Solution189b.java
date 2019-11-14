package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 189. Rotate Array
 */
public class Solution189b {

    @Test
    public void test() {
        int a[] = { 1, 2, 3, 4, 5, 6, 7 };

        new Solution189b().rotate(a, 3);
        Assertions.assertThat(a).isEqualTo(new int[] { 5, 6, 7, 1, 2, 3, 4 });
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;

            } while (start != current);
        }
    }

}
