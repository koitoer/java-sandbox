package com.koitoer.java.let.matrix;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution200a {

    @Test
    public void test() {
        char[][] map = new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
            { '0', '0', '0', '1', '1' } };
        Assertions.assertThat(new Solution200a().numIslands(map)).isEqualTo(3);

    }

    public int numIslands(char[][] grid) {
        int counter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    visit(grid, i, j);
                    counter++;
                }
            }
        }
        return counter;
    }

    private void visit(char[][] grid, int row, int column) {

        if (row >= grid.length || column >= grid[0].length || row < 0 || column < 0) {
            return;
        }


        if (grid[row][column] == '0') {
            return;
        }

        grid[row][column] = '0';
        visit(grid, row + 1, column);
        visit(grid, row - 1, column);
        visit(grid, row, column + 1);
        visit(grid, row, column - 1);

    }
}
