package com.koitoer.java.mti.chp16;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Dijkstra (G, W, s)
 * initialize
 * [G, s]
 * ---- S = NIL <-- settled is empty (a)
 * ---- Q <- V[G] -- all vertex in graph
 * ---- d[s] = 0; <-- initialize distance vector (b)
 *
 * algorithm:
 * ---- while Q != NIL
 * ---- ----  U <- extract_min(Q)
 * ---- ----  S <- S union {u}
 * ---- ----  for each vertex v E Adj[v]
 * ---- ---- ---- relax (u,v,w)
 */
public class Dijkstra extends ShortestPathAlgorithm {

    private PriorityQueue<Graph.AdjacentNode> pq;

    /**
     * (a) Settled is empty
     */
    private Set<Integer> settled = new HashSet<>();

    public Dijkstra(int numberOfVertex) {
        pq = new PriorityQueue<>(numberOfVertex);
    }

    @Override
    public int[] shortestPath(Graph graph, int originVertex) {

        //Create an initialize the distance for all the vertex to infinite except the origin to itself to zero. (b)
        int dist[] = initializeDistanceArrayToZero(graph, originVertex);

        //Add to priority queue the originVertex and mark with zero weight
        pq.add(new Graph.AdjacentNode(originVertex, 0));

        //Do this for all the vertex in the graph
        while (settled.size() != graph.getNumberOfVertex()) {

            //extract_min(Q)
            Graph.AdjacentNode currentNode = pq.remove();
            settled.add(currentNode.getV());

            processNeighbours(graph, currentNode.getV(), dist, settled, pq);

        }

        return dist;
    }

    private void processNeighbours(Graph graph, int indexNode, int[] dist, Set<Integer> settled, PriorityQueue<Graph.AdjacentNode> pq) {
        int edgeDistance;
        int newDistance;

        for (Graph.AdjacentNode neighbour : graph.getAdjacentArray()[indexNode]) {

            if (!settled.contains(neighbour.getV())) {
                edgeDistance = neighbour.getWeight();
                newDistance = dist[indexNode] + edgeDistance;

                if (newDistance < dist[neighbour.getV()]) {
                    dist[neighbour.getV()] = newDistance;
                }

                pq.add(new Graph.AdjacentNode(neighbour.getV(), dist[neighbour.getV()]));
            }

        }
    }
}
