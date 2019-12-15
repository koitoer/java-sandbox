package com.koitoer.java.let.stack;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution20a {

    @Test
    public void test() {
        Assertions.assertThat(new Solution20a().isValid("()()")).isTrue();
        Assertions.assertThat(new Solution20a().isValid("(){}")).isTrue();
        Assertions.assertThat(new Solution20a().isValid("((()))")).isTrue();
        Assertions.assertThat(new Solution20a().isValid("((())}")).isFalse();
        Assertions.assertThat(new Solution20a().isValid("")).isTrue();
        Assertions.assertThat(new Solution20a().isValid("[")).isFalse();
        Assertions.assertThat(new Solution20a().isValid("((")).isFalse();
    }

    @Test
    public void test2() {
        Assertions.assertThat(new Solution20a().isValid("[])")).isFalse();
    }

    public boolean isValid(String s) {

        if (s.length() % 2 != 0)
            return false;

        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if (!stack.isEmpty()) {
                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (c == '}' && stack.peek() == '{') {
                    stack.pop();
                } else if (c == ']' && stack.peek() == '[') {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }
}
