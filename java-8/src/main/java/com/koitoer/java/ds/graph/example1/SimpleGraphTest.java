package com.koitoer.java.ds.graph.example1;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * A --> B
 * \
 * S --> C --> D
 * |     | \
 * G --> F E
 * |       |
 * H  <-----
 */
public class SimpleGraphTest {

    @Test
    public void testDFS() {
        SimpleGraph simpleGraph = new SimpleGraph(9);
        simpleGraph.addEdge("A", "B");
        simpleGraph.addEdge("A", "S");
        simpleGraph.addEdge("S", "C");
        simpleGraph.addEdge("C", "D");
        simpleGraph.addEdge("S", "G");
        simpleGraph.addEdge("C", "F");
        simpleGraph.addEdge("G", "F");
        simpleGraph.addEdge("G", "H");
        simpleGraph.addEdge("C", "E");
        simpleGraph.addEdge("E", "H");

        List<String> visitOrder = simpleGraph.dfs("S");
        Assertions.assertThat(visitOrder.toArray(new String[visitOrder.size()]))
            .isEqualTo(new String[] { "S", "G", "H", "F", "C", "E", "D" });
        System.out.println(visitOrder);

        visitOrder = simpleGraph.dfs("A");
        Assertions.assertThat(visitOrder.toArray(new String[visitOrder.size()]))
            .isEqualTo(new String[] { "A", "S", "G", "H", "F", "C", "E", "D", "B" });
        System.out.println(visitOrder);

        visitOrder = simpleGraph.dfs("E");
        Assertions.assertThat(visitOrder.toArray(new String[visitOrder.size()]))
            .isEqualTo(new String[] { "E", "H" });

        visitOrder = simpleGraph.dfs("C");
        Assertions.assertThat(visitOrder.toArray(new String[visitOrder.size()]))
            .isEqualTo(new String[] { "C", "E", "H", "F", "D"});
        System.out.println(visitOrder);
    }
}
