package com.koitoer.java.java8;

public class PrintStringSubSequences {

    static int maximum = -1;

    public void printAllSubSequences(String input) {
        int[] temp = new int[input.length()];
        int index = 0;
        solve(input, index, temp);
    }

    private void solve(String input, int index, int[] temp) {
        if (index == input.length()) {
            print(input, temp);
            return;
        }
        //set the current index bit and solve it recursively
        temp[index] = 1;
        solve(input, index + 1, temp);
        //unset the current index bit and solve it recursively
        temp[index] = 0;
        solve(input, index + 1, temp);
    }

    private void print(String input, int[] temp) {
        String result = "";
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 1)
                result += input.charAt(i) + "";
        }
        if (result == "") {
            result = "{Empty Set}";
        }

        System.out.println(result);

        if (isPalindrome(result)) {
            maximum = Math.max(maximum, result.length());
        }

    }

    public static void main(String[] args) {
        String input = "cbbd";
        new PrintStringSubSequences().printAllSubSequences(input);
        System.out.println(maximum);
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

    public int longestPalindromeSubseq(String s) {
        new PrintStringSubSequences().printAllSubSequences(s);
        return maximum;
    }
}