package com.koitoer.training.dfs;

import org.junit.Before;
import org.junit.Test;

import com.koitoer.training.dfs.model.DFSGraph;

/**
 * Created by mmena on 2/26/18.
 */
public class DeepFirstSearch {

    @Before
    public void init(){

    }

    //0
    //|  \
    //1   2
    //| \   \
    //3  4  5
    @Test
    public void testImplementation(){
        DFSGraph graph = new DFSGraph(6);
        graph.add(0,2);
        graph.add(0,1);

        graph.add(1,4);
        graph.add(1,3);
        graph.add(1,0);

        graph.add(3,1);
        graph.add(4,1);

        graph.add(2,5);
        graph.add(2,0);

       graph.add(5,2);


        graph.DFSExplore(0);
    }

}
