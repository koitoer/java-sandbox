package com.koitoer.java.mti.chp16;

import java.util.LinkedList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Graph {

    private int numberOfVertex;

    private LinkedList<AdjacentNode>[] adjacentArray;

    public Graph(int v) {
        this.numberOfVertex = v;
        this.adjacentArray = new LinkedList[numberOfVertex];
        for (int i = 0; i < v; i++) {
            this.adjacentArray[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v, int weight) {
        LinkedList<AdjacentNode> nodeU = this.adjacentArray[u];
        nodeU.add(new AdjacentNode(v, weight));
    }

    @Getter
    @RequiredArgsConstructor
    public static class AdjacentNode implements Comparable<AdjacentNode> {

        private final int v;

        private final int weight;

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            AdjacentNode that = (AdjacentNode) o;
            return v == that.v;
        }

        @Override
        public int compareTo(AdjacentNode o) {
            if (this.getWeight() < o.getWeight())
                return -1;
            if (this.getWeight() > o.getWeight())
                return 1;
            return 0;
        }
    }
}
