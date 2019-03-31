package com.koitoer.training.dfsTree;

/**
 * Created by mmena on 2/26/18.
 */
public class Node {

    public Node left;

    public Node right;

    public Boolean visited;

    public String value;

    public Node(String value){
        this.value = value;
        this.visited = false;
        this.left = null;
        this.right =  null;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override public String toString() {
        return "Node{" +
            "value='" + value + '\'' +
            '}';
    }
}
