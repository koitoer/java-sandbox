package com.koitoer.java.let.string;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * EASY - MUST
 * 13. Roman to Integer
 * We need to iterate over the full string taking one element at the time.
 * However if we see the value of the next element is greater than the current value we need to do a substraction.
 * Otherwise we just add the value to the result.
 * This use map for getting the values of each symbol.
 */
public class Solution13 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution13().romanToInt("III")).isEqualTo(3);
        Assertions.assertThat(new Solution13().romanToInt("IV")).isEqualTo(4);
        Assertions.assertThat(new Solution13().romanToInt("MCMXCIX")).isEqualTo(1999);
    }

    public int romanToInt(String s) {
        Map<Character, Integer> values = new HashMap();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        int result = 0;
        int p = 0;
        //Iterate over the string one by one
        while (p < s.length()) {
            //Get the char of the current variable
            char charT = s.toCharArray()[p];
            //If the next one is inside the boundaries
            //And the next value is greater than the previous do a subtraction
            if (p + 1 < s.length() && values.get(charT) < values.get(s.toCharArray()[p + 1])) {
                result += values.get(s.toCharArray()[p + 1]) - values.get(charT);
                p += 2;
                //Otherwise just do the math.
            } else {
                result += values.get(charT);
                p++;
            }
        }
        return result;
    }
}
