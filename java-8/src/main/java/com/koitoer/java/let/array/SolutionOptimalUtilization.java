package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import lombok.Data;

/**
 * https://leetcode.com/discuss/interview-question/373202/amazon-oa-2019-optimal-utilization
 * Basically you create all the combinations, you can optimize this one if you sort the arrays first.
 */
public class SolutionOptimalUtilization {

    @Test
    public void test() {
        int a[][] = new int[][] { { 1, 2 }, { 2, 4 }, { 3, 6 } };
        int b[][] = new int[][] { { 1, 2 } };
        int target = 7;

        Assertions.assertThat(new SolutionOptimalUtilization().getSumIds(a, b, target))
            .containsOnly(new Triplet(2, 1, 6));
    }

    @Test
    public void test2() {
        int a[][] = new int[][] { { 1, 3 }, { 2, 5 }, { 3, 7 }, { 4, 10 } };
        int b[][] = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        int target = 10;

        Assertions.assertThat(new SolutionOptimalUtilization().getSumIds(a, b, target))
            .containsOnly(new Triplet(2, 4, 10), new Triplet(3, 2, 10));
    }

    @Test
    public void test3() {
        int[][] aC = { { 1, 8 }, { 2, 7 }, { 3, 14 } };
        int[][] bC = { { 1, 5 }, { 2, 10 }, { 3, 14 } };
        int target = 20;

        Assertions.assertThat(new SolutionOptimalUtilization().getSumIds(aC, bC, target))
            .containsOnly(new Triplet(3, 1, 19));
    }

    @Test
    public void test4() {
        int[][] aC = { { 1, 8 }, { 2, 7 }, { 3, 14 } };
        int[][] bC = { { 1, 5 }, { 2, 10 }, { 3, 14 } };
        int target = 20;

        Assertions.assertThat(new SolutionOptimalUtilization().getSumIds(aC, bC, target))
            .containsOnly(new Triplet(3, 1, 19));
    }

    public List<Triplet> getSumIds(int[][] a, int[][] b, int target) {
        List<Triplet> t = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                Triplet triplet = new Triplet(a[i][0], b[j][0], a[i][1] + b[j][1]);
                t.add(triplet);
            }
        }

        Collections.sort(t, (o1, o2) -> o1.getTotal() - o2.getTotal());

        List<Triplet> result = t.stream()
            .filter(triplet -> triplet.getTotal() == target)
            .collect(Collectors.toList());

        if (!result.isEmpty()) {
            return result;
        }

        result = t.stream()
            .filter(triplet -> triplet.getTotal() < target)
            .collect(Collectors.toList());

        Collections.sort(result, (o1, o2) -> o2.getTotal() - o1.getTotal());

        int from = 1;
        int value = result.get(0).getTotal();
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i).getTotal() == value) {
                from++;
            }
        }

        return result.subList(0, from);
    }

    @Data
    class Triplet {

        private final int iA;

        private final int iB;

        private final int total;
    }

}
