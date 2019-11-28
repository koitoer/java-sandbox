package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 15. 3Sum
 * Approach time exceed.
 */
public class Solution15 {

    @Test
    public void test() {
        int a[] = new int[] { -1, 0, 1, 2, -1, -4 };
        List result = new Solution15().threeSum(a);
        System.out.println(result);
    }

    @Test
    public void test1() {
        int a[] = new int[] { -1, 0, 1, -10, 0, 10 };
        List result = new Solution15().threeSum(a);
        System.out.println(result);
    }

    @Test
    public void testA() {
        int a[] = new int[] { 0, 0, 0, 0 };
        List result = new Solution15().threeSum(a);
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int p1 = i + 1;

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int p2 = nums.length - 1;

            while (p1 < p2) {

                int sum = nums[i] + nums[p1] + nums[p2];
                if (sum == 0) {
                    if (!result.contains(Arrays.asList(nums[i], nums[p1], nums[p2]))) {
                        result.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
                    }
                }
                p1++;
                if (p1 == p2) {
                    p1 = i + 1;
                    p2--;
                }
            }
        }

        return result;
    }

}
