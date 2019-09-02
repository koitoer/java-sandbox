package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class NoteBook1 {

    @Test
    public void verify_there_is_route() {
        int array[][] = new int[][] { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } };
        Assertions.assertThat(new NoteBook1().isBipartite(array)).isTrue();
    }

    @Test
    public void verify_there_is_route_2() {
        int array[][] = new int[][] { { 1, 2, 3 }, { 0, 2 }, { 0, 1, 3 }, { 0, 2 } };
        Assertions.assertThat(new NoteBook1().isBipartite(array)).isFalse();
    }

    @Test
    public void verify(){
        int array[][] = new int[][]{{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}};
        Assertions.assertThat(new NoteBook1().isBipartite(array)).isFalse();
    }

    @Test
    public void verify2(){
        int array[][] = new int[][]{{4},{},{4},{4},{0,2,3}};
        Assertions.assertThat(new NoteBook1().isBipartite(array)).isTrue();
    }

    public boolean isBipartite(int[][] graph) {
        CodeGraph graph1 = createGraph(graph);
        System.out.println(graph1);
        try {
            for(Node node : graph1.nodeList) {
                coloringGraph(node, 0);
            }
        } catch (IllegalStateException iea) {
            System.out.println("ERROR " + graph1);

            return false;
        }
        System.out.println(graph1);

       /**
        if(graph1.nodeList.stream().filter(x -> x.visited == false).count() > 0){
            return false;
        }**/

        return true;
    }

    //Review node and assign color
    private void coloringGraph(Node node, int color) {
        if(node.visited == true && node.color != color && color != 0 ){
            throw new IllegalStateException();
        }
        if(node.visited == true){
            return;
        }

        node.visited = true;
        node.color = getInitialColor(color);

        for(Node node1 : node.children){
            int nextColor = node.color == 1 ? 2 : 1;
            coloringGraph(node1, nextColor);
        }

    }

    private int getInitialColor(int color) {
        if(color == 0){
            return 1;
        }
        return color;
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

        private int color = 0;

        private boolean isTerminal() {
            return children.isEmpty();
        }

        //private List<Node> parents = new ArrayList<>();

        private List<Node> children = new ArrayList<>();

        @Override
        public String toString() {
            return "Node{" +
                "value=" + value +
                ", color =" + color +
                // ", parent=" + parents.stream().map(x -> x.value).collect(Collectors.toList()) +
                ", children=" + children.stream().map(x -> x.value).collect(Collectors.toList()) +
                ", visited=" + visited +
                '}';
        }
    }

}
