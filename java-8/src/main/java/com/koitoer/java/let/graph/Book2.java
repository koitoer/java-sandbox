package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Book2 {

    @Test
    public void verify_there_is_route() {
        int array[][] = new int[][] { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
        Assertions.assertThat(new Book2().eventualSafeNodes(array)).containsExactly(2, 4, 5, 6);
    }

    @Test
    public void verify_there_is_route_2() {
        int array[][] = new int[][] { {}, { 0, 2, 3, 4 }, { 3 }, { 4 }, {} };
        Assertions.assertThat(new Book2().eventualSafeNodes(array)).containsExactly(0, 1, 2, 3, 4);
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        CodeGraph codeGraph = createGraph(graph);

        // Let's visit one by one node.
        for (Node node : codeGraph.nodeList) {
            node.isSafe = visitNode(node);
        }

        //System.out.println(codeGraph);
        // Collect all the nodes that are safe.
        List<Integer> result = codeGraph.nodeList.stream().filter(x -> x.isSafe).map(x -> x.value).collect(Collectors.toList());
        Collections.sort(result);
        return result;
    }

    private boolean visitNode(Node node) {

        if (node.visited == true) {
            return node.isSafe;
        }

        if (node.isTerminal()) {
            node.visited = true;
            return true;
        }

        int[] statusChild = new int[node.children.size()];
        for (int i = 0; i < node.children.size(); i++) {
            node.visited = true;
            Node childNode = node.children.get(i);
            boolean childState = visitNode(childNode);
            childNode.isSafe = childState;
            statusChild[i] = childState == true ? 1 : 0;
        }

        return returnBooleanValue(statusChild);
    }

    private boolean returnBooleanValue(int[] statusChild) {
        for (int i = 0; i < statusChild.length; i++) {
            if (statusChild[i] == 0) {
                return false;
            }
        }
        return true;
    }

    private CodeGraph createGraph(int[][] graph) {
        CodeGraph codeGraph = new CodeGraph();
        for (int i = 0; i < graph.length; i++) {
            codeGraph.nodeList.add(new Node(i));
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                // Let's add the child)
                Node parent = codeGraph.nodeList.get(i);
                Node child = codeGraph.nodeList.get(graph[i][j]);
                parent.children.add(child);
                //child.parents.add(parent);
            }
        }
        return codeGraph;

    }

    //Classes fro graph
    public class CodeGraph {

        private List<Node> nodeList = new ArrayList<>();

        @Override public String toString() {
            return "CodeGraph{" +
                "nodeList=" + nodeList.stream().collect(Collectors.toSet()) +
                '}';
        }
    }

    class Node {

        public Node(Integer value) {
            this.value = value;
        }

        private Integer value;

        private boolean visited = false;

        private boolean isSafe = false;

        private boolean isTerminal() {
            return children.isEmpty();
        }

        //private List<Node> parents = new ArrayList<>();

        private List<Node> children = new ArrayList<>();

        @Override
        public String toString() {
            return "Node{" +
                "value=" + value +
                ", safe =" + isSafe +
                // ", parent=" + parents.stream().map(x -> x.value).collect(Collectors.toList()) +
                ", children=" + children.stream().map(x -> x.value).collect(Collectors.toList()) +
                ", terminal=" + isTerminal() +
                '}';
        }
    }

}
