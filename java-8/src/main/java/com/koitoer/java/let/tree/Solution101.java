package com.koitoer.java.let.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 101. Symmetric Tree
 * The trick is evaluate if the left and right are different of null
 * check left.rigth vs right left
 * check left.left vs right.right
 */
public class Solution101 {

    public boolean isSymmetric(TreeNode root) {
        return root == null || check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            return left.val == right.val &&
                check(left.right, right.left) &&
                check(left.left, right.right);
        }
        return false;
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return true;
        }

        LinkedList<TreeNode> q = new LinkedList();
        q.add(root);
        return isLevelSymmetric(q);
    }

    private boolean isLevelSymmetric(LinkedList<TreeNode> q) {

        if (q.isEmpty()) {
            return true;
        }

        List<Integer> v = new ArrayList();
        LinkedList<TreeNode> qq = new LinkedList();
        while (!q.isEmpty()) {
            TreeNode aux = q.poll();
            if (aux.left != null) {
                qq.add(aux.left);
                v.add(aux.left.val);
            } else {
                v.add(-1);
            }

            if (aux.right != null) {
                qq.add(aux.right);
                v.add(aux.right.val);
            } else {
                v.add(-1);
            }
        }

        List<Integer> qp = new ArrayList(v);
        Collections.reverse(qp);

        //System.out.println(v);
        //System.out.println(qp);

        return qp.equals(v) && isLevelSymmetric(qq);
    }
}
