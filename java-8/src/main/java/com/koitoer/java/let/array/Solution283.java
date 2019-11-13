package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution283 {

    @Test
    public void test2() {
        int a[] = new int[] { 4, 2, 4, 0, 0, 3, 0, 5, 1, 0 };
        new Solution283().moveZeroes(a);
        Assertions.assertThat(a).isEqualTo(new int[] { 4, 2, 4, 3, 5, 1, 0, 0, 0, 0 });
    }

    @Test
    public void test() {
        int a[] = new int[] { 0, 1, 0, 3, 12 };
        new Solution283().moveZeroes(a);
        Assertions.assertThat(a).isEqualTo(new int[] { 1, 3, 12, 0, 0 });

        int b[] = new int[] { 0, 1 };
        new Solution283().moveZeroes(b);
        Assertions.assertThat(b).isEqualTo(new int[] { 1, 0 });

        int c[] = new int[] { 0, 0 };
        new Solution283().moveZeroes(c);
        Assertions.assertThat(c).isEqualTo(new int[] { 0, 0 });

        int d[] = new int[] { 0 };
        new Solution283().moveZeroes(d);
        Assertions.assertThat(d).isEqualTo(new int[] { 0 });

        int e[] = new int[] { 1, 1 };
        new Solution283().moveZeroes(e);
        Assertions.assertThat(e).isEqualTo(new int[] { 1, 1 });
    }

    public void moveZeroes(int[] a) {
        int n = a.length;

        if (n == 1) {
            return;
        }

        int p = firstNonZero(a);
        int z = firstZero(a);

        if (firstNonZero(a) == -1 || firstZero(a) == -1) {
            return;
        }

        while (p < n) {

            if (a[p] != 0) {
                swap(a, z, p);
                z = firstZero(a);

                while (true) {
                    p++;
                    if (p == n) {
                        break;
                    }
                    if (a[p] != 0) {
                        break;
                    }
                }

            }
        }
    }

    private int firstZero(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int firstNonZero(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                return i;
            }
        }
        return -1;
    }

    public void swap(int[] a, int from, int to) {
        int aux = a[from];
        a[from] = a[to];
        a[to] = aux;
    }

}
