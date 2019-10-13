package com.koitoer.java.let.graph;

import java.util.List;

/**
 * 841. Keys and Rooms
 * DFS problem, have a boolean array to know which rooms you have visited.
 */
public class Solution841 {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int numberOfRooms = rooms.size();
        boolean[] visitedRooms = new boolean[numberOfRooms];

        visit(0, visitedRooms, rooms);

        for (boolean value : visitedRooms) {
            if (!value) {
                return false;
            }
        }
        return true;

    }

    private void visit(Integer room, boolean[] visited, List<List<Integer>> rooms) {

        //If we already visit the room, don't follow
        if (visited[room]) {
            return;
        }

        //We are visiting a new room, then mark it.
        visited[room] = true;
        List<Integer> keysInRoom = rooms.get(room);

        for (Integer keyOfRoom : keysInRoom) {
            visit(keyOfRoom, visited, rooms);
        }

    }
}