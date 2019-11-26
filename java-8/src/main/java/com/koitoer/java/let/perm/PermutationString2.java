package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PermutationString2 {

    @Test
    public void test() {
        String word = "boat";
        List<String> permutations = new PermutationString2().getPerms(word);
        System.out.println(permutations);
    }

    private List<String> getPerms(String str) {
        List<String> result = new ArrayList<>();
        getPerm("", str, result);
        return result;
    }

    private void getPerm(String prefix, String remainder, List<String> result) {
        if (remainder.length() == 0) {
            result.add(prefix);
        }
        int len = remainder.length();
        for (int i = 0; i < len; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1, len);
            char c = remainder.charAt(i);
            getPerm(prefix + c, before + after, result);
        }
    }

}
