package com.koitoer.java.let.numbers;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 7. Reverse Integer
 * The approach is use a long to store the variable if is max > Integer.MAX or min < Integer.MIN
 * we will return o, otherwise we save the sign.
 */
public class Solution7 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution7().reverse(123)).isEqualTo(321);
        Assertions.assertThat(new Solution7().reverse(-123)).isEqualTo(-321);
        Assertions.assertThat(new Solution7().reverse(153423646)).isEqualTo(646324351);
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution7().reverse2(-123)).isEqualTo(-321);
        Assertions.assertThat(new Solution7().reverse2(123)).isEqualTo(321);
        Assertions.assertThat(new Solution7().reverse2(153423646)).isEqualTo(646324351);
    }

    /**
     * Basically using like a stack solution approach.
     * We will pop the first element and store.
     * Decrease the size dividing by 10
     * then each iteration multiply by 10 and add the other part.
     */
    public int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public int reverse(int x) {

        boolean negative = x < 0;
        String s = Integer.toString(Math.abs(x));
        long result = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = Character.getNumericValue(s.charAt(i));
            result += c * (Math.pow(10, i));
        }

        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }

        return negative ? (int) result * -1 : (int) result;
    }
}
