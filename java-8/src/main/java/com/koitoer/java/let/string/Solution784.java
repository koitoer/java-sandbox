package com.koitoer.java.let.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 784. Letter Case Permutation
 * The problem is the conversion between string and char.
 * We can optimize this using the logic upperCase char is between 65-90 and lowerCase 97-122
 * 48-57 are digits.
 */
public class Solution784 {

    @Test
    public void test1() {
        new Solution784().letterCasePermutation("a1b2");
    }

    public List<String> letterCasePermutation(String S) {
        List<String> r = new ArrayList<>();
        permuteChange(S, "", 0, r);
        return r;
    }

    private void permuteChange(String s, String c, int index, List<String> r) {

        if (c.length() == s.length()) {
            r.add(c);
            return;
        }

        char a = s.charAt(index);
        permuteChange(s, c + a, index + 1, r);
        char lower = Character.toLowerCase(a);
        char upper = Character.toUpperCase(a);

        if (!(a >= 48 && a <= 57)) { // Character.isDigit(a);
            if (upper == a) {
                permuteChange(s, c + lower, index + 1, r);
            }
            if (lower == a) {
                permuteChange(s, c + upper, index + 1, r);
            }
        }
    }
}
