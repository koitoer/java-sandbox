package com.koitoer.java.ds;

/**
 * Created by koitoer on 7/3/16.
 */
public class BTreeNode {

    public BTreeNode rightNode;
    public BTreeNode leftNode;
    public int data;

    public BTreeNode(int data) {
        this.data = data;
        this.rightNode = null;
        this.leftNode = null;
    }


}
