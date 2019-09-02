package com.koitoer.java.mti.chp16;

import java.util.LinkedList;
import java.util.Stack;

public class ShortestPathSimple extends ShortestPathAlgorithm {

    /**
     * Find the shortest path to all the vertex from originVertex
     */
    public int[] shortestPath(Graph graph, int originVertex) {

        //Create an initialize the distance for all the vertex to infinite except the origin to itself to zero.
        int dist[] = initializeDistanceArrayToZero(graph, originVertex);

        //Do a topological sort.
        Stack<Graph.AdjacentNode> stack = new GraphOperation().topologicalSort(graph);

        //Once is ordering start iterating over the stack
        while (!stack.empty()) {
            Graph.AdjacentNode currentNode = stack.pop();
            int currentNodeIndex = currentNode.getV();

            //Update distance for all the child vertex within the current Vertex
            if (dist[currentNodeIndex] != INF) {
                LinkedList<Graph.AdjacentNode> adjacentNodes = graph.getAdjacentArray()[currentNodeIndex];

                //Iterate over the child vertex
                for (Graph.AdjacentNode adjacentNode : adjacentNodes) {

                    //Current distance to the node
                    int currentAdjacentNodeDistance = dist[adjacentNode.getV()];

                    //Using the current Node calculate the distance.
                    int possibleNewDistance = dist[currentNodeIndex] + adjacentNode.getWeight();

                    //Change if applies
                    if (currentAdjacentNodeDistance > possibleNewDistance) {
                        dist[adjacentNode.getV()] = possibleNewDistance;
                    }
                }

            }
        }

        return dist;
    }

}
