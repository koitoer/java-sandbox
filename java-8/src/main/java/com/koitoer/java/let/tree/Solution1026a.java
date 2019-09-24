package com.koitoer.java.let.tree;

import org.junit.Test;

/**
 * 1026. Maximum Difference Between Node and Ancestor
 * This is the optimized version it does not require adittional data structure.
 * First we check if the node is null, if so return 0.
 *
 * The visit method will pass the min and max, those values are the values that are in the path of a DFS.
 * With this values you always will be able to calculate the maximum value on each visit as it could be negative values.
 * we will update those values across the tree.
 *
 * Finally we just visit left and right and do the same.
 * Finally we get the maximum of the node parent / right and left nodes.
 *
 * Traverse down from root to each leaf node, while doing so keep track of the min and max values along the path and keep updating
 * the maxDiff as you travel along each path.
 */
public class Solution1026a {

    @Test
    public void test1(){
        //Input: [8,3,10,1,6,null,14,null,null,4,7,13] == 7
    }
    public int maxAncestorDiff(TreeNode root) {
        return visit(root, root.val, root.val);
    }

    public int visit(TreeNode root, int min, int max){
        if(root == null){
            return 0;
        }

        int a = Math.max(Math.abs(root.val-min), Math.abs(root.val-max));
        if(root.val < min)
            min = root.val;
        if(root.val > max)
            max = root.val;
        int b = Math.max(visit(root.left, min, max), visit(root.right, min, max));
        return Math.max(a, b);

    }
}
