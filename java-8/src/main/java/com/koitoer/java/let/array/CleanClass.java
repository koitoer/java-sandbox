package com.koitoer.java.let.array;

/**
 * Created by mmena on 3/31/19.
 */
public class CleanClass {

    private int numberOfIsland = 0;

    private int result = 0;

    private static class Island {

        private int value = 0;

        private boolean canReachShore = false;

        private boolean isVisited = false;

        public Island(int value, boolean isShore) {
            this.value = value;
            this.canReachShore = isShore && value == 1;
        }
    }

    public static void main(String[] args) {
        int[][] A = { { 1 } };
        CleanClass cleanClass = new CleanClass();
       // System.out.println("Result -> " + cleanClass.numEnclaves(A));

        int[][] B = { { 1, 0 }, { 0, 1 } };
        CleanClass cleanClass2 = new CleanClass();
        //System.out.println("Result -> " + cleanClass2.numEnclaves(B));

        int[][] C = { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
        cleanClass2 = new CleanClass();
        //System.out.println("Result -> " + cleanClass2.numEnclaves(C));

        int[][] D = { { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } };
        cleanClass2 = new CleanClass();
        // System.out.println("Result -> " + cleanClass2.numEnclaves(D));

        int[][] F = { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
        cleanClass2 = new CleanClass();
       //System.out.println("Result -> " + cleanClass2.numEnclaves(F));

       // [[0, 0, 1, 1, 0, 0, 0, 0, 0, 1 ], [ 1, 1, 0, 1, 0, 0, 1, 0, 0, 1 ], [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ],[ 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 ], [ 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 ], [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],[ 1, 0, 1, 0, 1, 1, 1, 0, 1, 0 ], [ 0, 1, 1, 1, 0, 0, 1, 0, 0, 1 ], [ 0, 1, 1, 0, 0, 1, 0, 1, 1, 0 ],[ 1, 0, 1, 1, 0, 0, 1, 1, 0, 0 ], [ 1, 0, 1, 0, 1, 1, 1, 0, 0, 1 ]]

        int[][] E = { { 0, 0, 1, 1, 0, 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 0, 0, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 },
            { 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, { 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0, 0, 1, 0, 0, 1 }, { 0, 1, 1, 0, 0, 1, 0, 1, 1, 0 },
            { 1, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 1, 0, 1, 0, 1, 1, 1, 0, 0, 1 } };

        cleanClass2 = new CleanClass();
        //System.out.println("Result -> " + cleanClass2.numEnclaves(E));
    //14

       int [][] G = {{0,0,0,1,1,1,0,1,0,0},{1,1,0,0,0,1,0,1,1,1},{0,0,0,1,1,1,0,1,0,0},
        {0,1,1,0,0,0,1,0,1,0},{0,1,1,1,1,1,0,0,1,0},{0,0,1,0,1,1,1,1,0,1},{0,1,1,0,0,0,1,1,1,1},
        {0,0,1,0,0,1,0,1,0,1},{1,0,1,0,1,1,0,0,0,0},{0,0,0,0,1,1,0,0,0,1}};
            cleanClass2 = new CleanClass();
          //  System.out.println("Result -> " + cleanClass2.numEnclaves(G));
        //3

        //[[1,0,1,0,1,0,0,0,1,0,0,1],[0,1,0,0,1,0,0,1,0,1,0,1],[1,0,1,1,1,0,1,0,0,0,1,0],[0,0,1,0,0,1,1,0,0,1,1,0],[0,1,0,1,0,1,0,0,0,0,0,1],[0,0,1,1,0,1,1,1,0,0,0,0],[1,1,1,0,1,1,0,1,1,1,0,0],[0,1,0,0,0,0,1,1,1,1,0,0],[1,1,0,0,0,0,1,0,0,1,1,0],[0,0,0,0,0,1,0,1,1,1,0,0],[0,0,0,1,1,1,1,1,1,1,0,1]]
        int [][] I = {{1,0,1,0,1,0,0,0,1,0,0,1},{0,1,0,0,1,0,0,1,0,1,0,1},{1,0,1,1,1,0,1,0,0,0,1,0},
        {0,0,1,0,0,1,1,0,0,1,1,0},{0,1,0,1,0,1,0,0,0,0,0,1},{0,0,1,1,0,1,1,1,0,0,0,0},{1,1,1,0,1,1,0,1,1,1,0,0},{0,1,0,0,0,0,1,1,1,1,0,0},
        {1,1,0,0,0,0,1,0,0,1,1,0},{0,0,0,0,0,1,0,1,1,1,0,0},{0,0,0,1,1,1,1,1,1,1,0,1}};
        cleanClass2 = new CleanClass();
        System.out.println("Result -> " + cleanClass2.numEnclaves(I));
    }

    private static void printMap(Island[][] map) {

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                System.out.print(map[row][column].value + "|" + map[row][column].canReachShore + "|" + map[row][column].isVisited + "| " );
            }
            System.out.println();
        }
    }

    private static void printMap(Island[][] map, int row1, int col1) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map.length; column++) {
                if(row == row1 && column == col1){
                    System.out.print(map[row][column].value + "|" + map[row][column].canReachShore + "|" + map[row][column].isVisited + "**| " );
                }else{
                    System.out.print(map[row][column].value + "|" + map[row][column].canReachShore + "|" + map[row][column].isVisited + "| " );
                }
            }
            System.out.println();
        }
    }

    public int numEnclaves(int[][] A) {
        Island[][] map1 = transform(A);
        //printMap(map1);
        numEnclaves(map1);
        printMap(map1);
        return countNotConnected(map1);
    }

    private int countNotConnected(Island[][] a) {
        int count = 0;
        int numberOfRows = a.length;
        int numberOfCol = a[0].length;
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfCol; column++) {
                Island island = a[row][column];
                if(island.value == 1 && island.canReachShore == false){
                    count++;
                }
            }
        }

        return count;
    }

    private Island[][] transform(int[][] a) {
        int numberOfRows = a.length;
        int numberOfCol = a[0].length;
        Island[][] map = new Island[numberOfRows][numberOfCol];
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfCol; column++) {
                Island island = new Island(a[row][column], isShore(row, column, map));
                map[row][column] = island;
                if(island.value == 1){
                    numberOfIsland++;
                }
            }
        }
        return map;
    }

    private int numEnclaves(Island[][] map) {
        int numberOfRows = map.length;
        int numberOfCol = map[0].length;
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfCol; column++) {
                Island island = map[row][column];
                island.isVisited = false;

                if (!island.isVisited) {
                    checkSurrounding(row, column, map);
                    //island.isVisited = true;
                }
            }
        }

        return result;
    }

    private boolean checkSurrounding(int row, int column, Island[][] map) {
        if (isInvalidRange(row, column, map)) {
            return false;
        }

        //System.out.println(" ------------------------------ ");
        //printMap(map, row, column);
        Island island = map[row][column];

        if(island.isVisited){
            return island.canReachShore;
        }

        if (island.value == 0) {
            island.isVisited = true;
            return false;
        }

        if (island.canReachShore) {
            island.isVisited = true;
            return true;
        }

        if (!island.isVisited) {

            island.isVisited = true;

            //checkSurrounding(row - 1, column - 1, map);
            //UP
            if(checkSurrounding(row - 1, column, map)){
                island.canReachShore = true;
                island.isVisited = true;
                return true;
            }
            //LEFT
            //checkSurrounding(row - 1, column + 1, map);
            if(checkSurrounding(row, column - 1, map)) {
                island.canReachShore = true;
                island.isVisited = true;
                return true;
            }

            //RIGHT
            if(checkSurrounding(row, column + 1, map)) {
                island.canReachShore = true;
                island.isVisited = true;
                return true;
            }

            //checkSurrounding(row + 1, column - 1, map);
            if(checkSurrounding(row + 1, column, map)) {
                island.canReachShore = true;
                island.isVisited = true;
                return true;
            }


        }
        return false;
    }

    private static boolean isInvalidRange(int row, int column, Island[][] map) {
        int numberOfRows = map.length;
        int numberOfCol = map[0].length;
        if (row < 0 || row >= numberOfRows) {
            return true;
        } else if (column < 0 || column >= numberOfCol) {
            return true;
        }
        return false;
    }

    /**
     * Verify the island is shore.
     */
    public static boolean isShore(int row, int column, Island[][] map) {
        int numberOfRows = map.length;
        int numberOfCol = map[0].length;
        if (row == 0 || row == (numberOfRows - 1)) {
            return true;
        } else if (column == 0 || column == (numberOfCol - 1)) {
            return true;
        }
        return false;
    }

}
