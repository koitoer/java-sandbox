package com.koitoer.java.let.numbers;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 204. Count Primes
 */
public class Solution204 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution204().countPrimes(20)).isEqualTo(8);
    }

    public int countPrimes(int n) {

        int[] isPrime = new int[n + 1];

        int count = 0;
        //First number reduction from i to  i * i < n  each i++
        for (int i = 2; i * i < n; i++) {

            //We check if the number has been marked as a non-prime to avoid iterate.
            if (isPrime[i] == 0) {
                //To all the i * i -> i * i + i -> i * i + 2i, we marked as non prime
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = -1;
                }
            }
        }

        //Iterate over the array and all the values in zero count as prime numbers
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 0) {
                count++;
            }
        }
        return count;
    }
}
