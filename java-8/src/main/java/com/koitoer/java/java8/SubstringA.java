package com.koitoer.java.java8;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SubstringA {

    /**
     * t m m z u x t
     * 1 2 3
     * * * 1 2 3 4 5
     */
    @Test
    public void test1() {
        Assertions.assertThat(lengthOfLongestSubstring("pwwkew")).isEqualTo(3).as("testA");
        Assertions.assertThat(lengthOfLongestSubstring("aab")).isEqualTo(2).as("test2");
        Assertions.assertThat(lengthOfLongestSubstring("dvdf")).isEqualTo(3).as("test3");
        Assertions.assertThat(lengthOfLongestSubstring("tmmzuxt")).isEqualTo(5).as("test3");
    }

    /**
     * u q i n n t q
     * 1 2 3 4
     * * * * * 1 2 3
     */
    @Test
    public void test2() {
        Assertions.assertThat(lengthOfLongestSubstring2("uqinntq")).isEqualTo(4).as("test2");
    }

    /**
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
     * s x h k b b c d f x n  m  q  k  a  t"
     * 1 2 3 4 5
     * * * * * * 1 2 3 4 5 6  7  8  9 10 11
     */
    @Test
    public void test3() {
        Assertions.assertThat(lengthOfLongestSubstring("sxhkbbcdfxnmqkat")).isEqualTo(11).as("test2");
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap();
        char[] chars = s.toCharArray();
        int replacement = 0;
        int maximum = 0;
        int aux = 0;
        for (int i = 0; i < chars.length; i++) {
            Integer pos = map.get(chars[i]);
            map.put(Character.valueOf(chars[i]), Integer.valueOf(i));

            //Not a repeated element
            if (pos == null) {
                aux = aux + 1;
            } else if (pos < replacement) {
                aux = aux + 1;
            } else {
                aux = i - pos;
                replacement = pos;
            }

            maximum = Math.max(aux, maximum);
        }
        return maximum;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, max = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch) && map.get(ch) >= start) {
                start = map.get(ch) + 1;
            }
            map.put(ch, i);
            int len = i - start + 1;
            max = Math.max(max, len);
        }

        return max;
    }

}
