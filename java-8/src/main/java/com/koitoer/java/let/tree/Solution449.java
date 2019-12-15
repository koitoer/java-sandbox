package com.koitoer.java.let.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 449. Serialize and Deserialize BST
 * This a little more complicated solution but also use the preorder operation to generate the string.
 * Then we just push element according to the rules of a BST.
 */
public class Solution449 {

    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        List<String> list = preorderTraversal(root);
        return String.join(",", list);
    }

    private List<String> preorderTraversal(TreeNode root) {
        List<String> result = new ArrayList();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode currentNode = stack.pop();
            result.add(String.valueOf(currentNode.val));
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }

            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }

        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        String[] values = data.split(",");
        TreeNode root = null;
        for (String v : values) {
            root = insertIntoBST(root, Integer.valueOf(v.trim()));
        }
        return root;
    }

    private TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val <= val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
