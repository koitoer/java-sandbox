package com.koitoer.java.let.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import lombok.Setter;

/**
 * Iterate over a tree in like if we were a snake.
 */
public class HaInter1 {

    // Function to reverse the queue
    static Queue reversequeue(Queue<TreeNode> q) {
        Stack<TreeNode> stack = new Stack<>();
        while (!q.isEmpty()) {
            stack.add(q.peek());
            q.remove();
        }
        while (!stack.isEmpty()) {
            q.add(stack.peek());
            stack.pop();
        }

        return q;
    }

    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);

        TreeNode root = t3;
        t3.left = t5;
        t3.right = t1;
        t5.left = t6;
        t5.right = t2;
        t2.left = t7;
        t2.right = t4;
        t1.left = t9;
        t1.right = t8;

        Assertions.assertThat(new HaInter1().snake(root)).containsExactly(3, 5, 1, 8, 9, 2, 6, 7, 4);
    }

    private List<Integer> snake(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        visitLevel(q, 1, r);

        return r;
    }

    private void visitLevel(Queue<TreeNode> q, int i, List<Integer> r) {

        if (q.isEmpty()) {
            return;
        }

        Queue<TreeNode> aux = new LinkedList<>();

        while (!q.isEmpty()) {
            TreeNode a = q.poll();
            if (a != null) {
                r.add(a.val);
                if (i % 2 == 0) {
                    aux.add(a.left);
                    aux.add(a.right);
                } else {
                    aux.add(a.right);
                    aux.add(a.left);
                }
            }
        }

        visitLevel(reversequeue(aux), i + 1, r);
    }

    class TreeNode {

        int val;

        @Setter
        int level;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override public String toString() {
            return "TreeNode{" +
                "val=" + val +
                ", level=" + level +
                '}';
        }
    }
}