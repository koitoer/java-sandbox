package com.koitoer.java.let.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 515. Find Largest Value in Each Tree Row
 * Super simple solution based on BFS, at each
 */
public class Solution515a {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        //We start BFS
        while (!queue.isEmpty()) {

            //Initialization per row
            int size = queue.size();
            int max = Integer.MIN_VALUE;

            //Until we exhaust the row
            while (size-- > 0) {
                //Take element;
                TreeNode curr = queue.poll();
                //Check the max
                max = Math.max(max, curr.val);
                //Add to the queue left
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                //Add to the queue right
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            //After each row add the max to the resut;
            res.add(max);
        }
        return res;
    }
}
