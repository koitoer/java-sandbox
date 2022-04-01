package com.koitoer.java.algorithm;

import org.assertj.core.api.Assertions;

public class Solution34 {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[] { -1, -1 };

        return new int[] { binarySearch(nums, target, true), binarySearch(nums, target, false) };
    }

    public int binarySearch(int[] nums, int target, boolean minMax) {
        int l = 0, r = nums.length - 1;
        int index = -1;
        while (l <= r) {
            int m = l + ((r - l) / 2);
            if (target > nums[m]) {
                l = m + 1;
            } else if (target < nums[m]) {
                r = m - 1;
            } else if (target == nums[m]) {
                index = m;

                //Try to find minimum, right will be moved to m-1
                //Try to find maximum, left will be moved l+1
                if (minMax) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }

        return index;
    }

    public static void main(String[] args) {
        Assertions.assertThat(new Solution34().searchRange(new int[] { 5, 7, 7, 8, 9, 10 }, 8)).isEqualTo(new int[] { 3, 3 });
        Assertions.assertThat(new Solution34().searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8)).isEqualTo(new int[] { 3, 4 });
        Assertions.assertThat(new Solution34().searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 6)).isEqualTo(new int[] { -1, -1 });
    }
}
