package com.koitoer.java.let.tree;

/**
 * This solution does not works it ignores cases when only one single node is part of the other tree as
 * [1, 1] -> [1]
 */
public class Solution572a {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        boolean left = false;
        boolean right = false;

        if (s.val == t.val) {
            left = isSubtree(s.left, t.left);
            right = isSubtree(s.right, t.right);
            return left && right;
        } else {
            left = isSubtree(s.left, t);
            right = isSubtree(s.right, t);
        }
        return (s.val == t.val) || left || right;

    }
}
