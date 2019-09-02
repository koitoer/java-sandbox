package com.koitoer.java.mti.chp16;

import java.util.Stack;

import org.junit.Test;

public class GraphOperationTest {

    @Test
    public void testA() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 1, 4);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 3, 9);

        Stack<Graph.AdjacentNode> stack = new GraphOperation().topologicalSort(graph);
        while (!stack.empty()) {
            System.out.print(stack.pop().getV() + " : ");
        }
    }

}
