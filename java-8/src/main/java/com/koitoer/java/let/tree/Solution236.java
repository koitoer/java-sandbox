package com.koitoer.java.let.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 */
public class Solution236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        List<TreeNode> l = new ArrayList();
        helper(root, p, q, l);
        return l.get(0);
    }

    private boolean helper(TreeNode node, TreeNode p, TreeNode q, List<TreeNode> a) {
        if (node == null) {
            return false;
        }

        boolean isThisNode = (node == p || node == q);
        boolean l = helper(node.left, p, q, a);
        boolean r = helper(node.right, p, q, a);

        //System.out.println("Val-> " + node.val +  " : " + l + " : " + r);
        if (l && r && a.isEmpty()) {
            a.add(node);
        } else if (l && isThisNode && a.isEmpty()) {
            a.add(node);
        } else if (r && isThisNode && a.isEmpty()) {
            a.add(node);
        }

        return l || r || isThisNode;

    }

}
