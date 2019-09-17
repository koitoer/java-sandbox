package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution922a {

    @Test
    public void test1() {
        int a[] = { 4, 2, 5, 7 };
        Assertions.assertThat(new Solution922a().sortArrayByParityII(a)).containsExactly(new int[] { 4, 5, 2, 7 });
    }

    public int[] sortArrayByParityII(int[] A) {
        int j = 1;

        //Iterate over the array in interval of 2
        for (int i = 0; i < A.length; i += 2)

            //If is odd
            if (A[i] % 2 == 1) {

                //And if still odd change the numbers.
                while (A[j] % 2 == 1)
                    j += 2;

                // Swap A[i] and A[j]
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

        return A;
    }
}
