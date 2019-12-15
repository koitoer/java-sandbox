package com.koitoer.java.let.string;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 1291. Sequential Digits
 * We use an array of number, we also use the length of the number provided, then we just generate all the numbers,
 */
public class Solution1291 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution1291().sequentialDigits(100, 300))
            .contains(123, 234);
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution1291().sequentialDigits(1000, 13000))
            .containsOnly(1234, 2345, 3456, 4567, 5678, 6789, 12345);
    }

    @Test
    public void test3() {
        Assertions.assertThat(new Solution1291().sequentialDigits(10, 1000000000))
            .containsOnly(12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345, 3456, 4567, 5678, 6789, 12345,
                23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789, 1234567, 2345678, 3456789, 12345678, 23456789, 123456789);
    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        int start = (low + "").length();
        int end = (high + "").length();

        //Create numbers that have length between start and end inclusive
        for (int i = start; i <= end; i++) {
            //Start using 1 to the last digit 9
            for (int k = 0; k < numbers.length - 1; k++) {
                //Generate the number from nums[k] until the length is i
                Integer a = returnValue(numbers, k, i);
                //If the number created is high or out of index, break the cycle
                if (a > high || a == -1) {
                    break;
                }
                //If the number generated is between low and high value then add
                if (a >= low && a <= high) {
                    result.add(a);
                }
            }
        }

        return result;
    }

    private Integer returnValue(int[] numbers, int start, int maxLength) {
        //Avoid have ArrayIndexException
        if (start + maxLength > 9) {
            return -1;
        }

        String valueString = "";
        int valueDigit = 0;

        //From the start value append until we complete the length
        while (valueDigit < maxLength) {
            valueString += "" + numbers[start++];
            valueDigit++;
        }
        return Integer.valueOf(valueString);
    }

}
