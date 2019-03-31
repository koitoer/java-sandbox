package com.koitoer.training.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.koitoer.training.bfs.model.Node;

import scala.Int;

/**
 * Created by mmena on 3/10/18.
 */
public class Run {

    Node root;

    @Before
    public void inti() {
        root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        Node node7 = new Node(7);
        Node node8 = new Node(8);

        root.setLeft(node1);
        root.setRigth(node2);

        node1.setLeft(node3);
        node1.setRigth(node4);

        node2.setLeft(node5);
        node2.setRigth(node6);

        node6.setLeft(node8);
        node6.setRigth(node7);
    }

    @Test
    public void bfs() {

        Queue<Node> queue = new LinkedList<>();
        List<Integer> result =  new ArrayList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            Node current = queue.poll();
            result.add(current.getValue());


            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRigth() != null) {
                queue.add(current.getRigth());
            }
        }

        Assertions.assertThat(result.toArray(new Integer[result.size()])).isEqualTo(new Integer[]{0, 1, 2, 3, 4, 5, 6, 8, 7});
    }


    @Test
    public void dfs(){
        List<Integer> result =  new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){

            Node current = stack.pop();

            if(current.getRigth() != null  ){
                stack.push(current.getRigth());
            }
            if (current.getLeft() != null ) {
                stack.push(current.getLeft());
            }

            result.add(current.getValue());

        }
        System.out.println(result);

        Assertions.assertThat(result.toArray(new Integer[result.size()])).isEqualTo(new Integer[]{0, 1, 3, 4, 2, 5, 6, 8, 7});

    }
}
