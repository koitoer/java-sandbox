package com.koitoer.training;

import java.util.ArrayList;
import java.util.Arrays;

import org.assertj.core.api.Assertions;

/**
 * Created by mmena on 2/23/18.
 */
public class AlmostIncrease {

    public static void main(String[] args) {
        AlmostIncrease almostIncrease = new AlmostIncrease();

        Assertions.assertThat(almostIncrease.verify(new Integer[] { 1, 2, 3 })).isTrue();
        Assertions.assertThat(almostIncrease.verify(new Integer[] { -1, 2, 3 })).isTrue();
        Assertions.assertThat(almostIncrease.verify(new Integer[] { -11, -12, -13 })).isFalse();
        Assertions.assertThat(almostIncrease.verify(new Integer[] { 0, 2, 5 })).isTrue();
        Assertions.assertThat(almostIncrease.verify(new Integer[] { -11, -13, -13 })).isFalse();
        Assertions.assertThat(almostIncrease.verify(new Integer[] { 2, 1, 3 })).isFalse();

        Assertions.assertThat(almostIncrease.almostIncreasingSequence(new int[] { 1, 3, 2, 1 })).isFalse();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence(new int[] { 1, 3, 2 })).isTrue();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence(new int[] { 1, 2, 1, 2 })).isFalse();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence(new int[] { 1, 4, 10, 4, 2 })).isFalse();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence(new int[] { 1, 2, 5, 3, 5 })).isTrue();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence(new int[] { 10, 1, 2, 3, 4, 5 })).isTrue();

        Assertions.assertThat(almostIncrease.almostIncreasingSequence2(new int[] { 1, 3, 2, 1 })).isFalse();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence2(new int[] { 1, 3, 2 })).isTrue();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence2(new int[] { 1, 2, 1, 2 })).isFalse();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence2(new int[] { 1, 4, 10, 4, 2 })).isFalse();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence2(new int[] { 1, 2, 5, 3, 5 })).isTrue();
        Assertions.assertThat(almostIncrease.almostIncreasingSequence2(new int[] { 10, 1, 2, 3, 4, 5 })).isTrue();
    }

    boolean almostIncreasingSequence2(int[] sequence) {
        int numErr = 0;
        for (int i = 0; i < sequence.length - 1; i++) {
            if (sequence[i] - sequence[i + 1] >= 0) {
                numErr += 1;
                if (i - 1 >= 0 && i + 2 <= sequence.length - 1
                    && sequence[i] - sequence[i + 2] >= 0
                    && sequence[i - 1] - sequence[i + 1] >= 0) {
                    return false;
                }
            }
        }

        return numErr <= 1;
    }

    private boolean almostIncreasingSequence3(int[] x) {
        for (int i = 0; i < x.length; i++) {
            if (isValid(i, x)) {
                return true;
            }
        }
        return false;

    }

    private boolean isValid(int i, int[] x) {
        int max = 0;
        boolean maxSelected = false;
        for (int j = 0; j < x.length; j++) {
            if (i == j) {
                continue;
            }
            int current = x[j];
            if (maxSelected) {
                if (max >= current) {
                    return false;
                } else {
                    max = current;
                }
            } else {
                maxSelected = true;
                max = current;
            }
        }

        return true;
    }

    private boolean verify(Integer[] arrayWithoutElement) {
        int maximum = arrayWithoutElement[0];
        for (int i = 1; i < arrayWithoutElement.length; i++) {
            if (maximum < arrayWithoutElement[i]) {
                maximum = arrayWithoutElement[i];
            } else {
                return false;
            }
        }
        return true;
    }

    boolean almostIncreasingSequence(int[] sequence) {
        if (sequence.length == 0) {
            return true;
        }

        boolean valid = false;
        for (int i = 0; i < sequence.length; i++) {
            Integer[] arrayWithoutElement = this.removeElement(i, sequence);
            boolean isArrayValid = this.verify(arrayWithoutElement);
            if (isArrayValid) {
                valid = true;
            }
        }

        return valid;
    }

    private Integer[] removeElement(int index, int[] a) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < a.length; i++) {
            if (index != i) {
                list.add(a[i]);
            }
        }
        return (Integer[]) list.toArray(new Integer[a.length - 1]);
    }

}
