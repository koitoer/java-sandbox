package com.koitoer.java.let.tree;

import java.util.Arrays;

import org.junit.Test;

/**
 * 654. Maximum Binary Tree
 * Divide and conquer problem.
 */
public class Solution654 {

    @Test
    public void test() {
        int a[] = new int[] { 3, 2, 1, 6, 0, 5 };
        new Solution654().constructMaximumBinaryTree(a);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int i = 0;
        int indexMax = 0;
        int maxValue = Integer.MIN_VALUE;

        for (int a : nums) {
            if (a > maxValue) {
                maxValue = a;
                indexMax = i;
            }
            i++;
        }

        TreeNode root = new TreeNode(maxValue);
        int left[] = Arrays.copyOfRange(nums, 0, indexMax);
        root.left = constructMaximumBinaryTree(left);

        int right[] = Arrays.copyOfRange(nums, indexMax + 1, nums.length);
        root.right = constructMaximumBinaryTree(right);

        return root;
    }
}
