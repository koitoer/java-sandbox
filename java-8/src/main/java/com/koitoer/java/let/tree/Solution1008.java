package com.koitoer.java.let.tree;

/**
 * 1008. Construct Binary Search Tree from Preorder Traversal
 * The Preorder is normal order you create an empty root and just push elements via a recursive function.
 */
public class Solution1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int p : preorder) {
            root = insertIntoBST(root, p);
        }
        return root;
    }

    private TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val <= val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }
}
