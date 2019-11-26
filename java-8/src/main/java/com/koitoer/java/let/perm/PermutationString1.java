package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PermutationString1 {

    @Test
    public void test() {
        String word = "boat";
        List<String> permutations = new PermutationString1().getPerm(word);
        System.out.println(permutations);
    }

    public List<String> getPerm(String str) {

        //Case when string is null.
        if (str == null) {
            return null;
        }

        //Case when string is empty string
        List<String> permutations = new ArrayList<>();
        if (str.length() == 0) {
            permutations.add("");
            return permutations;
        }

        //Get the first character
        char first = str.charAt(0);

        //Get the remainder characters
        String remainder = str.substring(1);

        //Permute the remainder
        List<String> words = getPerm(remainder);

        //For each word returned
        for (String w : words) {
            //We will insert the first char in all the positions
            for (int j = 0; j <= w.length(); j++) {
                String s = insertCharAt(w, first, j);
                //Add to the string.
                permutations.add(s);
            }
        }

        return permutations;
    }

    private String insertCharAt(String word, char c, int j) {
        String start = word.substring(0, j);
        String end = word.substring(j);
        return start + c + end;
    }
}
