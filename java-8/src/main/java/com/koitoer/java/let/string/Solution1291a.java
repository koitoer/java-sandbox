package com.koitoer.java.let.string;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 1291. Sequential Digits
 * We use an list to store the result.
 * Two nested loops , the first one start in i = 1 and the second j = i
 * And the formula [r = r * 10 + j];
 * r * 10, take the previous and move one decimal place so when we add is like we are concatenating numbers
 */
public class Solution1291a {

    @Test
    public void test() {
        Assertions.assertThat(new Solution1291a().sequentialDigits(100, 300))
            .contains(123, 234);
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution1291a().sequentialDigits(1000, 13000))
            .containsOnly(1234, 2345, 3456, 4567, 5678, 6789, 12345);
    }

    @Test
    public void test3() {
        Assertions.assertThat(new Solution1291a().sequentialDigits(10, 1000000000))
            .containsOnly(12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345, 3456, 4567, 5678, 6789, 12345,
                23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789, 1234567, 2345678, 3456789, 12345678, 23456789, 123456789);
    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            int r = 0;
            for (int j = i; j < 10; j++) {
                r = r * 10 + j;
                if (r >= low && r < high) {
                    result.add(r);
                }
            }
        }

        return result;
    }

}
