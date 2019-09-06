package com.koitoer.java.ds.graph.example0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import com.koitoer.java.ds.graph.example1.Vertex;

public class GraphOperations {

    public void bfs(LinkedList<Integer> adjacentList[]) {

        //Array will store the nodes for each level.
        List<LinkedList<Integer>> levelNodes = new ArrayList<>();

        //Nodes that will be in the frontier
        List<Integer> frontier = new ArrayList<>();

        //Nodes that we already visited.
        List<Integer> visited = new ArrayList<>();

        //Initialize the level before enter the while loop
        int i = 1;

        //In the level 0, only the node zero will be.
        levelNodes.add(new LinkedList<>(Collections.singletonList(0)));

        //The node source is already visited.
        visited.add(0);

        //Initialize frontier with the neighbors of the source
        frontier.addAll(adjacentList[0]);

        //Start while loop
        while (!frontier.isEmpty()) {

            //Initialize the levelNode list
            levelNodes.add(new LinkedList<>());

            //Clean out the new frontier to add new elements.
            List<Integer> newFrontier = new LinkedList<>();

            //Get the element from the frontier
            for (Integer vertex : frontier) {

                //Add to the level nodes.
                levelNodes.get(i).add(vertex);

                //Mark as visited
                visited.add(vertex);

                //Get the neighbors of the vertex
                for (Integer neighbor : adjacentList[vertex]) {

                    //Check if has not been visited.
                    if (!visited.contains(neighbor)) {

                        //Create the new frontier
                        newFrontier.add(neighbor);
                    }
                }
            }

            // The new frontier for the next iteration
            frontier = newFrontier;

            //Let's move the index.
            i = i + 1;
        }

        levelNodes.stream().forEach(integers -> System.out.println(integers));
        System.out.println("visited" + visited);
    }

    public void bfsWithQueue(LinkedList<Integer> adjacentList[]) {

        //Array will store the nodes for each level.
        List<Vertex> vertices = new ArrayList<>();

        //Initialize stack
        Queue<Integer> integerQueue = new LinkedBlockingQueue<>();

        //Initialize visited list
        List<Integer> visited = new ArrayList<>();

        //Add first element to the stack
        integerQueue.add(0);

        //Add the root to the zero level
        vertices.add(new Vertex("0", 0));

        //While loop
        while (integerQueue.peek() != null) {

            //Get the element form the stack
            Integer index = integerQueue.poll();

            //Mark the node as visited.
            visited.add(index);

            //Get the current level List
            LinkedList<Integer> currentLevel = new LinkedList<>();

            //Get Neighbors of node
            LinkedList<Integer> neighborList = adjacentList[index];

            //Loop the neighbor add them to the stack.
            for (Integer neighbor : neighborList) {

                //Verify we haven't visited.
                if (!visited.contains(neighbor)) {

                    //Push the new nodes to the stack
                    integerQueue.add(neighbor);

                    //Add the items to the level
                    currentLevel.add(neighbor);

                    //Get parent level
                    Integer level = vertices.stream()
                        .filter(x -> Integer.parseInt(x.getVertexName()) == index)
                        .findFirst().get().getLevel();

                    vertices.add(new Vertex(String.valueOf(neighbor), ++level));

                }
            }
        }

        Map<Integer, List<Vertex>> map = vertices.stream().collect(Collectors.groupingBy(vertex -> vertex.getLevel()));

        map.values().stream().forEach(
            vertices1 -> System.out.println(vertices1.stream().map(Vertex::getVertexName).collect(Collectors.joining(", "))));

        System.out.println("visitedByQueue " + visited);

    }

}
