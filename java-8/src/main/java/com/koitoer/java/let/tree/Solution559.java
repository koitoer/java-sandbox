package com.koitoer.java.let.tree;

import java.util.List;

import org.junit.Test;

/**
 * 559. Maximum Depth of N-ary Tree
 * Given a n-ary tree, find its maximum depth.
 * The trick is a recursive function over each one of the child nodes
 * and return the max depth per level.
 */
public class Solution559 {

    @Test
    public void test1() {

    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        //As the first node already have a depth or 1, we need to set the max as one.
        int max = 1;
        for (Node node : root.children) {
            max = Math.max(maxDepth(node) + 1, max);
        }
        return max;
    }

    class Node {

        public int val;

        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
