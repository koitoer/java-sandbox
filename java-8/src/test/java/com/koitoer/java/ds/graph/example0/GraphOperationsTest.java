package com.koitoer.java.ds.graph.example0;

import org.junit.Test;

public class GraphOperationsTest {

    @Test
    public void testBFS_first_graph_implementation(){

        GraphExample graphExample = new GraphExample();
        graphExample.createGraphUsingList();

        GraphOperations graphOperations = new GraphOperations();
        graphOperations.bfs(graphExample.adjacentList);

    }
}