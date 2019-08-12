package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Book1 {

    @Test
    public void verify_there_is_route() {
        int array[][] = new int[][] { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
        Assertions.assertThat(new Book1().eventualSafeNodes(array)).containsExactly(2, 4, 5, 6);
    }

    @Test
    public void verify_there_is_route_2() {
        int array[][] = new int[][] { {}, { 0,2,3,4 }, { 3 }, { 4 }, {  }};
        Assertions.assertThat(new Book1().eventualSafeNodes(array)).containsExactly(0,1,2,3,4);
    }


    public List<Integer> eventualSafeNodes(int[][] graph) {
        CodeGraph codeGraph = createGraph(graph);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < codeGraph.nodeList.size(); i++) {
            Node currentNode = codeGraph.nodeList.get(i);
            if (currentNode.isTerminal()) {
                result.add((int) currentNode.value);
            }
        }

        for(int i= 0; i< result.size() ; i++){
            Node<Integer> terminalNode = codeGraph.nodeList.get(result.get(i));
            if(terminalNode.parents.isEmpty()){
                continue;
            }

            for(Node parent : terminalNode.parents){
                if(parent.children.size() == 1){
                    result.add((int)parent.value);
                }
            }


        }

        Collections.sort(result);
        return result;
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
                child.parents.add(parent);
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

    class Node<T> {

        public Node(T value) {
            this.value = value;
        }

        private T value;

        private boolean visited = false;

        private boolean isTerminal() {
            return children.isEmpty();
        }

        ;

        private List<Node<T>> parents = new ArrayList<>();

        private List<Node<T>> children = new ArrayList<>();

        @Override
        public String toString() {
            return "Node{" +
                "value=" + value +
                ", parent=" + parents.stream().map(x -> x.value).collect(Collectors.toList()) +
                ", children=" + children.stream().map(x -> x.value).collect(Collectors.toList()) +
                ", terminal=" + isTerminal() +
                '}';
        }
    }

}
