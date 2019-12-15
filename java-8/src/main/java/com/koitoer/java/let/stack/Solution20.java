package com.koitoer.java.let.stack;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution20 {

    @Test
    public void test() {
        Assertions.assertThat(new Solution20().isValid("()()")).isTrue();
        Assertions.assertThat(new Solution20().isValid("(){}")).isTrue();
        Assertions.assertThat(new Solution20().isValid("((()))")).isTrue();
        Assertions.assertThat(new Solution20().isValid("((())}")).isFalse();
        Assertions.assertThat(new Solution20().isValid("")).isTrue();
        Assertions.assertThat(new Solution20().isValid("[")).isFalse();
        Assertions.assertThat(new Solution20().isValid("((")).isFalse();
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution20().isValid("[])")).isFalse();
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        } else if (s.length() == 1) {
            return false;
        }

        char c[] = s.toCharArray();
        Stack<Character> stack = new Stack();
        stack.push(c[0]);
        int p = 1;
        while (p <= s.length() - 1) {
            char next = c[p++];
            if (next == '(' || next == '[' || next == '{') {
                stack.push(next);
            } else {
                if(stack.isEmpty()){
                    return false;
                }
                char current = stack.pop();
                if (current == '(' && next == ')' ||
                    current == '[' && next == ']' ||
                    current == '{' && next == '}') {

                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
