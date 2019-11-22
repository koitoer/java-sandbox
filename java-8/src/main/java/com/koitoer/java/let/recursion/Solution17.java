package com.koitoer.java.let.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 17. Letter Combinations of a Phone Number
 * Do this using backtracking. Add one element exhaust possibilities and return.
 */
public class Solution17 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution17().letterCombinations("23"))
            .containsOnly("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList();
        createResults(digits, 0, "", result);
        return result;
    }

    private void createResults(String digits, int index, String prefix, List<String> result) {

        if (prefix.length() == digits.length()) {
            result.add(prefix);
            return;
        }

        List<String> values = getCharFromNumber(digits.charAt(index));
        for (int i = 0; i < values.size(); i++) {
            String p = values.get(i);
            createResults(digits, index + 1, prefix + p, result);
        }
    }

    private List<String> getCharFromNumber(char a) {
        switch (a) {
        case '2':
            return Arrays.asList("a", "b", "c");
        case '3':
            return Arrays.asList("d", "e", "f");
        case '4':
            return Arrays.asList("g", "h", "i");
        case '5':
            return Arrays.asList("j", "k", "l");
        case '6':
            return Arrays.asList("m", "n", "o");
        case '7':
            return Arrays.asList("p", "q", "r", "s");
        case '8':
            return Arrays.asList("t", "u", "v");
        case '9':
            return Arrays.asList("w", "x", "y", "z");
        default:
            return new ArrayList<>();
        }
    }
}

