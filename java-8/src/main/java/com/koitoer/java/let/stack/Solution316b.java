package com.koitoer.java.let.stack;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 316. Remove Duplicate Letters
 * This is not the correct solution as this is not in out-putting in the correct order.
 */
public class Solution316b {

    @Test
    public void test() {
        String a = "bcabc";
        Assertions.assertThat(new Solution316b().removeDuplicateLetters(a)).isEqualTo("abc");
    }

    @Test
    public void test2() {
        String a = "cbacdcbc";
        Assertions.assertThat(new Solution316b().removeDuplicateLetters(a)).isEqualTo("acdb");
    }

    public String removeDuplicateLetters(String s) {
        //Initialize variables.
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        char[] arr = s.toCharArray();

        //Count number of chars.
        for (char c : arr) {
            count[c - 'a']++;
        }

        boolean[] visited = new boolean[26];
        for (char c : arr) {
            count[c - 'a']--;

            if (visited[c - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(c);
            visited[c - 'a'] = true;
        }

        //Make the string..
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
