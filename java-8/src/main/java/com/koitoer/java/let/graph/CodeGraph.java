package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.List;

public class CodeGraph {

    private List<Node> nodeList;
}

class Node<T> {

    public Node(T value) {
        this.value = value;
    }

    private T value;

    private List<Node> parents = new ArrayList<>();

    private List<Node> children = new ArrayList<>();
}
