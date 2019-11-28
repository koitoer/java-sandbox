package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 15. 3Sum
 * Two pointer approach taking care of exclude similar numbers.
 */
public class Solution15a {

    @Test
    public void test() {
        int a[] = new int[] { -1, 0, 1, 2, -1, -4 };
        List result = new Solution15a().threeSum(a);
        System.out.println(result);
    }

    @Test
    public void test1() {
        int a[] = new int[] { -1, 0, 1, -10, 0, 10 };
        List result = new Solution15a().threeSum(a);
        System.out.println(result);
    }

    @Test
    public void testA() {
        int a[] = new int[] { 0, 0, 0, 0 };
        List result = new Solution15a().threeSum(a);
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            //If the previous value has been seen don't use it again
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int p1 = i + 1;
            int p2 = nums.length - 1;

            while (p1 < p2) {

                //If number p1 is equal to the previous we will skip
                if (p1 > i + 1 && nums[p1] == nums[p1 - 1]) {
                    p1++;
                    continue;
                }

                //If number p2 is equal to the next we will skip
                if (p2 < nums.length - 1 && nums[p2] == nums[p2 + 1]) {
                    p2--;
                    continue;
                }

                int sum = nums[i] + nums[p1] + nums[p2];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
                    p1++;
                    p2--;
                } else if (sum > 0) {
                    p2--;
                } else if (sum < 0) {
                    p1++;
                }

            }
        }

        return result;
    }

}
