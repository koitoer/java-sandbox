package com.koitoer.training;

import org.assertj.core.api.Assertions;

/**
 * Created by mmena on 2/23/18.
 */
public class IsLucky {

    public static void main(String[] args) {
        Assertions.assertThat(new IsLucky().isLucky(1230)).isTrue();
        Assertions.assertThat(new IsLucky().isLucky(239017)).isFalse();
    }

    boolean isLucky(int n) {
        int sum = 0;
        char[] numbers = String.valueOf(n).toCharArray();
        for (int i = 0; i < numbers.length; i++) {
            int half =(numbers.length / 2) - 1;
            if (i <= half) {
                sum += Character.getNumericValue(numbers[i]);
            }else {
                sum -= Character.getNumericValue(numbers[i]);
            }
        }

        return sum == 0;
    }

}
