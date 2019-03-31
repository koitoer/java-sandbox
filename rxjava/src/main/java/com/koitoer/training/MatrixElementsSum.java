package com.koitoer.training;

import org.assertj.core.api.Assertions;

/**
 * Created by mmena on 2/23/18.
 */
public class MatrixElementsSum {

    public static void main(String[] args) {
        int [][]matrix = new int[][]{
            {0, 1, 1, 2},
            {0, 5, 0, 0},
            {2, 0, 3, 3}};

        Assertions.assertThat(new MatrixElementsSum().matrixElementsSum(matrix)).isEqualTo(9);

    }

    int matrixElementsSum(int[][] matrix) {
        int sum =0;
        int rows = matrix.length;
        int columns = matrix[0].length;
        System.out.println("Rows " + rows + " : Col " + columns);
        for(int colIndex =0; colIndex < matrix[0].length ; colIndex++){
            for(int rowIndex=0; rowIndex< matrix.length ; rowIndex++){
                int currentValue = matrix[rowIndex][colIndex];
                System.out.println("Position row " + rowIndex + " : col " + colIndex);

                System.out.println("Checking " + currentValue);
                if(currentValue == 0){
                    break;
                }else{
                    sum += currentValue;
                }
            }
        }
        return sum;
    }
}
