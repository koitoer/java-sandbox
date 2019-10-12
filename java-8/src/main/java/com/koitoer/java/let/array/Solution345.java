package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 345. Reverse Vowels of a String
 * Two pointer solution, move one to the left the other to the right.
 * The expensive process is identify the vowels, upperCase and lowerCase, you could build a boolean array to be faster
 * Don't use string translate everything to and array for faster retrieval access.
 */
public class Solution345 {

    @Test
    public void test1(){
        Assertions.assertThat(new Solution345().reverseVowels("leetcode")).isEqualTo("leotcede");
        Assertions.assertThat(new Solution345().reverseVowels("Mauricio")).isEqualTo("Moiricua");
    }

    public String reverseVowels(String s) {
        int c1 = 0;
        int c2 = s.length() - 1;
        char[] aux = s.toCharArray();

        while (c1 < c2) {
            boolean c1V = isVowel(aux[c1]);
            boolean c2V = isVowel(aux[c2]);
            if (c1V && c2V) {
                change(aux, c1, c2);
                c1++;
                c2--;
            } else if (c1V) {
                c2--;
            } else {
                c1++;
            }
        }

        return new String(aux);
    }

    private boolean isVowel(char aux) {
        char a = Character.toLowerCase(aux);
        if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u') {
            return true;
        }
        return false;
    }

    private void change(char[] a, int p, int q) {
        char aa = a[p];
        a[p] = a[q];
        a[q] = aa;
    }

}
