package com.koitoer.java.let.matrix;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution200 {

    @Test
    public void test() {
        char[][] map = new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
            { '0', '0', '0', '1', '1' } };
        Assertions.assertThat(new Solution200().numIslands(map)).isEqualTo(3);

    }

    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int counter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    counter++;
                    visit(grid, i, j, visited);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            System.out.println();
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(visited[i][j] + " :");
            }
        }

        return counter;
    }

    private void visit(char[][] grid, int row, int column, boolean[][] visited) {

        if (row >= grid.length || column >= grid[0].length || row < 0 || column < 0) {
            return;
        }

        if (visited[row][column] == true) {
            return;
        }

        if (grid[row][column] == '0') {
            return;
        }

        visited[row][column] = true;
        visit(grid, row + 1, column, visited);
        visit(grid, row - 1, column, visited);
        visit(grid, row, column + 1, visited);
        visit(grid, row, column - 1, visited);

    }
}
