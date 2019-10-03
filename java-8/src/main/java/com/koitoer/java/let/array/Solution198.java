package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 198. House Robber
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
 * Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for House Robber.
 */
public class Solution198 {

    @Test
    public void test1(){
        int [] nums = {2,7,9,3,1};
        Assertions.assertThat(new Solution198().rob(nums)).isEqualTo(12);

        int [] nums2 = {1,2,3,1};
        Assertions.assertThat(new Solution198().rob(nums2)).isEqualTo(4);

        int[] nums3 = {2,1,1,2};
        Assertions.assertThat(new Solution198().rob(nums3)).isEqualTo(4);
    }

    public int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }

        int p[] = new int[nums.length];

        int max = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            max = visit(p, i, nums, max);
        }

        return max;
    }

    public int visit(int p[], int index, int nums[], int maxtotal) {
        int max = nums[index];
        int pointer = index + 2;
        int current = nums[index];

        while (pointer < nums.length) {
            current = Math.max(current, max + p[pointer]);
            pointer = pointer + 1;
        }

        p[index] = current;
        return Math.max(maxtotal, current);
    }

}
