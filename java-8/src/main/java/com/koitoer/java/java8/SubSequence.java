package com.koitoer.java.java8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SubSequence {

    @Test
    public void test() {
        int max = new SubSequence().longestPalindromeSubseq3 ("abcabcabcabc");
        System.out.println(max);
    }


        public int longestPalindromeSubseq3(String str) {
            return LPS(str, 0, str.length() - 1, new HashMap<String, Integer>());
        }

        private static int LPS(String str, int start, int end, HashMap<String, Integer> cache) {
            String key = start + " " + end;
            System.out.println(key);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            int result;
            if (start > end) {
                result = 0;
            } else if (start == end) {
                result = 1;
            } else if (str.charAt(start) == str.charAt(end)) {
                result = 2 + LPS(str, start + 1, end - 1, cache);
            } else {
                result = Math.max(LPS(str, start + 1, end, cache), LPS(str, start, end - 1, cache));
            }
            cache.put(key, result);
            return result;
        }



    public int longestPalindromeSubseq2(String s) {
        return lps(s, 0, s.length()-1);
    }
    private int lps(String s, int i, int j){
        if(i > j)
            return 0;
        if(i == j)
            return 1;
        if(s.charAt(i) == s.charAt(j))
            return 2+lps(s, i+1, j-1);
        return Math.max(lps(s, i+1, j), lps(s, i, j-1));
    }


    public int longestPalindromeSubseq(String s) {
        Set<String> set = new HashSet<>();
        for(int i= 0; i< s.length() ; i++){
            for(int j = s.length() ; j > 0 ; j--){
                String a = s.substring(i);
                String b = null;
                try {
                    b = s.substring(i, j);
                }catch (Exception e){

                }
                if(b != null && b.length() > 0){
                    set.add(b);
                    for(int k = 0 ; k < b.length() ; k++){
                        String f = removeCharAt(b, k);
                        System.out.println("A -> " + a + " : B " + b + ":  F  -> " + f);
                        if(f != null && f.length() > 0) {
                            set.add(f);
                        }

                    }
                }
            }
        }


        int maximum = -1;

        for(String a : set) {
            if (isPalindrome(a)) {
                maximum = Math.max(maximum, a.length());
            }
        }


        System.out.println(set);
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

    public static String removeCharAt(String str, int index) {

        // The part of the String before the index:
        String str1 = str.substring(0,index);

        // The part of the String after the index:
        String str2 = str.substring(index+1,str.length());

        // These two parts together gives the String without the specified index
        return str1+str2;

    }


    //"a", "ap", "al", "ae", "app", "apl", "ape", "ale", "appl", "appe", "aple", "apple", "p", "pp", "pl", "pe", "ppl", "ppe", "ple", "pple", "l", "le", "e", "".
}