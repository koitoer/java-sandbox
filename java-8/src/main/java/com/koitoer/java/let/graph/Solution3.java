package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution3 {

    private static final boolean logEnabled = true;

    @Test
    public void test0() {
        int b[][] = new int[][] { { 1, 2 }, { 3, 4 } };
        Assertions.assertThat(new Solution3().gardenNoAdj(4, b)).contains(1, 2, 1, 2);

    }

    @Test
    public void test1() {
        int b[][] = new int[][] { { 4, 1 }, { 4, 2 }, { 4, 3 }, { 2, 5 }, { 1, 2 }, { 1, 5 } };
        Assertions.assertThat(new Solution3().gardenNoAdj(5, b)).containsExactly( 1, 2, 1, 3, 3 );
    }

    @Test
    public void test2() {
        int a[][] = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 1 } };
        Assertions.assertThat(new Solution3().gardenNoAdj(3, a)).containsExactly( 1, 2, 3 );
    }

    public int[] gardenNoAdj(int N, int[][] paths) {

        if (paths.length == 0) {
            return IntStream.range(0, N).map(x -> 1).toArray();
        }

        Graph graph = new Graph();
        graph.init(N, paths);
        Set<Integer> flowerOptions = new HashSet<>();
        flowerOptions.addAll(Arrays.asList(1, 2, 3, 4));

        //Check the entire graph
        for (int i = 1; i < N; i++) {
            Node currentNode = graph.nodeMap.get(i);
            if (currentNode.visited == true) {
                continue;
            } else {
                visitNode(currentNode);
            }
        }

        for (Node c : graph.nodeMap.values()) {
            selectFlower(c);
        }

        log(graph.nodeMap);

        int resolution[] = graph.nodeMap.values().stream().map(x -> x.flowerType).mapToInt(Integer::intValue).toArray();
        return resolution;
    }

    private void selectFlower(Node currentNode) {

        Set<Integer> childFlowers = currentNode.children.stream().map(x -> x.flowerType).collect(Collectors.toSet());
        Set<Integer> parentFloers = currentNode.parentNodes.stream().map(x -> x.flowerType).collect(Collectors.toSet());

        Set<Integer> flowerOptions = new HashSet<>();
        flowerOptions.addAll(Arrays.asList(1, 2, 3, 4));

        childFlowers.addAll(parentFloers);

        childFlowers.stream().forEach(x -> flowerOptions.remove(x));
        currentNode.flowerType = (int) flowerOptions.toArray()[0];

    }

    private void visitNode(Node currentNode) {
        if (currentNode.visited == true) {
            return;
        }

        if (currentNode.children.isEmpty()) {
            return;
        }

        for (Node children : currentNode.children) {
            children.parentNodes.add(currentNode);
        }
    }

    class Graph {

        HashMap<Integer, Node> nodeMap = new HashMap<>();

        public void init(int n, int[][] paths) {

            for (int i = 1; i <= n; i++) {
                nodeMap.putIfAbsent(i, new Node(i, 0));
            }

            for (int[] a : paths) {
                int node = a[0];
                int children = a[1];

                log(node + " -> " + children);
                if (nodeMap.get(node) == null) {
                    nodeMap.put(node, new Node(node));
                }
                if (nodeMap.get(children) == null) {
                    nodeMap.put(children, new Node(children));
                }

                nodeMap.get(node).children.add(nodeMap.get(children));
            }
        }

    }

    class Node {

        public Node(int numberNode) {
            this.numberNode = numberNode;
            this.flowerType = 0;
        }

        public Node(int numberNode, int flowerType) {
            this.numberNode = numberNode;
            this.flowerType = flowerType;
        }

        public int numberNode;

        public int flowerType;

        public boolean visited = false;

        public List<Node> parentNodes = new ArrayList<>();

        public List<Node> children = new ArrayList<>();

        @Override public String toString() {
            return "Node{" +
                "numberNode=" + numberNode +
                ", flowerType=" + flowerType +
                ", visited=" + visited +
                ", parentNodes=" + parentNodes.stream().map(x -> x.numberNode).collect(Collectors.toList()) +
                ", children=" + children.stream().map(x -> x.numberNode).collect(Collectors.toList()) +
                '}';
        }
    }

    protected void log(Object message) {
        if (logEnabled) {
            System.out.println(message);

        }
    }

}
