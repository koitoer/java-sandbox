package com.koitoer.java.java8;

import java.util.HashSet;
import java.util.Set;

public class SubSeq {

    public static void main(String[] args) {
        new SubSeq().longestPalindromeSubseq("bbbab");
    }

    public int longestPalindromeSubseq(String s) {

        int maximum = -1;

        Set<String> set = subsequence(s);
        System.out.println(set);
        for (String a : set) {
            if (isPalindrome(a)) {
                maximum = Math.max(maximum, a.length());
            }
        }

        return maximum;
    }

    // It computes all the subsequence of an string
    public Set<String> subsequence(String str) {
        HashSet<String> st = new HashSet<>();

        // iterate over the entire string
        for (int i = 0; i <= str.length(); i++) {

            // iterate from the end of the string
            // to generate substrings
            for (int j = str.length(); j > i; j--) {
                String sub_str = str.substring(i, j);

                if (!st.contains(sub_str))
                    st.add(sub_str);

                // drop kth character in the substring
                // and if its not in the set then recur
                for (int k = 0; k < sub_str.length() - 1; k++) {
                    StringBuffer sb = new StringBuffer(sub_str);

                    // drop character from the string
                    sb.deleteCharAt(k);
                   // if (!st.contains(sb))
                       // subsequence(sb.toString());
                }
            }
        }
        return st;

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
