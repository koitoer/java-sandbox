package com.koitoer.java.prep.treeGraphs;

/**
 * BinarySearchTree is an integer implementation of a binary tree.
 */
public class BinarySearchTree {

    private BinaryTreeNode rootNode;

    public BinarySearchTree(BinaryTreeNode rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * Will find a value in the binary tree
     */
    public BinaryTreeNode find(Integer value) {
        if (rootNode != null) {
            return rootNode.find(value);
        }
        return null;
    }

    /**
     * Insert to the binary tree
     */
    public void insert(BinaryTreeNode binaryTreeNode) {
        if (rootNode != null) {
            rootNode.insert(binaryTreeNode);
            return;
        }
        rootNode = binaryTreeNode;
    }

    public void delete(Integer value) {
        if (rootNode == null) {
            return;
        }

        BinaryTreeNode parent = rootNode;
        BinaryTreeNode current = rootNode;
        boolean isLeftNode = false;

        while (current != null && current.getValue() != value) {
            parent = current;

            if (current.getValue() > value) {
                current = current.getLeft();
                isLeftNode = true;
            } else {
                current = current.getRight();
                isLeftNode = false;
            }
        }

        //We don't find it
        if (current == null) {
            return;
        }

        //CASE 1: The node is a leaf.
        if (current.getLeft() == null && current.getRight() == null) {
            if (isLeftNode) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }

        //CASE 2L The node has one child and is the left
        else if (current.getRight() == null) {
            if (current == rootNode) {
                rootNode = current.getLeft();
            } else if (isLeftNode) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        }

        //CASE 2L The node has one child and is the right
        else if (current.getLeft() == null) {
            if (current == rootNode) {
                rootNode = current.getRight();
            } else if (isLeftNode) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        }
    }

    public Integer findMin() {
        if (rootNode == null) {
            return null;
        }
        BinaryTreeNode current = rootNode;
        while (current != null && current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getValue();
    }

    public Integer findMax() {
        if (rootNode == null) {
            return null;
        }
        return rootNode.findMax();
    }

    public static void inOrder(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode != null) {
            inOrder(binaryTreeNode.getLeft());
            System.out.print(binaryTreeNode.getValue() + " -> ");
            inOrder(binaryTreeNode.getRight());
        }
    }

    public BinaryTreeNode getRootNode() {
        return rootNode;
    }
}
