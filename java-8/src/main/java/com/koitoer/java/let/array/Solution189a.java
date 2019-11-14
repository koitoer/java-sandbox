package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 189. Rotate Array
 */
public class Solution189a {

    @Test
    public void test() {
        int a[] = { 1, 2, 3, 4, 5, 6, 7 };

        new Solution189a().rotate(a, 3);
        Assertions.assertThat(a).isEqualTo(new int[] { 5, 6, 7, 1, 2, 3, 4 });
    }

    @Test
    public void test1(){
        int a[] = { 1, 2, 3, 4, 5, 6, 7 };
        new Solution189a().reverse(a, 0 , a.length -1);
        Assertions.assertThat(a).isEqualTo(new int[] { 7, 6, 5, 4, 3, 2, 1 });

    }

    /**
     * This solution will reverse numbers in their positions 3 times.
     * Reverse the entire array.
     * Reverse from 0 to k-1.
     * Reverse from k to end-1
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while(start < end){
            int a = nums[start];
            int b = nums[end];
            nums[start] = b;
            nums[end] = a;
            start++;
            end--;
        }
    }

}
