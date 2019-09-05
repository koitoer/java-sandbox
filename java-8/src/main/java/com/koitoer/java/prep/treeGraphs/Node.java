package com.koitoer.java.prep.treeGraphs;

/**
 * Binary Trees impose the condition in which left descendant are less than or equal to the current node , which is less than the
 * right descendants.
 * O(log n) for insert and find are balanced enough. [Red-black trees and AVL trees]
 */
public class Node {

    public String value;

    public Node[] children;

}
