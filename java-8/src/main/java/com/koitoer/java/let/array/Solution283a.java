package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 283. Move Zeroes
 * Two pointer problem.
 * O(n). One pointer will always point to the zero values and the other to the non-zero values
 * We will use swap.
 */
public class Solution283a {

    @Test
    public void test2() {
        int a[] = new int[] { 4, 2, 4, 0, 0, 3, 0, 5, 1, 0 };
        new Solution283a().moveZeroes(a);
        Assertions.assertThat(a).isEqualTo(new int[] { 4, 2, 4, 3, 5, 1, 0, 0, 0, 0 });
    }

    @Test
    public void test() {
        int a[] = new int[] { 0, 1, 0, 3, 12 };
        new Solution283a().moveZeroes(a);
        Assertions.assertThat(a).isEqualTo(new int[] { 1, 3, 12, 0, 0 });

        int b[] = new int[] { 0, 1 };
        new Solution283a().moveZeroes(b);
        Assertions.assertThat(b).isEqualTo(new int[] { 1, 0 });

        int c[] = new int[] { 0, 0 };
        new Solution283a().moveZeroes(c);
        Assertions.assertThat(c).isEqualTo(new int[] { 0, 0 });

        int d[] = new int[] { 0 };
        new Solution283a().moveZeroes(d);
        Assertions.assertThat(d).isEqualTo(new int[] { 0 });

        int e[] = new int[] { 1, 1 };
        new Solution283a().moveZeroes(e);
        Assertions.assertThat(e).isEqualTo(new int[] { 1, 1 });
    }

    public void moveZeroes(int[] a) {
        int n = a.length;

        if (n <= 1) {
            return;
        }

        int pnz = 0;
        int pz = 0;

        //Iterate until the last element to verify if they are zero or not zero.
        while (pnz < a.length) {
            int vnz = a[pnz];
            int za = a[pz];

            //If pnz is pointing to a non-zero value
            if (vnz != 0) {

                //If pointer to zero is already pointing to a zero, then swap element.
                if (za == 0) {
                    swap(a, pz, pnz);
                }

                pnz++;
                pz++;

            //Case non zero pointer point to a zero value we will forward until we found a non zero.
            } else {
                while (pnz < a.length && a[pnz] == 0) {
                    pnz++;
                }
            }

        }

    }

    public void swap(int[] a, int from, int to) {
        int aux = a[from];
        a[from] = a[to];
        a[to] = aux;
    }

}
