package com.koitoer.java.prep.treeGraphs;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class that proves the correct implementation of the traversal
 *
 * a
 * b    c
 * d    e   f   g
 * h    X   X   X   X   X   i
 */
public class BinaryTreeOperationsTest {

    private BinaryNode rootNode;

    @Before
    public void createBinaryTree(){
        rootNode = new BinaryNode<>("a");
        BinaryNode<String> nodeB = new BinaryNode<>("b");
        BinaryNode<String> nodeC = new BinaryNode<>("c");
        BinaryNode<String> nodeD = new BinaryNode<>("d");
        BinaryNode<String> nodeE = new BinaryNode<>("e");
        BinaryNode<String> nodeF = new BinaryNode<>("f");
        BinaryNode<String> nodeG = new BinaryNode<>("g");
        BinaryNode<String> nodeH = new BinaryNode<>("h");
        BinaryNode<String> nodeI = new BinaryNode<>("i");

        nodeD.setLeftNode(nodeH);
        nodeB.setLeftNode(nodeD);
        nodeB.setRightNode(nodeE);
        rootNode.setLeftNode(nodeB);

        nodeC.setLeftNode(nodeF);
        nodeC.setRightNode(nodeG);
        nodeG.setRightNode(nodeI);
        rootNode.setRightNode(nodeC);
    }


    /**
     * h -> d -> b -> e -> a -> f -> c -> g -> i
     */
    @Test
    public void testInOrderTraversal(){
        BinaryTreeOperations.inOrderTraversal(rootNode);
    }

    /**
     * a -> b -> d -> h -> e -> c -> f -> g -> i
     */
    @Test
    public void testPreOrderTraversal(){
        BinaryTreeOperations.preOrderTraversal(rootNode);
    }

    /**
     * h -> d -> e -> b -> f -> i -> g -> c -> a
     */
    @Test
    public void testPostOrderTraversal(){
        BinaryTreeOperations.postOrderTraversal(rootNode);
    }
}