package com.koitoer.java.ds.graph.example1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import lombok.Getter;

@Getter
public class SimpleGraph {

    private Map<Vertex, List<Vertex>> vertexListMap;

    public SimpleGraph(int numberOfVertex) {
        vertexListMap = new HashMap<>(numberOfVertex);
    }

    private void addVertex(String label) {
        Vertex vertex = new Vertex(label);
        vertexListMap.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Remove the vertex from map but also for all the adjacent links
     */
    public void removeVertex(String label) {
        Vertex toRemoveVertex = new Vertex(label);
        vertexListMap.values().stream().forEach(vertices -> vertices.remove(toRemoveVertex));
        vertexListMap.remove(toRemoveVertex);
    }

    public void addEdge(String labelA, String labelB) {
        Vertex vertexA = new Vertex(labelA);
        Vertex vertexB = new Vertex(labelB);

        boolean vertexAExist = vertexListMap.containsKey(vertexA);
        if (vertexAExist) {
            vertexListMap.get(vertexA).add(vertexB);
        } else {
            List<Vertex> adjacentList = new ArrayList<>();
            adjacentList.add(vertexB);
            vertexListMap.put(vertexA, adjacentList);
        }
    }

    public List<String> dfs(String root) {
        List<String> visited = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        stack.push(root);

        while (!stack.empty()) {
            String vertexLabel = stack.pop();
            if (!visited.contains(vertexLabel)) {
                visited.add(vertexLabel);
                Vertex auxVertex = new Vertex(vertexLabel);
                if (vertexListMap.get(auxVertex) != null){
                    for (Vertex vertex : vertexListMap.get(auxVertex)) {
                            stack.push(vertex.getVertexName());
                    }
                }
            }
        }

        return visited;
    }
}
