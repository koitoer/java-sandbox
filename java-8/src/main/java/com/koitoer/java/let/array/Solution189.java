package com.koitoer.java.let.array;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 189. Rotate Array
 */
public class Solution189 {

    @Test
    public void test() {
        int a[] = { 1, 2, 3, 4, 5, 6, 7 };
        new Solution189().rotate(a, 3);
        Assertions.assertThat(a).isEqualTo(new int[] { 5, 6, 7, 1, 2, 3, 4 });

    }

    /**
     * This solution split the array in both sections and then merge that in the original.
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;

        if (k == n) {
            return;
        }

        if (k > n) {
            k = k % n;
        }

        int p1 = n - k;

        int[] end = Arrays.copyOf(nums, p1);
        int[] start = Arrays.copyOfRange(nums, p1, n);

        concatenateArrays(nums, start, end);
    }

    private void concatenateArrays(int nums[], int array1[], int array2[]) {
        int pos = 0;
        for (int element : array1) {
            nums[pos] = element;
            pos++;
        }
        for (int element : array2) {
            nums[pos] = element;
            pos++;
        }
    }

    private void printArray(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " : ");
        }
        System.out.println();
    }

}
