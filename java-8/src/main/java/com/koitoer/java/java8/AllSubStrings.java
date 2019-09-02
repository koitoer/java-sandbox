package com.koitoer.java.java8;

import java.util.HashSet;

public class AllSubStrings {
    //hashset to keep a record of all the substrings
    static HashSet<String> subStrings_r=new HashSet<>();

    public static void main(String[] args) {
        String testString="abcabcabcabc";
        getSubstrings_r(testString);
        System.out.println("RECURSION ->"+subStrings_r);

        int maximum = -1;

        for(String a : subStrings_r) {
            if (isPalindrome(a)) {
                maximum = Math.max(maximum, a.length());
            }
        }
        System.out.println(maximum);
    }

    public static void getSubstrings_r(String testString){
        _getSubstrings_r(testString, 0, testString.length());
    }

    public static void _getSubstrings_r(String testString,int start,int end){
        if(start==end){ //base condition
            return;
        }
        subStrings_r.add(testString.substring(start, end));
        //start getting substrings from left to right
        _getSubstrings_r(testString,start+1,end);
        //start getting substrings from right to left
        _getSubstrings_r(testString,start,end-1);
    }


    public static boolean isPalindrome(String s) {
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
