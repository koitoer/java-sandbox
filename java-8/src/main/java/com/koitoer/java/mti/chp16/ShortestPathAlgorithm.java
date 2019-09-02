package com.koitoer.java.mti.chp16;

public abstract class ShortestPathAlgorithm {

    public static final int INF = Integer.MAX_VALUE;

    public int[] initializeDistanceArrayToZero(Graph graph, int originVertex) {
        int dist[] = new int[graph.getNumberOfVertex()];
        for (int i = 0; i < graph.getNumberOfVertex(); i++)
            dist[i] = INF;
        dist[originVertex] = 0;
        return dist;
    }

    public abstract int[] shortestPath(Graph graph, int originVertex);
}
