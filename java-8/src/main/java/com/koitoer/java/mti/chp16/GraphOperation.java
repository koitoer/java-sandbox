package com.koitoer.java.mti.chp16;

import java.util.LinkedList;
import java.util.Stack;

public class GraphOperation {

    /**
     * Initialize the components for topological sort.
     */
    public Stack<Graph.AdjacentNode> topologicalSort(Graph graph) {
        Stack<Graph.AdjacentNode> stack = new Stack<>();
        boolean visited[] = new boolean[graph.getNumberOfVertex()];

        //Call the recursive function for each vertex in the graph one by one.
        for (int i = 0; i < graph.getNumberOfVertex(); i++) {
            //Only run the process if this haven't been visited.
            if (visited[i] == false) {
                visit(i, visited, stack, graph);
            }
        }

        return stack;

    }

    private void visit(int nodeIndex, boolean[] visited, Stack<Graph.AdjacentNode> stack, Graph graph) {
        //Mark the node as visited
        visited[nodeIndex] = true;

        //Recur for all vertex that are adjacent to this one.
        LinkedList<Graph.AdjacentNode> adjacentNodes = graph.getAdjacentArray()[nodeIndex];
        for(Graph.AdjacentNode adjacentNode : adjacentNodes){

            //Just visit if the child haven't been visited.
            if(visited[adjacentNode.getV()] == false) {
                visit(adjacentNode.getV(), visited, stack, graph);
            }
        }

        //There are no more children to visit, this needs to be a final vertex, we add to the stack.
        stack.push(new Graph.AdjacentNode(nodeIndex, 0));
    }
}
