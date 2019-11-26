package com.koitoer.java.let.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 515. Find Largest Value in Each Tree Row
 */
public class Solution515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> q = new LinkedList();
        q.add(root);
        bfs(q, result);
        return result;
    }

    private void bfs(LinkedList<TreeNode> q, List<Integer> result) {
        if (!q.isEmpty()) {
            result.add(getMaxFromQueue(q));
        } else {
            return;
        }

        LinkedList<TreeNode> a = new LinkedList();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            //System.out.println(node.val);
            if (node != null) {
                if (node.left != null) {
                    a.add(node.left);
                }
                if (node.right != null) {
                    a.add(node.right);
                }
            }
        }

        bfs(a, result);
    }

    private int getMaxFromQueue(LinkedList<TreeNode> q) {
        int max = Integer.MIN_VALUE;
        for (TreeNode node : q) {
            max = Math.max(node.val, max);
        }
        return max;
    }
}
