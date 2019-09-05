package com.koitoer.java.prep.treeGraphs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * BinaryTreeNode for integers.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BinaryTreeNode {

    private final Integer value;

    private BinaryTreeNode left;

    private BinaryTreeNode right;

    /**
     * Find the node that contains the value.
     */
    public BinaryTreeNode find(int searchValue) {
        if (this.value == searchValue) {
            return this;
        } else if (searchValue < this.value && left != null) {
            return left.find(searchValue);
        } else if (right != null) {
            return right.find(searchValue);
        }
        return null;
    }

    /**
     * Do the insertion of a binary node in the tree
     */
    public void insert(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode.value < this.value) {
            if (this.getLeft() == null) {
                this.setLeft(binaryTreeNode);
            }
            this.getLeft().insert(binaryTreeNode);
        }else {
            if (this.getRight() == null) {
                this.setRight(binaryTreeNode);
            } else {
                this.getRight().insert(binaryTreeNode);
            }
        }
    }

    public void delete(int deleteValue){
        if(this.value == deleteValue){
            //CASE 1: Node is a leaf.
            if(this.left == null && this.right == null){

            }
        }
        //CASE 1 : Node is a leaf.
    }

    public Integer findMax() {
        if(this.getRight() != null){
           return this.getRight().findMax();
        }
        return this.getValue();
    }

}
