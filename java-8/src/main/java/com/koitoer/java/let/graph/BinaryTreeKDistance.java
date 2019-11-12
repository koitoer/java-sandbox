package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class BinaryTreeKDistance {

    Map<BinaryNode, BinaryNode> parents = new HashMap<>();

    @Test
    public void testLevelK2() {
        BinaryNode root = new BinaryNode(3);
        BinaryNode nodeA = new BinaryNode(5);
        BinaryNode nodeB = new BinaryNode(1);
        BinaryNode nodeC = new BinaryNode(6);
        BinaryNode nodeD = new BinaryNode(2);
        BinaryNode nodeE = new BinaryNode(0);
        BinaryNode nodeF = new BinaryNode(8);
        BinaryNode nodeG = new BinaryNode(7);
        BinaryNode nodeH = new BinaryNode(4);

        root.setLeft(nodeA);
        root.setRight(nodeB);
        nodeA.setLeft(nodeC);
        nodeA.setRight(nodeD);
        nodeD.setLeft(nodeG);
        nodeD.setRight(nodeH);
        nodeB.setLeft(nodeE);
        nodeB.setRight(nodeF);

        Assertions.assertThat(getNodesAtJump(root, nodeA, 2)).extracting("value").containsOnly(4, 7, 1);
    }

    private List<BinaryNode> getNodesAtJump(BinaryNode root, BinaryNode nodeA, int k) {
        iterateTree(root, parents, null);

        LinkedList linkedList = new LinkedList();
        linkedList.push(nodeA);
        List seen = new ArrayList<>();
        seen.add(nodeA);

        return iterateByLevel(0, k, seen, linkedList);
    }

    private List<BinaryNode> iterateByLevel(int initialLevel, int kLevel, List<BinaryNode> seen, LinkedList<BinaryNode> q) {

        LinkedList q2 = new LinkedList();
        while (!q.isEmpty()) {
            BinaryNode node = q.poll();
            List<BinaryNode> relatives = getRelatives(node);
            for (BinaryNode binaryNode : relatives) {
                if (!seen.contains(binaryNode)) {
                    q2.push(binaryNode);
                }
            }
        }
        if (kLevel == ++initialLevel) {
            return q2;
        }
        return iterateByLevel(initialLevel, kLevel, seen, q2);
    }

    private List<BinaryNode> getRelatives(BinaryNode node) {
        List<BinaryNode> relatives = new ArrayList<>(3);
        if (node.getLeft() != null) {
            relatives.add(node.getLeft());
        }
        if (node.getRight() != null) {
            relatives.add(node.getRight());
        }
        BinaryNode parent = parents.get(node);
        if (parent != null) {
            relatives.add(parent);
        }
        return relatives;
    }

    private void iterateTree(BinaryNode node, Map<BinaryNode, BinaryNode> parents, BinaryNode parent) {
        if (node == null) {
            return;
        } else if (node.getLeft() == null && node.getRight() == null) {
            parents.put(node, parent);
            return;
        }
        parents.put(node, parent);
        iterateTree(node.getLeft(), parents, node);
        iterateTree(node.getRight(), parents, node);
    }

}

@Data
@RequiredArgsConstructor
class BinaryNode {

    public final int value;

    @Setter
    private BinaryNode right;

    @Setter
    private BinaryNode left;

    @Override public String toString() {
        return "BinaryNode{" +
            "value=" + value +
            '}';
    }
}

