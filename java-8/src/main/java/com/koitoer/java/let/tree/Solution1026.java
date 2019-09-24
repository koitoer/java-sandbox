package com.koitoer.java.let.tree;

import java.util.Stack;

/**
 * 1026. Maximum Difference Between Node and Ancestor
 * Store all the parent in a stack and check the calculation for each one
 * Optimize later.
 *
 * I assume the optimization will be instead of save the parent information save the current max.
 * That will track the previous calculations.
 */
public class Solution1026 {

    public int maxAncestorDiff(TreeNode root) {
        Structure s = new Structure();
        visit(root, s);
        return s.max;
    }

    public void visit(TreeNode root, Structure s) {
        if (root == null) {
            return;
        }

        s.parent.push(root.val);

        visit(root.right, s);
        visit(root.left, s);

        int internalMax = Integer.MIN_VALUE;
        for (Integer i : s.parent) {
            internalMax = Math.max(Math.abs(i - root.val), internalMax);
        }

        s.updateMaxMax(internalMax);

        s.parent.pop();
    }

    class Structure {

        public Stack<Integer> parent = new Stack();

        public int max;

        public void updateMaxMax(int value) {
            max = Math.max(value, max);
        }

    }
}
