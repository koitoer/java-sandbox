package com.koitoer.training;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by mmena on 2/26/18.
 */
public class SortByHeight {

    @Test
    public void test() {

        Assertions.assertThat(SortByHeight.sort(new int[] { 2, 3, 1, 4 })).isEqualTo(new int[] { 1,2,3,4});

        Assertions.assertThat(SortByHeight.sortByHeight(new int[] { -1, 150, 190, 170, -1, -1, 160, 180 }))
            .isEqualTo(new int[] { -1, 150, 160, 170, -1, -1, 180, 190 });
        Assertions.assertThat(SortByHeight.sortByHeight(new int[] { -1, -1, -1, -1, -1 }))
            .isEqualTo(new int[] { -1, -1, -1, -1, -1 });
    }

    private static int[] sort(int[] ints) {
        for(int i =0 ; i<ints.length ; i++){
            for(int j = 0 ; j<ints.length; j++){
                if(ints[i] < ints[j]){
                    int aux = ints[j];
                    ints[j] = ints[i];
                    ints[i] =aux;
                }
            }
        }
        return ints;
    }

    static int[] sortByHeight(int[] ints) {

        for(int i =0 ; i<ints.length ; i++){
            if(ints[i] == -1){
                continue;
            }
            for(int j = 0 ; j<ints.length; j++){

                if(ints[j] == -1){
                    continue;
                }

                if(ints[i] < ints[j]){
                    int aux = ints[j];
                    ints[j] = ints[i];
                    ints[i] =aux;
                }
            }
        }
        return ints;
    }

}
