package com.koitoer.training.dfs2;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.koitoer.training.dfs2.model.Node;

/**
 * Created by mmena on 2/26/18.
 */
public class DFSAlgo {

    // Iterative DFS using stack
    public void dfsUsingStack(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        node.visited = true;
        while (!stack.isEmpty()) {
            Node element = stack.pop();
            System.out.print(element.data + " ");

            List<Node> neighbours = element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.add(n);
                    n.visited = true;

                }
            }
        }
    }

    @Test
    public void test() {

        Node node40 = new Node(40);
        Node node20 = new Node(20);
        Node node50 = new Node(50);
        Node node70 = new Node(70);
        Node node10 = new Node(10);
        Node node30 = new Node(30);
        Node node60 = new Node(60);

        node40.setNeighbours(Arrays.asList(node10, node20));
        node20.setNeighbours(Arrays.asList(node50, node10, node30, node60));
        node50.setNeighbours(Arrays.asList(node70));
        node10.setNeighbours(Arrays.asList(node30));
        node30.setNeighbours(Arrays.asList(node60));
        node60.setNeighbours(Arrays.asList(node70));

        DFSAlgo dfsAlgo = new DFSAlgo();
        dfsAlgo.dfsUsingStack(node40);
    }

}
