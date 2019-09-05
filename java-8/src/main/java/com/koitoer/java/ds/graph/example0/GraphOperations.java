package com.koitoer.java.ds.graph.example0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        levelNodes.add(new LinkedList<>(Arrays.asList(0)));

        //The node source is already visited.
        visited.add(0);

        //Initialize frontier with the neighbors of the source
        adjacentList[0].stream().forEach(frontier::add);

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
            i = i +1;
        }

        levelNodes.stream().forEach(integers -> System.out.println(integers));
        System.out.println("visited" + visited);
    }

}
