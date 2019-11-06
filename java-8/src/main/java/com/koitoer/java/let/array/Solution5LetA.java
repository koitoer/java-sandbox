package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 5. Longest Palindromic Substring
 * You use a matrix for dp solution.
 * The diagonal values are true and you start from there. in this case.
 * the initial and final values must be equals  + the previous substring must be a palindrome.
 * row == row + window && dp[row + 1][row + col - 1] == true
 */
public class Solution5LetA {

    @Test
    public void test1() {
        String a = "aba";
        Assertions.assertThat(new Solution5LetA().longestPalindrome(a)).isEqualTo("aba");

        String b = "ababa";
        Assertions.assertThat(new Solution5LetA().longestPalindrome(b)).isEqualTo("ababa");

        String c =
            "klvxwqyzugrdoaccdafdfrvxiowkcuedfhoixzipxrkzbvpusslsgfjocvidnpsnkqdfnnzzawzsslwnvvjyoignsfbxkgrokzyusxikxumrxlzzrnbtrixxfioormoyyejashrowjqqzifacecvoruwkuessttlexvdptuvodoavsjaepvrfvbdhumtuvxufzzyowiswokioyjtzzmevttheeyjqcldllxvjraeyflthntsmipaoyjixygbtbvbnnrmlwwkeikhnnmlfspjgmcxwbjyhomfjdcnogqjviggklplpznfwjydkxzjkoskvqvnxfzdrsmooyciwulvtlmvnjbbmffureoilszlonibbcwfsjzguxqrjwypwrskhrttvnqoqisdfuifqnabzbvyzgbxfvmcomneykfmycevnrcsyqclamfxskmsxreptpxqxqidvjbuduktnwwoztvkuebfdigmjqfuolqzvjincchlmbrxpqgguwuyhrdtwqkdlqidlxzqktgzktihvlwsbysjeykiwokyqaskjjngovbagspyspeghutyoeahhgynzsyaszlirmlekpboywqdliumihwnsnwjc";
        Assertions.assertThat(new Solution5LetA().longestPalindrome(c)).isEqualTo("wnsnw");
    }

    public String longestPalindrome(String s) {

        if (s.length() == 0) {
            return "";
        }

        boolean dp[][] = new boolean[s.length()][s.length()];

        //initialize array values to true for all substrings of length 1 and 0 (the i-1 term)
        for (int i = 0; i < dp.length; i++)
            for (int j = Math.max(0, i - 1); j <= i; j++)
                dp[i][j] = true;

        //this will be the largest palindrome if the loop finds nothing
        String ans = "" + s.charAt(0);
        int maxD = 0;

        //We start col from 1 instead of zero
        for (int col = 1; col < dp.length; col++) {
            //Rows are normal from zero to row + col
            for (int row = 0; row + col < dp.length; row++) {

                //Verify the letter are equal the
                if (s.charAt(row) == s.charAt(row + col) && dp[row + 1][row + col - 1]) {
                    dp[row][row + col] = true;

                    if (maxD < col) {
                        ans = s.substring(row, row + col + 1);
                        maxD = col;
                    }

                }
            }
        }

        return ans;
    }

    private void printMatrix(boolean[][] dp) {
        System.out.println("M -> ");
        for (int i = 0; i < dp.length; i++) {
            System.out.println();
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j] + " : ");
            }
        }
    }

}