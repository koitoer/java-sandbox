package com.koitoer.java.let.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 652. Find Duplicate Subtrees
 * We serialize each node and save its string representation in a list.
 * If the node representation is not in that list we will add that node
 * We need to use stringBuilder which is faster than a concatenation.
 */
public class Solution652 {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        //All the non-repeated nodes
        List<String> nodes = new ArrayList();
        //Representation of the nodes that are already in the response
        List<String> sameNodes = new ArrayList();
        //The final result
        List<TreeNode> result = new ArrayList<>();
        checkEachNode(root, nodes, sameNodes, result);
        return result;
    }

    private void checkEachNode(TreeNode node, List<String> nodes, List<String> sameNodes, List<TreeNode> result) {
        if (node == null) {
            return;
        }

        String serializeNode = serialize(node);
        if (!nodes.contains(serializeNode)) {
            nodes.add(serializeNode);
        } else {
            //that means the value is repeated
            //but we add other list to avoid duplicate so we check if in the other list the Node does not exists.
            //then we add.
            if (!sameNodes.contains(serializeNode)) {
                result.add(node);
                sameNodes.add(serializeNode);
            }
        }

        checkEachNode(node.left, nodes, sameNodes, result);
        checkEachNode(node.right, nodes, sameNodes, result);
    }

    private String serialize(TreeNode t) {
        if (t == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        sb.append(",");
        sb.append(serialize(t.left));
        sb.append(",");
        sb.append(serialize(t.right));

        return sb.toString();
    }

}
