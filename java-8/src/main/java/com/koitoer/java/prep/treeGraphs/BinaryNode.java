package com.koitoer.java.prep.treeGraphs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * BinaryNode will be a node for a binary tree
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BinaryNode<T> {

    public BinaryNode leftNode;

    public BinaryNode rightNode;

    public final T value;

}
