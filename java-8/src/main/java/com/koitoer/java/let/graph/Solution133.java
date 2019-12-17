package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 133. Clone Graph
 * If the node is null we return null.
 * If the node does not have children return the cloned node with an empty list.
 * If we already visit the node return the node in the Map
 * If we haven't visit the node we will add to the map
 * Iterate over all the neighbors, we recursive clone each one of them and add to the cloned node list/
 */
public class Solution133 {

    public Node cloneGraph(Node node) {
        return clone(node, new HashMap());
    }

    public Node clone(Node node, Map<Integer, Node> visited) {

        //Not really needed
        if (node == null) {
            return null;
        }

        List<Node> newnei = new ArrayList();
        Node clonedNode = new Node(node.val, newnei);

        if (node.neighbors == null || node.neighbors.size() == 0) {
            return clonedNode;
        }

        if (visited.get(node.val) == null) {
            visited.put(node.val, clonedNode);
        } else {
            return visited.get(node.val);
        }

        for (Node vec : node.neighbors) {
            Node neighbor = clone(vec, visited);
            //Not really needed the check for null if we remove line 24
            if (neighbor != null) {
                newnei.add(neighbor);
            }
        }

        return clonedNode;
    }

    class Node {

        public int val;

        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    ;
}
