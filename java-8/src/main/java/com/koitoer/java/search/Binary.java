package com.koitoer.java.search;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * O(logn)
 * y= x ^ n  --->  n = (log y)/x
 */
public class Binary {

    @Test
    public void testBinary() {
        int arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        Assertions.assertThat(new Binary().binarySearch(arr, 7))
            .isEqualTo(6);
        Assertions.assertThat(new Binary().binarySearch(arr, 7))
            .isEqualTo(6);
        Assertions.assertThat(new Binary().binarySearch(arr, 1))
            .isEqualTo(0);
        Assertions.assertThat(new Binary().binarySearch(arr, 8))
            .isEqualTo(7);
        Assertions.assertThat(new Binary().binarySearch(arr, 11))
            .isEqualTo(10);
        Assertions.assertThat(new Binary().binarySearch(arr, 10))
            .isEqualTo(9);
    }

    @Test
    public void testBinary2() {
        int arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        Assertions.assertThat(new Binary().binarySearch2(arr, 8))
            .isEqualTo(7);
        Assertions.assertThat(new Binary().binarySearch2(arr, 7))
            .isEqualTo(6);
        Assertions.assertThat(new Binary().binarySearch2(arr, 7))
            .isEqualTo(6);
        Assertions.assertThat(new Binary().binarySearch2(arr, 1))
            .isEqualTo(0);
        Assertions.assertThat(new Binary().binarySearch2(arr, 11))
            .isEqualTo(10);
        Assertions.assertThat(new Binary().binarySearch2(arr, 10))
            .isEqualTo(9);
    }

    /**
     * Correct implementation of a binary search.
     */
    private int binarySearch2(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;

        // This will cover the case both pointers point to the next middle and that will be the element.
        while (left <= right) {

            //Get middle element
            int middle = left + (right - left) / 2;

            //Check if the element is not one of the current cursors
            if (arr[middle] == num) {
                return middle;
            }

            // Update cursors
            if (arr[middle] < num) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }

        }

        return 0;
    }

    private int binarySearch(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            //Get middle element
            int middle = left + (right - left) / 2;

            //Check if the element is not one of the current cursors
            if (arr[middle] == num) {
                return middle;
            } else if (arr[left] == num) {
                return left;
            } else if (arr[right] == num) {
                return right;
            }

            // Update cursors
            if (arr[left] < num) {
                left = middle + 1;
            } else {
                right = middle;
            }

        }

        return 0;
    }

}
