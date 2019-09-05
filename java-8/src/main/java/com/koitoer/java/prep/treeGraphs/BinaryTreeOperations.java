package com.koitoer.java.prep.treeGraphs;

/**
 * BinaryTreeOperations traversal operations
 */
public final class BinaryTreeOperations {

    /**
     * Ascending ORDER
     * Visit left node first until the last level of the tree
     * Then visit the node.
     * Finally visit all the right nodes.
     * left -> inner -> right
     */
    public static void inOrderTraversal(BinaryNode binaryNode){
        if(binaryNode != null){
            inOrderTraversal(binaryNode.leftNode);
            visit(binaryNode);
            inOrderTraversal(binaryNode.rightNode);
        }
    }

    /**
     * inner -> left -> right
     */
    public static void preOrderTraversal(BinaryNode binaryNode){
        if(binaryNode != null){
            visit(binaryNode);
            preOrderTraversal(binaryNode.leftNode);
            preOrderTraversal(binaryNode.rightNode);
        }
    }


    /**
     * Root node is always the last visited
     * left -> right -> inner
     */
    public static void postOrderTraversal(BinaryNode binaryNode){
        if(binaryNode != null){
            postOrderTraversal(binaryNode.leftNode);
            postOrderTraversal(binaryNode.rightNode);
            visit(binaryNode);
        }
    }

    private static void visit(BinaryNode binaryNode) {
        System.out.print(binaryNode.getValue() + " -> ");
    }
}
