package com.koitoer.training.dfs2.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by mmena on 2/26/18.
 */
public class Node {

    public boolean visited;

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    private List<Node> neighbours;

    public int data;

    public Node(int data) {
        this.data = data;
        this.visited = false;
        this.setNeighbours(Collections.emptyList());
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }


}
