package com.koitoer.java.ds;

/**
 * Created by koitoer on 7/3/16.
 */
public class BTree {

    public BTreeNode rootNode;

    public void addNode(int data){

        BTreeNode newNode = new BTreeNode(data);

        if(rootNode == null){
            rootNode = newNode;
        }else{

            BTreeNode focusNode = rootNode;
            BTreeNode parentNode;

            while(true){
                parentNode = focusNode;

                if(data <= focusNode.data){
                    focusNode = focusNode.leftNode;
                    if(focusNode == null){
                        parentNode.leftNode = newNode;
                        return;
                    }
                }
                else{

                    focusNode = focusNode.rightNode;
                    if(focusNode == null){
                        parentNode.rightNode = newNode;
                        return;
                    }

                }
            } //while end
        }
    }

    /**
     * Used to traverse the tree in order.
     * Take the left node and go deeper until does not have nothing and print value, then go back to the parent
     * and do the same research in the left node. Left the root node at the middle
     * @param node
     */
    public void inOrderTraverse(BTreeNode node){
        if(node != null){
            this.inOrderTraverse(node.leftNode);
            System.out.println(node.data);
            this.inOrderTraverse(node.rightNode);
        }
    }

    /**
     * Go to the left node until find an end, then go to the right side and do the same, finally
     * print parent value, left the root node until the end
     * @param node
     */
    public void postOrderTraverse(BTreeNode node){
        if(node != null){
            this.postOrderTraverse(node.leftNode);
            this.postOrderTraverse(node.rightNode);
            System.out.println(node.data);
        }
    }


    /**
     * Go always to the left side, but print the parent node at first.
     * @param node
     */
    public void preOrderTraverse(BTreeNode node){
        if(node != null){
            System.out.println(node.data);
            this.preOrderTraverse(node.leftNode);
            this.preOrderTraverse(node.rightNode);
        }
    }


    public boolean remove(int data){
        BTreeNode focusNode = rootNode;
        BTreeNode parentNode = rootNode;
        boolean isAtLeftSide =  true;

        while(focusNode.data != data) {
            parentNode = focusNode;
            if (data < focusNode.data) {
                isAtLeftSide = true;
                focusNode = focusNode.leftNode;
            } else {
                isAtLeftSide = false;
                focusNode = focusNode.rightNode;
            }

            //No node was found
            if (focusNode == null) {
                return false;
            }
        }


        //When there is no left or right node
        if (focusNode.leftNode == null && focusNode.rightNode == null) {
            if (focusNode == rootNode) {
                rootNode = null;
            } else if (isAtLeftSide) {
                parentNode.leftNode = null;
            } else {
                parentNode.rightNode = null;
            }
        }

        //When there is no right node
        else if (focusNode.rightNode == null) {
            if (focusNode == rootNode) {
                rootNode = focusNode.leftNode;
            } else if (isAtLeftSide) {
                parentNode.leftNode = focusNode.leftNode;
            } else {
                parentNode.rightNode = focusNode.leftNode;
            }
        }

        //When there is no left side
        else if (focusNode.leftNode == null) {
            if (focusNode == rootNode) {
                rootNode = focusNode.rightNode;
            } else if (isAtLeftSide) {
                parentNode.leftNode = focusNode.rightNode;
            } else {
                parentNode.rightNode = focusNode.rightNode;
            }
        }

        //Two children are in place
        else {
            BTreeNode replaceNode = getReplaceNode(focusNode);
            if (focusNode == rootNode) {
                rootNode = replaceNode;
            } else if (isAtLeftSide) {
                parentNode.leftNode = replaceNode;
            } else {
                parentNode.rightNode = replaceNode;
            }

            replaceNode.leftNode = focusNode.leftNode;
        }

        return true;
    }


    public BTreeNode getReplaceNode(BTreeNode replacedNode){
        BTreeNode replacementParent = replacedNode;
        BTreeNode replacement = replacedNode;

        BTreeNode focusNode = replacedNode.rightNode;

        while(focusNode != null){
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftNode;
        }

        if (replacement != replacedNode.rightNode) {
            replacementParent.leftNode = replacement.rightNode;
            replacement.rightNode = replacedNode.rightNode;
        }
        return replacement;

    }


}


