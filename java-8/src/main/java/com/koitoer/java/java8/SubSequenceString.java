package com.koitoer.java.java8;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SubSequenceString {

    //"a", "ap", "al", "ae", "app", "apl", "ape", "ale", "appl", "appe", "aple",
    //"apple", "p", "pp", "pl", "pe", "ppl", "ppe", "ple", "pple", "l", "le", "e", "".

    @Test
    public void testSubstring() {
        Set<String> set = new SubSequenceString().subsequence("abcabcabcabc");
        int a = new SubSequenceString().longestPalindromeSubseq("abcabcabcabc");
        System.out.println(set.size());
    }

    public int longestPalindromeSubseq(String s) {
        int maximum = -1;
        Set<String> subseQuences = subsequence(s);
        System.out.println(subseQuences);
        for (String a : subseQuences) {
            if (isPalindrome(a)) {
                maximum = Math.max(maximum, a.length());
            }
        }

        return maximum;
    }

    public Set<String> subsequence(String s) {
        Set<String> set = new HashSet<>();
        for (int p = 0; p <= s.length(); p++) {
            String newString = s.substring(p, s.length());
            // System.out.println("newString -> " + newString);
            for (int i = 0; i < newString.length(); i++) {
                String first = newString.substring(0, i + 1);
                set.add(first);
                // System.out.println("F -> " + first + " : ");
                for (int j = i + 1; j < newString.length(); j++) {
                    String second = newString.substring(j, j + 1);
                    //System.out.println("    FIN -> " + second + " : ");
                    set.add(first.concat(second));
                }
            }
        }
        return set;
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        // reverse the given String
        String reverse = new StringBuffer(s).reverse().toString();

        // check whether the string is palindrome or not
        if (s.equals(reverse)) {
            return true;
        } else {
            return false;
        }
    }
}
