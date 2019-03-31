package com.koitoer.training.dfs.model;

import java.util.Stack;

/**
 * Created by mmena on 2/26/18.
 */
public class DFSGraph {

    private int size;

    private AdjList array[];

    public DFSGraph(int size) {
        this.size = size;
        this.array =  new AdjList[this.size];

        for(int i=0 ; i< size ; i++){
            array[i] = new AdjList();
            array[i].head = null;
        }
    }

    public void add(int source, int destination){
        Node n =  new Node(destination, null);
        n.next = array[source].head;
        array[source].head = n;
    }

    public void DFSExplore(int startVertex){

        //Keep track of visited nodes.
        Boolean [] visited = new Boolean[size];
        for(int i=0 ; i<size ; i++){
            visited[i] =false;
        }

        //Define stack for helping support
        Stack<Integer> stack = new Stack<>();

        stack.push(startVertex);
        while(!stack.isEmpty()){

            //Removes the object at the top of this stack and returns that object as the value of this function.
            int n = stack.pop();
            stack.push(n);
            visited[n] = true;

            Node head = array[n].head;
            Boolean isDone = true;

            while(head != null){
                if(visited[head.value] == false){
                    stack.push(head.value);
                    visited[head.value] = true;
                    isDone = false;
                    break;
                }else{
                    head = head.next;
                }
            }

            if(isDone){
                int out =stack.pop();
                System.out.println("Visited node "  + out);
            }

        }

    }

}
