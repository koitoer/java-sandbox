package com.koitoer.training.exploringWaters;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by mmena on 2/28/18.
 */
public class ArrayChange {

    int arrayChange(int[] inputArray) {
        int counter = 0;
        for(int i =0 ; i< inputArray.length-1 ; i++){
            if(inputArray[i] >= inputArray[i+1]){
                int number = inputArray[i] +1;
                int sum = number - inputArray[i+1];
                inputArray[i+1] = number;
                counter = counter + sum;
            }
        }
        return counter;
    }

    @Test
    public void test(){
        Assertions.assertThat(new ArrayChange().arrayChange(new int[]{-1000,0,-2,0})).isEqualTo(5);
        Assertions.assertThat(new ArrayChange().arrayChange(new int[]{1,1,1})).isEqualTo(3);
    }

}
