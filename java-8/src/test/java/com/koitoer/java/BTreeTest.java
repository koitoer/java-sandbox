package com.koitoer.java;

import com.koitoer.java.ds.BTree;
import com.koitoer.java.ds.BTreeNode;
import org.junit.Test;

import javax.annotation.PostConstruct;

/**
 * Created by koitoer on 7/3/16.
 */
public class BTreeTest {

    public BTree bTree;

    @PostConstruct
    public void createTree(){
        bTree = new BTree();
        bTree.rootNode = new BTreeNode(15);
        bTree.rootNode.leftNode = new BTreeNode(10);
        bTree.rootNode.leftNode.leftNode = new BTreeNode(8);
        bTree.rootNode.leftNode.rightNode = new BTreeNode(12);
        bTree.rootNode.rightNode = new BTreeNode(20);
        bTree.rootNode.rightNode.leftNode = new BTreeNode(17);
        bTree.rootNode.rightNode.rightNode =  new BTreeNode(25);
    }


    @Test
    public void printTree(){

        BTree bTree =  new BTree();
        bTree.addNode(15);
        bTree.addNode(10);
        bTree.addNode(20);
        bTree.addNode(8);
        bTree.addNode(12);
        bTree.addNode(17);
        bTree.addNode(25);
        System.out.println("In Order Traverse");
        bTree.inOrderTraverse(bTree.rootNode);
        System.out.println("Post Order Traverse");
        bTree.postOrderTraverse(bTree.rootNode);
        System.out.println("Pre Order Traverse");
        bTree.preOrderTraverse(bTree.rootNode);

        bTree.remove(20);
        System.out.println("After remove Pre Order Traverse");
        bTree.preOrderTraverse(bTree.rootNode);
    }
}
