package com.koitoer.java.let.tree;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution108 {

    @Test
    public void test() {
        int[] a = { -10, -3, 0, 5, 9 };
        Assertions.assertThat(new Solution108().sortedArrayToBST(a));

        Assertions.assertThat(new Solution108().sortedArrayToBST2(a));
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int middle = nums.length / 2;
        int[] rightArray = Arrays.copyOfRange(nums, 0, middle);
        int[] leftArray = Arrays.copyOfRange(nums, middle + 1, nums.length);

        TreeNode treeNode = new TreeNode(nums[middle]);
        treeNode.right = sortedArrayToBST(rightArray);
        treeNode.left = sortedArrayToBST(leftArray);
        return treeNode;
    }

    public TreeNode sortedArrayToBST2(int[] nums) {
        return createTreeNode(nums, 0, nums.length - 1);
    }

    private TreeNode createTreeNode(int[] nums, int min, int max) {

        //If values are the same create the node and return it.
        if (min == max)
            return new TreeNode(nums[min]);

        // Calculate the middle based on the min + the half as we are considering the full array
        int middle = min + ((max - min) / 2);

        //Create that node
        TreeNode treeNode = new TreeNode(nums[middle]);

        //If the middle is not the min, we need to recurse
        if (middle != min) {
            //Don't consider the middle again
            treeNode.left = createTreeNode(nums, min, middle - 1);
            //Don't consider the middle again
            treeNode.right = createTreeNode(nums, middle + 1, max);
        } else {
            //If the middle is already the min, create the leaf node
            treeNode.right = new TreeNode(nums[max]);
        }

        return treeNode;
    }

}
