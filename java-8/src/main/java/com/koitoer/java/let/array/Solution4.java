package com.koitoer.java.let.array;

import java.util.Arrays;

class Solution4 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        if (nums2.length == 0) {
            return;
        }

        int index = 0;
        int index2 = 0;
        while (index != nums1.length) {

            if (index >= m) {
                swap(nums1, index, nums2, index2);
                index2++;

            } else if (nums1[index] > nums2[index2]) {
                swap(nums1, index, nums2, index2);
                Arrays.sort(nums2);

            }
            index++;
        }

        print(nums1);
        print(nums2);
    }

    private void swap(int a[], int index, int b[], int index2) {
        int aux = a[index];
        a[index] = b[index2];
        b[index2] = aux;
    }

    private void print(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " : ");
        }
    }
}