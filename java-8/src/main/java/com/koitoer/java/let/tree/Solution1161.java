package com.koitoer.java.let.tree;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * 1161. Maximum Level Sum of a Binary Tree
 * The trick is create a q per level, this will be used to iterate and calculate the current sum.
 * We will use a class to store the maximum, the currentLevel and the maximumLevel
 */
public class Solution1161 {

    @Test
    public void test1(){
        //[1,7,0,7,-8,null,null] === 2
    }

    public int maxLevelSum(TreeNode root) {
        Queue q = new LinkedList();
        Result r = new Result();

        q.add(root);
        visit(q, r);

        return r.maxLevel;
    }

    public void visit(Queue<TreeNode> q, Result r) {

        if (q.isEmpty()) {
            return;
        }

        int count = 0;
        Queue q1 = new LinkedList();
        while (!q.isEmpty()) {
            //Get the current node.
            TreeNode aux = q.poll();
            count += aux.val;

            if (aux.right != null) {
                q1.add(aux.right);
            }
            if (aux.left != null) {
                q1.add(aux.left);
            }
        }

        if (count > r.max) {
            r.max = count;
            r.maxLevel = r.level;
        }

        r.level++;

        visit(q1, r);

    }

    static class Result {

        public int max = 0;

        public int level = 1;

        public int maxLevel = 1;
    }

}
