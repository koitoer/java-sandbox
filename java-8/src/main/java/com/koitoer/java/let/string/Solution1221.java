package com.koitoer.java.let.string;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 1221. Split a String in Balanced Strings
 */
public class Solution1221 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution1221().balancedStringSplit("RLRRLLRLRL")).isEqualTo(4);
        Assertions.assertThat(new Solution1221().balancedStringSplit("RLLLLRRRLR")).isEqualTo(3);
        Assertions.assertThat(new Solution1221().balancedStringSplit("LLLLRRRR")).isEqualTo(1);
    }

    public int balancedStringSplit(String s) {

        int count = 0;
        int result = 0;

        for (char c : s.toCharArray()) {
            if (c == 'L') {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                result++;
            }
        }

        return result;

    }

}
