package com.koitoer.java.let.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 784. Letter Case Permutation
 * The problem with this solution is that it took the complete remainSolution784
 */
public class Solution784a {

    @Test
    public void test1() {
        new Solution784a().letterCasePermutation("a1b2");
    }

    public List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        permuteChange(S, new char[S.length()], 0, list);
        return list;
    }

    private void permuteChange(String S, char[] result, int p, List<String> list) {
        if (p == S.length()) {
            System.out.println(new String(result));
            list.add(new String(result));
            return;
        }

        result[p] = S.charAt(p);

        permuteChange(S, result, p + 1, list);

        if(!Character.isDigit(S.charAt(p))) {

            if (Character.toString(S.charAt(p)).toUpperCase().equals(Character.toString(S.charAt(p)))) {
                result[p] = Character.toString(S.charAt(p)).toLowerCase().charAt(0);
                permuteChange(S, result, p + 1, list);
            }
            if (Character.toString(S.charAt(p)).toLowerCase().equals(Character.toString(S.charAt(p)))) {
                result[p] = Character.toString(S.charAt(p)).toUpperCase().charAt(0);
                permuteChange(S, result, p + 1, list);
            }
        }

    }
}


