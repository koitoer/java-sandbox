package com.koitoer.java.java8;

import org.assertj.core.api.Assertions;

public class Record2 {

    public static void main(String[] args) {
        new Record2().longestPalindromeSubseq("bbbab");

        //new Record2().longestPalindromeSubseq("bcbcbcbc");

        Assertions.assertThat(new Record2().isPalindrome("abcabcabcabc")).isTrue();
    }

    public int longestPalindromeSubseq(String s) {

        int maximum = -1;

        if (isPalindrome(s)) {
            maximum = Math.max(maximum, s.length());
        }

        for (int i = 0; i < s.length(); i++) {

            String aux = s.substring(0, i);
            String aux2 = s.substring(i + 1);

            String aux3 = "";
            try {
                aux3 = aux2.substring(1);
            } catch (StringIndexOutOfBoundsException e) {

            }
            String newS = aux + aux2;
            System.out.println("mysTring ->  " + newS);

            if (true) {
                for (int p = 0; p < newS.length(); p++) {
                    for (int q = p; q <= newS.length(); q++) {
                        String aux4 = newS.substring(p, q);
                        //System.out.println("        aux ->  " + aux4);
                        if (aux4.length() == 7) {
                            System.out.println("777 ->  " + aux4);

                        }
                        if (isPalindrome(aux4)) {
                            ///System.out.println("                p ->  " + aux4);

                            maximum = Math.max(maximum, aux4.length());
                        }
                    }
                }
            }

        }

        System.out.println(maximum);
        return maximum;
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

/**
 * //System.out.println("string -> " + s);
 * for (int i = 0; i < s.length(); i++) {
 * int start = i;
 * int fin = s.length() - 1 - i;
 * char a = s.charAt(start);
 * char b = s.charAt(fin);
 * if (a == b) {
 * continue;
 * } else {
 * return false;
 * }
 * }
 * return true;
 * }
 **/

