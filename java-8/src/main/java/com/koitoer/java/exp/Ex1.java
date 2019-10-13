package com.koitoer.java.exp;

import java.util.Arrays;

public class Ex1 {

    enum Direction {R, D, L, U}


    public static int[][] createSpiral(int n) {
        if (n <= 0) {
            return new int[][] {};
        }
        if (n == 1) {
            return new int[][] { { 1 } };
        }

        int result[][] = new int[n][n];

        int pos[] = new int[] { 0, 0 };
        int current[] = new int[] { 1 };
        Direction nextDirection = Direction.R;
        while (true) {
            nextDirection = addElement(pos, result, current, nextDirection, n);
            if (current[0] == n * n + 1) {
                break;
            }
        }

        return result;
    }

    private static Direction addElement(int[] pos, int[][] result, int[] current, Direction nextDirection, int n) {
        int currentRow = pos[0];
        int currentColumn = pos[1];
        int i = current[0];

        switch (nextDirection) {
        case R:
            //If next column is empty and within the range
            if (currentColumn < n && result[currentRow][currentColumn] == 0) {
                result[currentRow][currentColumn] = i;
                pos[1] = currentColumn + 1;
                current[0] = i + 1;
                return Direction.R;
            } else {
                pos[0] = currentRow + 1;
                pos[1] = currentColumn - 1;
                return Direction.D;
            }
        case D:
            //If next rox is empty and within the range
            if (currentRow <= n-1 && result[currentRow][currentColumn] == 0) {
                result[currentRow][currentColumn] = i;
                pos[0] = currentRow + 1;
                current[0] = i + 1;
                return Direction.D;
            } else {
                pos[0] = currentRow - 1;
                pos[1] = currentColumn - 1;
                return Direction.L;
            }
        case L:

            //If next column is empty and within the range
            if (currentColumn >= 0 && result[currentRow][currentColumn] == 0) {
                result[currentRow][currentColumn] = i;
                pos[1] = currentColumn - 1;
                current[0] = i + 1;
                return Direction.L;
            } else {
                pos[0] = currentRow - 1;
                pos[1] = currentColumn + 1;
                return Direction.U;
            }
        case U:
            //If next rox is empty and within the range
            if (currentRow >= 0 && result[currentRow][currentColumn] == 0) {
                result[currentRow][currentColumn] = i;
                pos[0] = currentRow - 1;
                current[0] = i + 1;
                return Direction.U;
            } else {
                pos[0] = currentRow + 1;
                pos[1] = currentColumn + 1;
                return Direction.R;
            }
        default:
            throw new IllegalStateException("s");
        }
    }

    public static void main(String args[]) {

         validate(new int[][] {}, 0);
         validate(new int[][] {}, -1);
         validate(new int[][] { { 1 } }, 1);
         validate(new int[][] {
         { 1, 2 },
         { 4, 3 } },
         2);

        validate(new int[][] {
                { 1, 2, 3 },
                { 8, 9, 4 },
                { 7, 6, 5 } },
            3);

        validate(new int[][] {
                { 1, 2, 3, 4 },
                { 12, 13, 14, 5 },
                { 11, 16, 15, 6 },
                { 10, 9, 8, 7 } },
            4);
        validate(new int[][] {
                { 1, 2, 3, 4, 5 },
                { 16, 17, 18, 19, 6 },
                { 15, 24, 25, 20, 7 },
                { 14, 23, 22, 21, 8 },
                { 13, 12, 11, 10, 9 } },
            5);
        validate(new int[][] {
                { 1, 2, 3, 4, 5, 6 },
                { 20, 21, 22, 23, 24, 7 },
                { 19, 32, 33, 34, 25, 8 },
                { 18, 31, 36, 35, 26, 9 },
                { 17, 30, 29, 28, 27, 10 },
                { 16, 15, 14, 13, 12, 11 } },
            6);
        validate(new int[][] {
                { 1, 2, 3, 4, 5, 6, 7 },
                { 24, 25, 26, 27, 28, 29, 8 },
                { 23, 40, 41, 42, 43, 30, 9 },
                { 22, 39, 48, 49, 44, 31, 10 },
                { 21, 38, 47, 46, 45, 32, 11 },
                { 20, 37, 36, 35, 34, 33, 12 },
                { 19, 18, 17, 16, 15, 14, 13 } },
            7);
        System.out.println("Success!");
    }

    private static void validate(int[][] expectedArray, int n) {
        if (!Arrays.deepEquals(expectedArray, createSpiral(n))) {
            System.out.println("Failed at n=" + n + "\nExpected: " + Arrays.deepToString(expectedArray)
                + "\nActual: " + Arrays.deepToString(createSpiral(n)));
            System.exit(1);
        }
    }

}
