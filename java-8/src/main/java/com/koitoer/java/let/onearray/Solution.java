package com.koitoer.java.let.onearray;

/**
 * Created by mmena on 4/1/19.
 */
class Solution {

    public static void main(String[] args) {
        int[] a = new Solution().twoSum(new int[]{3,2,4}, 6);
        System.out.println(a[0] + " " + a[1]);

        int[] b = new Solution().twoSum(new int[]{2,7,11, 15}, 9);
        System.out.println(b[0] + " " + b[1]);

        int[] c = new Solution().twoSum(new int[]{-1,-2,-3,-4,-5}, -8);
        System.out.println(c[0] + " " + c[1]);
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int firstValue = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int secondValue = nums[j];
                if (firstValue + secondValue == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { 0, 0 };
    }
}