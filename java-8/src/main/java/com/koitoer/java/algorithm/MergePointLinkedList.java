package com.koitoer.java.algorithm;

/**
 * Created by mauricio.mena on 04/08/2016.
 */
public class MergePointLinkedList {

    static class Node{
        int data;
        Node next;
    }

    /**
     * Brute force, check each node from B in A, the first match is the merge point
     * Complexity is O(mn)
     * @return
     */
    public Node findMergePoint(Node a, Node b){
        //Check the length of linked list A (n)
        //Check the length of linked list B (m)
        //Take each one by one the element in A
            //Take each one by one element in B
                //Compare two nodes
                    //If is equal we have merge point

        return new Node();
    }



    /**
     * Use hash technique to identify the correct pair of merge point
     * Complexity is O(m log n + n log n), but we use extra space
     * @return
     */
    public Node findMergePointByHash(Node a, Node b){
        //Check the length of linked list A (n)
        //Check the length of linked list B (m)
        //Add all the values and objects to Hash table all the B elements
        //Take each one by one the element in A
            //Look in the hash to find a match and return the coincidence
        return new Node();
    }


    /**
     * Corner cases is>
     *  When there is no merge point
     *  If m>n, we need to swap to avoid d as negative value.
     * Remove nodes that are not allow to make the same distance to the merge point
     * after that start iterating to the merge point
     * @param a
     * @param b
     * @return
     */
    public Node findMergePointByPuttingSameLength(Node a, Node b){
        //Check the length of linked list A O(n)
        //Check the length of linked list B O(m)
        //Calculate the value d = m-n
        //Then A and B are equidistant from the merge point O(m+n) time complexity
        //Loop to A and B, comparing the values of the node and then the coincidence will be found O(1)
        //          space complexity, we don't need more space
        return new Node();
    }

}
