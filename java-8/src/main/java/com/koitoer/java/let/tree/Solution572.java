package com.koitoer.java.let.tree;

/**
 * 572. Subtree of Another Tree
 * Use recursion check if if they are the same or the left part is equal or the right part is equal.
 */
public class Solution572 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return t == null;
        }
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        if (s.val == t.val) {
            boolean left = isSame(s.left, t.left);
            boolean right = isSame(s.right, t.right);
            return left && right;
        } else {
            return false;
        }
    }

}
