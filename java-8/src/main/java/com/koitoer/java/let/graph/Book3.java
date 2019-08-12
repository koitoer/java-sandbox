package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Book3 {

    @Test
    public void verify_there_is_route() {
        int array[][] = new int[][] { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
        Assertions.assertThat(new Book3().eventualSafeNodes(array)).containsExactly(2, 4, 5, 6);
    }

    @Test
    public void verify_there_is_route_2() {
        int array[][] = new int[][] { {}, { 0, 2, 3, 4 }, { 3 }, { 4 }, {} };
        Assertions.assertThat(new Book3().eventualSafeNodes(array)).containsExactly(0, 1, 2, 3, 4);
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {

        // Let's visit one by one node.
        int[] finalState = new int[graph.length];
        int[] visitedState = new int[graph.length];

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            finalState[i] = visitNode1(i, graph, finalState, visitedState);
            if (finalState[i] == 1) {
                result.add(i);
            }
        }

        return result;

    }

    private int visitNode1(int idx, int[][] graph, int[] finalState, int[] visitedState) {

        if (visitedState[idx] == 1) {
            return finalState[idx];
        }

        visitedState[idx] = 1;

        for (int j = 0; j < graph[idx].length; j++) {
            int childrenIdx = graph[idx][j];
            int childState = visitNode1(childrenIdx, graph, finalState, visitedState);
            finalState[childrenIdx] = childState;
            if (childState == 0) {
                return 0;
            }
        }
        return 1;
    }

}
