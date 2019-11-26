package com.koitoer.java.let.tree;

/**
 * 669. Trim a Binary Search Tree
 * In this case first we go deep then we analyze.
 */
public class Solution669 {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        if (root.val < L) {
            return root.right;
        } else if (root.val > R) {
            return root.left;
        }

        return root;
    }
}
