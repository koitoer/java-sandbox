package com.koitoer.java.let.tree;

import org.junit.Test;

/**
 * 965. Univalued Binary Tree
 * It is case of recursion over the tree, checking the value that was seeing first.
 */
public class Solution965 {

    @Test
    public void test1(){
        //[1,1,1,1,1,null,1] == true;
        //[2,2,2,5,2]  == false

    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public boolean isUnivalTree(TreeNode root) {
        return visit(root, root.val);
    }

    public boolean visit(TreeNode root, int value) {

        //Check if node is not null, if is null we always return true
        if (root == null) {
            return true;
        }

        //Check left
        boolean left = visit(root.left, value);
        //Check left
        boolean right = visit(root.right, value);

        //The val needs to be equal to the initial value and right and true as well.
        return root.val == value && left && right;
    }

}
