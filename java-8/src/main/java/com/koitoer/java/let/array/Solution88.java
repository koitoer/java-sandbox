package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 88. Merge Sorted Array
 * This solution use 3 pointers, 1 in nums1 , 2nd in nums2 and the last one in nums1
 * It start filling the zeros, until the point that the first 2 pointers are empty.
 * All the pointer decrease every time we have a match. If one of the indexes goes below zero
 * We just substitute the value for Integer.MIN_VALUE to select always the other.
 * Take care with all the PA and PB when they are negative indexes.
 */
public class Solution88 {

    @Test
    public void test() {
        int[] a = { 1, 2, 3, 0, 0, 0 };
        int[] b = { 2, 5, 6 };
        new Solution88().merge(a, 3, b, 3);
        Assertions.assertThat(a).containsExactly(1, 2, 2, 3, 5, 6);
    }

    @Test
    public void testA() {
        int[] a = { 0 };
        int[] b = { 1 };
        new Solution88().merge(a, 0, b, 1);
        Assertions.assertThat(a).containsExactly(1);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int pA = m - 1;
        int pB = n - 1;
        int index = nums1.length - 1;

        while (index >= 0) {
            int valA = pA >= 0 ? nums1[pA] : Integer.MIN_VALUE;
            int valB = pB >= 0 ? nums2[pB] : Integer.MIN_VALUE;

            if (valA >= valB) {
                nums1[index] = valA;
                pA--;
            } else {
                nums1[index] = valB;
                pB--;
            }
            index--;
        }
    }
}
