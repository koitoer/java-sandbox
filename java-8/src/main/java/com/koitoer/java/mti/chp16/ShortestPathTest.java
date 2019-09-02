package com.koitoer.java.mti.chp16;

import org.junit.Test;

public class ShortestPathTest {

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

        int dist[] = new ShortestPathSimple().shortestPath(graph, 0);

        // Print the calculated shortest distances
        for (int i = 0; i < graph.getNumberOfVertex(); i++) {
            System.out.print(dist[i] + " ");
        }
    }

}
