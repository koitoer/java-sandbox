package com.koitoer.training.exploringWaters;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by mmena on 2/27/18.
 */
public class AlternatingSums {

    int[] alternatingSums(int[] a) {
        int[] result = new int[2];
        for(int i =0 ; i< a.length ; i++){
            result[i%2] += a[i];
        }
        return result;
    }


    @Test
    public void test() {
        Assertions.assertThat(new AlternatingSums().alternatingSums(new int[] { 100, 50}))
            .isEqualTo(new int[] { 100, 50 });
        Assertions.assertThat(new AlternatingSums().alternatingSums(new int[] { 50, 60, 60, 45, 70 }))
            .isEqualTo(new int[] { 180, 105 });

    }

}
