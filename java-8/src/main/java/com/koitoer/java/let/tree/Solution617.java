package com.koitoer.java.let.tree;

/**
 * 617. Merge Two Binary Trees
 * Iterate over the tree and in each node do null check if one of the inputs is null we return the other to concatenate the rest of the
 * tree, then we will stop the recursion once both are null, finally at any stage we will create the new node and assign a right and left
 * nodes.
 */
public class Solution617 {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if(t1 == null){
            return t2;
        }
        if(t2 == null){
            return t1;
        }

        if(t1 == null && t2 == null){
            return null;
        }

        TreeNode treeNode = new TreeNode(t1.val + t2.val);
        treeNode.right = mergeTrees(t1.right, t2.right);
        treeNode.left = mergeTrees(t1.left, t2.left);

        return  treeNode;
    }
}
