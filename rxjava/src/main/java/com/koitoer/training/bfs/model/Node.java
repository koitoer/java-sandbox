package com.koitoer.training.bfs.model;

/**
 * Created by mmena on 3/10/18.
 */
public class Node {

    public Node(int value, Node left, Node rigth) {
        this.value = value;
        this.left = left;
        this.rigth = rigth;
    }

    private int value;

    private Node left;

    private Node rigth;

    public Node(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRigth() {
        return rigth;
    }

    public void setRigth(Node rigth) {
        this.rigth = rigth;
    }
}
