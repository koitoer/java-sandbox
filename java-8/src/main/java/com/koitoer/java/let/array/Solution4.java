package com.koitoer.java.let.array;

import java.util.Arrays;

class Solution4 {

    //This solution use 3 pointers, 1 in nums1 , 2nd in nums2 and the last one in nums1
    //It start filling the zeros, until the point that the first 2 pointers are empty.
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int index = nums1.length - 1;

        //Needs to end when index == 0
        while (index >= 0) {
            int i1 = p1 >= 0 ? nums1[p1] : Integer.MIN_VALUE;
            int i2 = p2 >= 0 ? nums2[p2] : Integer.MIN_VALUE;

            if (i1 >= i2) {
                nums1[index] = i1;
                p1--;
            } else {
                nums1[index] = i2;
                p2--;
            }
            index--;

        }

    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {

        if (nums2.length == 0) {
            return;
        }

        for (int i = 0; i < nums2.length; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);

    }

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