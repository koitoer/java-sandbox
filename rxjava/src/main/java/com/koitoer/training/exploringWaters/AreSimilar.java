package com.koitoer.training.exploringWaters;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import scala.Int;

/**
 * Created by mmena on 2/28/18.
 */
public class AreSimilar {

    boolean areSimilar(int[] a, int[] b) {
        int firstDifferenceIndex = 0;
        int firstDifferenceI = 0;

        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                firstDifferenceIndex++;
                if (firstDifferenceIndex == 1) {
                    firstDifferenceI = i;
                }
            }

            if (firstDifferenceIndex >= 2) {
                int aux = a[i];
                a[i] = a[firstDifferenceI];
                a[firstDifferenceI] = aux;
                break;
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test() {
        Assertions.assertThat(new AreSimilar().areSimilar(new int[] { 1, 2, 3 }, new int[] { 2, 1, 3 })).isTrue();

        Assertions.assertThat(new AreSimilar().areSimilar(new int[] { 1, 1, 4 }, new int[] { 1, 2, 3 })).isFalse();

        Assertions.assertThat(new AreSimilar().areSimilar(new int[] { 4, 6, 3 }, new int[] { 3, 4, 6 })).isFalse();

    }

}
