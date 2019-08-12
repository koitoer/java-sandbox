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

public class Solution2 {

    @Test
    public void test0() {
        int b[][] = new int[][] { { 1, 2 }, { 3, 4 } };
        Assertions.assertThat(new Solution2().gardenNoAdj(4, b)).isEqualTo(new int[][] { { 1, 2, 1, 2 } });

    }

    @Test
    public void test1() {
        int b[][] = new int[][] { { 4, 1 }, { 4, 2 }, { 4, 3 }, { 2, 5 }, { 1, 2 }, { 1, 5 } };
        Assertions.assertThat(new Solution2().gardenNoAdj(5, b)).isEqualTo(new int[][] { { 1, 2, 1, 3, 3 } });
    }

    @Test
    public void test2() {
        int a[][] = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 1 } };
        Assertions.assertThat(new Solution2().gardenNoAdj(3, a)).isEqualTo(new int[][] { { 1, 2, 3 } });
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
                visitNode(currentNode, flowerOptions);
                currentNode.flowerType = assignMyself(currentNode, flowerOptions);
            }
        }

        System.out.println(graph.nodeMap);

        int resolution[] = graph.nodeMap.values().stream().map(x -> x.flowerType).mapToInt(Integer::intValue).toArray();
        return resolution;
    }

    private int assignMyself(Node currentNode, Set<Integer> flowerOptions) {
        if (currentNode.children.isEmpty()) {
            return 1;
        }
        List<Integer> kidsFlowers = currentNode.children.stream().map(x -> x.flowerType).collect(Collectors.toList());
        return myFlower(kidsFlowers, flowerOptions);
    }

    private int myFlower(List<Integer> kidsFlowers, Set<Integer> flowerOptions) {
        HashSet newFlowers = new HashSet(flowerOptions);
        kidsFlowers.stream().forEach(x -> newFlowers.remove(x));
        return (int) newFlowers.toArray()[0];
    }

    private void visitNode(Node node, Set<Integer> remainingFlowers) {

        if (node.visited == true) {
            remainingFlowers.remove(node.flowerType);
        }

        if (node.children.isEmpty()) {
            node.visited = true;
            node.flowerType = getAFlower(remainingFlowers);
        }

        for (Node child : node.children) {
            child.visited = true;
            //visitNode(child.);

        }

    }

    private int getAFlower(Set<Integer> remainingFlowers) {
        return (int) remainingFlowers.toArray()[0];
    }

    private Set<Integer> getRemainingFlowers(int flowerType, Set<Integer> flowerOptions) {
        HashSet<Integer> copy = new HashSet<>(flowerOptions);
        copy.remove(flowerType);
        return copy;
    }

    class Graph {

        HashMap<Integer, Node> nodeMap = new HashMap<>();

        public void init(int n, int[][] paths) {

            for (int i = 1; i < n; i++) {
                nodeMap.putIfAbsent(i, new Node(i, 1));
            }

            for (int[] a : paths) {
                int node = a[0];
                int children = a[1];

                System.out.println(node + " -> " + children);
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
        }

        public Node(int numberNode, int flowerType) {
            this.numberNode = numberNode;
            this.flowerType = flowerType;
        }

        public int numberNode;

        public int flowerType;

        public boolean visited = false;

        public List<Node> children = new ArrayList<>();

        @Override public String toString() {
            return "Node{" +
                "numberNode=" + numberNode +
                ", flowerType=" + flowerType +
                ", children=" + children.stream().map(x -> x.numberNode).collect(Collectors.toList()) +
                '}';
        }
    }

}
