package com.koitoer.training.dfsTree;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mmena on 2/26/18.
 */
public class TreeDeep {

    private Node root = new Node("1");

    @Before
    public void init() {
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");

        root.setLeft(n2);
        n2.setLeft(n4);
        n2.setRight(n5);

        root.setRight(n3);
        n3.setLeft(n6);
        n3.setRight(n7);

    }

    public void dfsTree(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if(current.right != null  ){
                stack.push(current.right);
            }
            if (current.left != null ) {
                stack.push(current.left);
            }

            System.out.println("Visited node " + current);
        }
    }

    @Test
    public void testTree() {
        TreeDeep treeDeep = new TreeDeep();
        treeDeep.dfsTree(root);
    }

}
