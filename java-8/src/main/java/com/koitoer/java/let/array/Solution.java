package com.koitoer.java.let.array;

/**
 * Created by mmena on 3/31/19.
 */
public class Solution {

    private static int result = 0;

    private static class Island {

        private int value = 0;

        private boolean isShore = false;

        private boolean canReachShore = false;

        private boolean isVisited = false;

        public Island(int value, boolean isShore) {
            this.value = value;
            this.isShore = isShore;
            this.canReachShore = isShore && value == 1;
        }
    }

    public static void main(String[] args) {
        int[][] A = { { 1 } };
        Island[][] map1 = transform(A);
        System.out.println("Result -> " + numEnclaves(map1));

        result = 0;

        int[][] B = { { 1, 0 }, { 0, 1 } };
        Island[][] map2 = transform(B);
        System.out.println("Result -> " + numEnclaves(map2));


        result = 0;
        int[][] C = { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
        Island[][] map = transform(C);
        printMap(map);
        System.out.println("Result -> " + numEnclaves(map));

        result = 0;
        int[][] D = { { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } };
        Island[][] mapA = transform(D);
        printMap(mapA);
        System.out.println("Result -> " + numEnclaves(mapA));
        printMap(mapA);
    }

    private static void printMap(Island[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map.length; column++) {
                System.out.print(map[row][column].value + "|" + map[row][column].isShore + "|" + map[row][column].canReachShore + "|" + map[row][column].isVisited + " ");
            }
            System.out.println();
        }
    }

    private static Island[][] transform(int[][] a) {
        Island[][] map = new Island[a.length][a.length];
        for (int row = 0; row < a.length; row++) {
            for (int column = 0; column < a.length; column++) {
                Island island = new Island(a[row][column], isShore(row, column, a.length));
                map[row][column] = island;
            }
        }
        return map;
    }

    private static int numEnclaves(Island[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map.length; column++) {
                Island island = map[row][column];
                if (!island.isVisited) {
                    checkSurrounding(row, column, map);
                }
            }
        }

        return result;
    }

    private static void checkSurrounding(int row, int column, Island[][] map) {
        if (isInvalidRange(row, column, map.length)) {
            return;
        }

        Island island = map[row][column];
        if (island.value == 0) {
            island.isVisited = true;
            return;
        }

        if (island.canReachShore) {
            result++;
            island.isVisited = true;
            return;
        }


        if(!island.isVisited) {
            island.isVisited = true;
            checkSurrounding(row - 1, column, map);
            checkSurrounding(row, column - 1, map);
            checkSurrounding(row, column + 1, map);
            checkSurrounding(row + 1, column, map);
        }
    }

    private static boolean isInvalidRange(int row, int column, int size) {
        if (row < 0 || row >= size) {
            return true;
        } else if (column < 0 || column >= size) {
            return true;
        }
        return false;
    }

    /**
     * Verify the island is shore.
     */
    public static boolean isShore(int row, int column, int size) {
        if (row == 0 || row == size - 1) {
            return true;
        } else if (column == 0 || column == size - 1) {
            return true;
        }

        return false;
    }



}
