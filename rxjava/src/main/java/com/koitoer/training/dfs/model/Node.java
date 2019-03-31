package com.koitoer.training.dfs.model;

/**
 * Created by mmena on 2/26/18.
 */
public final class Node {

    public int value;

    public Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

}
