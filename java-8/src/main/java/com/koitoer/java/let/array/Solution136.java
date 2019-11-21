package com.koitoer.java.let.array;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 136. Single Number
 * Basically we sort and compare pos and pos++, if they are equals we jump if not just return.
 * The pointer will be moved 2 steps in each validation.
 * When the pointer is one element before the last , the last is the result.
 */
public class Solution136 {

    @Test
    public void test() {
        int[] a = new int[] { 2, 2, 1 };
        Assertions.assertThat(new Solution136().singleNumber(a)).isEqualTo(1);

        int b[] = new int[] { 4, 1, 2, 1, 2 };
        Assertions.assertThat(new Solution136().singleNumber(b)).isEqualTo(4);

    }

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            if (i == nums.length - 1) {
                return nums[i];
            }

            if (nums[i] == nums[i + 1]) {
                i++;
                continue;
            }

            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }

        return -1;
    }

}
