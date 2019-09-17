package com.koitoer.java.let.tree;

import org.junit.Test;

public class Solution104 {

    @Test
    public void test1(){
        //[3,9,20,null,null,15,7] == 3
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
    class Solution {

        public int maxDepth(TreeNode root) {
            return visit(root);
        }

        public int visit(TreeNode r) {

            if (r == null) {
                return 0;
            }

            return 1 + Math.max(visit(r.left), visit(r.right));
        }

        public int maxDepth2(TreeNode root) {
            if (root == null) return 0;
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

}

class TreeNode {

    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
