package com.koitoer.java.algorithm;

import org.assertj.core.api.Assertions;

/**
 * https://leetcode.com/problems/unique-paths/
 */
public class Solution62 {

    public static void main(String[] args) {
        Assertions.assertThat(new Solution62().uniquePaths(2,4)).isEqualTo(4);
        Assertions.assertThat(new Solution62().uniquePaths(3,7)).isEqualTo(28);
    }

    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];

        if (m == 1 || n == 1)
            return 1;
        return back(0, 0, m - 1, n - 1, dp);
    }

    public int back(int row, int col, int maxRow, int maxCol, int[][] dp) {

        if (row > maxRow || col > maxCol) {
            return 0;
        }
        if (row == maxRow && col == maxCol) {
            return 1;
        }

        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        int right = back(row + 1, col, maxRow, maxCol, dp);
        int down = back(row, col + 1, maxRow, maxCol, dp);

        dp[row][col] = down + right;

        return dp[row][col];
    }

}
