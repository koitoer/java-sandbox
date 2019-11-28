package com.koitoer.java.let.array;

/**
 * 392. Is Subsequence
 * Use two pointer.
 * Iterate over the first string one time each time you found the same char in the other string.
 * if at some point your cursor is pointing to the same value of the length, is your stop condition where you reach the end and return
 * true, otherwise you must return false;
 */
public class Solution392 {

    public boolean isSubsequence(String s, String t) {
        if (s == null && t != null) {
            return true;
        }

        if (s.length() > t.length()) {
            return false;
        }

        int pa = 0;
        int pt = 0;
        while (pa < s.length() && pt < t.length()) {
            if (s.charAt(pa) == t.charAt(pt)) {
                pa++;
                pt++;

                if (pa == s.length()) {
                    return true;
                }
            } else {
                pt++;
            }
        }
        return false;
    }
}
