package com.koitoer.java.let.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * The trick is use an array to store the code for each character in the first string.
 * Then you will window the second string counting the characters as well and finally compare each window.
 * Once you found a window that match the counter you are done.
 */
public class Solution567a {

    @Test
    public void test1() {
        Assert.assertFalse(checkInclusion("ab", "eidboaoo"));
        Assert.assertFalse(checkInclusion("hello", "ooolleoooleh"));
        Assert.assertTrue(checkInclusion("adc","dcda"));
    }

    public boolean checkInclusion(String s, String t) {
        int[] s1 = new int[26];

        for (char a : s.toCharArray()) {
            s1[a - 'a']++;
        }

        for (int i = 0; i < t.length() - s.length() + 1; i++) {
            int[] s2 = new int[26];
            for (int j = 0; j < s.length(); j++) {
                char a = t.charAt(i + j);
                s2[a - 'a']++;
            }

            if (match(s1, s2)) {
                return true;
            }
        }

       // System.out.println(s1);

        return false;
    }

    private boolean match(int[] s1, int[] s2) {
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }
        return true;
    }

}
