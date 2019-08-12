package com.koitoer.java.let.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.val;

class Solution {



    public static void main(String[] args) {
        //int trust[][] = new int[][]{{1,3},{1,4},{2,3},{2,4},{4,3}};
        int trust[][] = new int[][]{{1,3},{2,3}};
        System.out.println("Solucion ->" + new Solution().findJudge(3, trust));
    }

    public int findJudge(int N, int[][] trust) {

        List<Person> people = new ArrayList<>(N);
        for (int i = 0; i <= N + 1; i++) {
            people.add(new Person(i));
        }

        for (int i = 0; i < trust.length; i++) {
            createPerson(people, trust[i][0], trust[i][1]);
        }

        Person truste = people.stream()
            // There should be not outbound connections for the king
            .filter(person -> person.outbound == 0)
            // Only one can be the maximum in terms of inbound connections.
            .max(Comparator.comparingInt(o -> o.inbound))
            .orElse(null);

        if (truste == null)
            return -1;

        if (truste.inbound == N - 1) {
            return truste.personNumber;
        }

        return -1;
    }

    private void createPerson(List<Person> people, int peopleOut, int peopleIn) {
        people.get(peopleOut - 1).outbound++;
        people.get(peopleIn - 1).inbound++;
    }

    private class Person {

        public Person(int personNumber) {
            this.personNumber = personNumber + 1;
        }

        int personNumber;

        int inbound;

        int outbound;
    }
}