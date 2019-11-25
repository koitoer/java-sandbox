package com.koitoer.java.let.tree;

/**
 * 110. Balanced Binary Tree
 * Important to check.
 */
public class Solution110 {

    /**
     * The final expression is the important.
     * Verify between node left and node right there is not a major difference in height
     * Check the left side is balanced but also check the right is balanced as well.
     * When the node is null it will be always balanced. However if one of them is false that will be propagated across the stack.
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(heightAtNode(root.left) - heightAtNode(root.right)) <= 1
            && isBalanced(root.left) && isBalanced(root.right);
    }

    private int heightAtNode(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int l = heightAtNode(node.left);
        int r = heightAtNode(node.right);
        return Math.max(l, r) + 1;
    }
}
