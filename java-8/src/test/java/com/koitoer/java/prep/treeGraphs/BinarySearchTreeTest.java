package com.koitoer.java.prep.treeGraphs;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

/**
 * BinarySearchTreeTest will test the different operations in the binary tree.
 */
public class BinarySearchTreeTest {

    private BinarySearchTree binarySearchTree;

    /**
     *                          52
     *               33                    65
     *         25        39         60          78
     *      12   27   34   48     X     X     72    90
     *                                      70  X
     */
    @Before
    public void createBinaryTree(){
        BinaryTreeNode rootNode = new BinaryTreeNode(52);
        BinaryTreeNode node1 = new BinaryTreeNode(33);
        BinaryTreeNode node2 = new BinaryTreeNode(25);
        BinaryTreeNode node3 = new BinaryTreeNode(39);
        BinaryTreeNode node4 = new BinaryTreeNode(12);
        BinaryTreeNode node5 = new BinaryTreeNode(27);
        BinaryTreeNode node6 = new BinaryTreeNode(34);
        BinaryTreeNode node7 = new BinaryTreeNode(48);
        BinaryTreeNode node8 = new BinaryTreeNode(65);
        BinaryTreeNode node9 = new BinaryTreeNode(60);

        BinaryTreeNode node10 = new BinaryTreeNode(78);
        BinaryTreeNode node11 = new BinaryTreeNode(72);
        BinaryTreeNode node12 = new BinaryTreeNode(90);
        BinaryTreeNode node13 = new BinaryTreeNode(70);

        binarySearchTree = new BinarySearchTree(rootNode);
        rootNode.setLeft(node1);
        node1.setLeft(node2);
        node2.setLeft(node4);
        node2.setRight(node5);
        node1.setRight(node3);
        node3.setLeft(node6);
        node3.setRight(node7);

        rootNode.setRight(node8);
        node8.setLeft(node9);
        node8.setRight(node10);
        node10.setLeft(node11);
        node10.setRight(node12);
        node11.setLeft(node13);
    }

    /**
     * Time Complexity
     * O(log2 n)
     */
    @Test
    public void test_find_method(){
        Integer value = 78;
        BinaryTreeNode node = binarySearchTree.find(value);
        Assertions.assertThat(node).isNotNull();
        Assertions.assertThat(node.getLeft().getValue()).isEqualTo(72);
        Assertions.assertThat(node.getRight().getValue()).isEqualTo(90);

        node = binarySearchTree.find(100);
        Assertions.assertThat(node).isNull();

        node = binarySearchTree.find(70);
        Assertions.assertThat(node).isNotNull();
        Assertions.assertThat(node.getLeft()).isNull();
        Assertions.assertThat(node.getRight()).isNull();

        node = binarySearchTree.find(60);
        Assertions.assertThat(node).isNotNull();
        Assertions.assertThat(node.getLeft()).isNull();
        Assertions.assertThat(node.getRight()).isNull();
    }


    @Test
    public void test_insert_method(){
        BinaryTreeNode node = new BinaryTreeNode(12);
        binarySearchTree.insert(node);

        node = binarySearchTree.find(63);
        Assertions.assertThat(node).isNotNull();
        Assertions.assertThat(node.getLeft()).isNull();
        Assertions.assertThat(node.getRight()).isNull();

        node = binarySearchTree.find(60);
        Assertions.assertThat(node).isNotNull();
        Assertions.assertThat(node.getRight().getValue()).isEqualTo(63);
    }

    /**
     * O(log2 n) + C = O(log2 n)
     */
    @Test
    public void test_delete_method_case_no_children(){
        BinaryTreeNode deletedNode = binarySearchTree.find(12);
        Assertions.assertThat(deletedNode).isNotNull();
        Assertions.assertThat(deletedNode.getRight()).isNull();
        Assertions.assertThat(deletedNode.getLeft()).isNull();

        binarySearchTree.delete(12);
        BinaryTreeNode node = binarySearchTree.find(25);
        Assertions.assertThat(node).isNotNull();
        Assertions.assertThat(node.getLeft()).isNull();
        Assertions.assertThat(node.getRight().getValue()).isEqualTo(27);

        deletedNode = binarySearchTree.find(12);
        Assertions.assertThat(deletedNode).isNull();
    }

    @Test
    public void test_delete_method_case_one_child(){
        BinaryTreeNode deletedNode = binarySearchTree.find(72);
        Assertions.assertThat(deletedNode).isNotNull();
        Assertions.assertThat(deletedNode.getLeft().getValue()).isEqualTo(70);
        Assertions.assertThat(deletedNode.getRight()).isNull();

        binarySearchTree.delete(72);
        BinaryTreeNode node = binarySearchTree.find(70);
        Assertions.assertThat(node).isNotNull();
        Assertions.assertThat(node.getLeft()).isNull();
        Assertions.assertThat(node.getRight()).isNull();


        deletedNode = binarySearchTree.find(72);
        Assertions.assertThat(deletedNode).isNull();

        BinaryTreeNode parentNode = binarySearchTree.find(78);
        Assertions.assertThat(parentNode.getLeft().getValue()).isEqualTo(70);
        Assertions.assertThat(parentNode.getRight().getValue()).isEqualTo(90);
    }

    @Test
    public void getMinimumValue(){
        Assertions.assertThat(binarySearchTree.findMin()).isEqualTo(12);
    }

    @Test
    public void getMaximumValue(){
        Assertions.assertThat(binarySearchTree.findMax()).isEqualTo(90);
    }

    @Test
    public void inOrder(){
        BinarySearchTree.inOrder(binarySearchTree.getRootNode());
    }
}