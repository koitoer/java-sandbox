package com.koitoer.java.let.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 872. Leaf-Similar Trees
 * We iterate over the tree when we detect a leaf we will add the value to the list then we will compare the two lists.
 */
public class Solution872 {

    @Test
    public void test1() {
        //[3,5,1,6,2,9,8,null,null,7,4]
        //[3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
        //Result will be true
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> r1 = new ArrayList();
        leafSequence(root1, r1);

        List<Integer> r2 = new ArrayList();
        leafSequence(root2, r2);

        if (r1.size() != r2.size()) {
            return false;
        }

        for (int i = 0; i < r1.size(); i++) {
            if (r1.get(i) != r2.get(i)) {
                return false;
            }
        }

        return true;
    }

    private void leafSequence(TreeNode r, List<Integer> values) {
        if (r == null) {
            return;
        }

        if (r.left == null && r.right == null) {
            values.add(r.val);
            return;
        }

        leafSequence(r.left, values);
        leafSequence(r.right, values);
    }
}

