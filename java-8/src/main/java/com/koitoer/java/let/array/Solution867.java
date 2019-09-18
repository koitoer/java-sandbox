package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 867. Transpose Matrix
 * Check edge cases when the dimension is 1
 * We just need to invert the result of m x n  will be n x m
 * Check the index does not get NPE outside of the ranges
 */
public class Solution867 {

    @Test
    public void test1(){
        Assertions.assertThat(new Solution867().transpose(new int[][]{{1,2,3},{4,5,6},{7,8,9}}))
            .isEqualTo(new int[][] {{1,4,7},{2,5,8},{3,6,9}});
    }

    @Test
    public void test2(){
        Assertions.assertThat(new Solution867().transpose(new int[][]{{3}}))
            .isEqualTo(new int[][]{{3}});
    }

    public int[][] transpose(int[][] A) {

        int rows = A.length;
        int cols = A[0].length;

        if(rows == 1 && cols == 1)
            return A;

        int[][] m = new int[cols][rows];

        for(int i = 0; i<cols; i++){
            for(int j = 0; j<rows; j++){
                m[i][j] = A[j][i];
            }
        }

        return m;
    }

}
