package com.koitoer.java.ds.graph.example0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GraphExample {

    //Representation - 1
    public LinkedList<Integer> adjacentList[];

    //Representation - 2
    public String[][] adjacentListByArray;

    //Representation - 3
    public Vertex[] vertices;

    //Create a graph using the Representation - 1
    public void createGraphUsingList() {

        //Define the number of nodes.
        int n = 9;

        //Create array that will hold all the vertex
        adjacentList = new LinkedList[n];

        //Add to each index the edges.
        adjacentList[0] = createEdge(1, 2, 3);
        adjacentList[1] = createEdge(7);
        adjacentList[2] = createEdge(5, 6);
        adjacentList[3] = createEdge(4);
        adjacentList[4] = createEdge();
        adjacentList[5] = createEdge();
        adjacentList[6] = createEdge();
        adjacentList[7] = createEdge(8);
        adjacentList[8] = createEdge();
    }

    //Create a graph using the Representation - 2
    public void createGraphUsingArray() {

        //Each position represent a vertex and its neighbors
        adjacentListByArray = new String[][] { { "B", "E", "F" }, { "C" }, { "I" }, { "G" }, {}, {}, {}, { "D" }, {} };
    }

    //Create a graph using the Representation - 2
    public void createGraphUsingObject() {

        //Define the number of nodes.
        int n = 9;

        //Each position represent a vertex and its neighbors
        vertices = new Vertex[n];

        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");
        Vertex vertexE = new Vertex("E");
        Vertex vertexF = new Vertex("F");
        Vertex vertexG = new Vertex("G");
        Vertex vertexH = new Vertex("H");
        Vertex vertexI = new Vertex("I");

        vertexA.neighbors.add(vertexB);
        vertexA.neighbors.add(vertexE);
        vertexA.neighbors.add(vertexF);
        vertexB.neighbors.add(vertexC);
        vertexE.neighbors.add(vertexI);
        vertexF.neighbors.add(vertexG);
        vertexC.neighbors.add(vertexD);

        vertices = new Vertex[] { vertexA, vertexB, vertexC, vertexD, vertexE, vertexF, vertexG, vertexH, vertexI };
    }

    private LinkedList<Integer> createEdge(Integer... level) {
        if (level.length == 0) {
            return new LinkedList<>();
        }
        return new LinkedList(Arrays.asList(level));
    }

    private class Vertex {

        private String label;

        private List<Vertex> neighbors = new ArrayList<>();

        private Vertex(String label) {
            this.label = label;
        }
    }
}
