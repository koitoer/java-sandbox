package com.koitoer.java.let.array;

/**
 * Created by mmena on 4/1/19.
 */
public class Solution2 {

    public static void main(String[] args) {

        int[][] E = { { 0, 0, 1, 1, 0, 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 0, 0, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 },
            { 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, { 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0, 0, 1, 0, 0, 1 }, { 0, 1, 1, 0, 0, 1, 0, 1, 1, 0 },
            { 1, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 1, 0, 1, 0, 1, 1, 1, 0, 0, 1 } };

        Solution2 solution2 = new Solution2();
        solution2.numEnclaves(E);
        //14
    }

    private boolean debug = false;

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

    public int numEnclaves(int[][] A) {
        Island[][] map = transform(A);
        if (debug) {
            printMap(map, 0, 0);
        }

        int numberOfRows = A.length;
        int numberOfCol = A[0].length;

        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfCol; column++) {
                Island island = map[row][column];
                if (island.isShore) {
                    visit(map, row, column);
                }
            }
        }

        int countNotConnected = countNotConnected(map);

        if (debug) {
            System.out.println("---------------------------- OOOOOOOO  -------------------------------------");
            printMap(map, 0, 0);
            System.out.println(countNotConnected);
        }
        return countNotConnected;
    }

    /**
     * Verify the next movement is valid
     * Verify that we haven't visit that node.
     * If the value is one, we will proceed to verify the surrounding and we mark the node as traceable and as visited.
     */
    private void visit(Island[][] map, int row, int col) {
        if (isInvalidRange(row, col, map.length, map[0].length)) {
            return;
        }

        Island island = map[row][col];
        if (island.isVisited) {
            return;
        }

        //Only start in shore and value = 1
        if (island.value == 1) {
            island.isVisited = true;
            island.canReachShore = true;
            visit(map, row - 1, col);
            visit(map, row, col - 1);
            visit(map, row + 1, col);
            visit(map, row, col + 1);
        }

    }

    /**
     * Transform to an array of Island's
     */
    private static Island[][] transform(int[][] a) {
        int numberOfRows = a.length;
        int numberOfCol = a[0].length;

        Island[][] map = new Island[numberOfRows][numberOfCol];
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfCol; column++) {
                Island island = new Island(a[row][column], isShore(row, column, numberOfRows, numberOfCol));
                map[row][column] = island;
            }
        }
        return map;
    }

    /**
     * Verify the island is shore, if we can not track this from a shore then it is not worth it.
     */
    public static boolean isShore(int row, int column, int rowNumber, int colNumber) {
        if (row == 0 || row == rowNumber - 1) {
            return true;
        } else if (column == 0 || column == colNumber - 1) {
            return true;
        }
        return false;
    }

    /**
     * Verify if the movement is valid or it will be outside of the grid.
     */
    private static boolean isInvalidRange(int row, int column, int numberOfRows, int numberOfCol) {
        if (row < 0 || row >= numberOfRows) {
            return true;
        } else if (column < 0 || column >= numberOfCol) {
            return true;
        }
        return false;
    }

    /**
     * Used for debugging purposes only
     */
    private static void printMap(Island[][] map, int row1, int col1) {
        int numberOfRows = map.length;
        int numberOfCol = map[0].length;

        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfCol; column++) {
                if (row == row1 && column == col1) {
                    System.out
                        .printf(map[row][column].value + "|" + map[row][column].canReachShore + "|" + map[row][column].isVisited + "**| ");
                } else {
                    System.out
                        .print(map[row][column].value + "|" + map[row][column].canReachShore + "|" + map[row][column].isVisited + "| ");
                }
            }
            System.out.println();
        }
    }

    private static int countNotConnected(Island[][] a) {
        int count = 0;
        int numberOfRows = a.length;
        int numberOfCol = a[0].length;
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfCol; column++) {
                Island island = a[row][column];
                if (island.value == 1 && island.canReachShore == false) {
                    count++;
                }
            }
        }
        return count;
    }


}
