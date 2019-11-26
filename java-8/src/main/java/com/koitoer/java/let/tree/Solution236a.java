package com.koitoer.java.let.tree;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Important !!
 * Trick is find p or find q, and return that trough the stack once you have both you have found the LCA of the nodes.
 * Runtime: 6 ms, faster than 76.64% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
 */
public class Solution236a {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);

        if (l != null && r != null)
            return root;

        return l == null ? r : l;
    }

}
