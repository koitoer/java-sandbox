package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution922 {

    @Test
    public void test1() {
        int a[] = { 4, 2, 5, 7 };
        Assertions.assertThat(new Solution922().sortArrayByParityII(a)).containsExactly(new int[] { 4, 5, 2, 7 });
    }

    public int[] sortArrayByParityII(int[] A) {
        int[] a = new int[A.length];
        int even = 0;
        int odd = 1;
        for (int i = 0; i < A.length; i++) {

            if (A[i] % 2 == 0) {
                a[even] = A[i];
                even = even + 2;
            } else {
                a[odd] = A[i];
                odd = odd + 2;
            }

        }

        return a;
    }
}
