package com.koitoer.java.algorithm;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.assertj.core.api.Assertions;

import lombok.Getter;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class Solution1249 {

    public static void main(String[] args) {
        Assertions.assertThat(new Solution1249().minRemoveToMakeValid("lee(t(c)o)de)")).isEqualTo("lee(t(c)o)de");
        Assertions.assertThat(new Solution1249().minRemoveToMakeValid2("lee(t(c)o)de)")).isEqualTo("lee(t(c)o)de");
        Assertions.assertThat(new Solution1249().minRemoveToMakeValid("a)b(c)d")).isEqualTo("ab(c)d");
    }

    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i <= sb.length() - 1; i++) {
            Character current = s.charAt(i);
            if (current.equals('(')) {
                stack.push(i);
            } else if (current.equals(')')) {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    sb.setCharAt(i, '^');
                }
            }
        }

        while (!stack.isEmpty())
            sb.setCharAt(stack.pop(), '^');

        return sb.toString().replaceAll("\\^", "");
    }

    public String minRemoveToMakeValid2(String s) {
        Stack<Pair<Character, Integer>> stack = new Stack();
        Set<Integer> invalid = new HashSet<Integer>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= s.length() - 1; i++) {
            Character current = s.charAt(i);
            if (current.equals('(')) {
                stack.push(new Pair(current, i));
            } else if (current.equals(')')) {
                if (stack.isEmpty()) {
                    invalid.add(i);
                } else {
                    Pair<Character, Integer> previous = stack.peek();
                    if (previous.getKey().equals('(')) {
                        stack.pop();
                    } else {
                        stack.pop();
                        invalid.add(i);
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            invalid.add(stack.pop().getValue());
        }

        for (int i = 0; i <= s.length() - 1; i++) {
            if (!invalid.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    @Getter
    class Pair<A, B> {

        private A key;

        private B value;

        public Pair(A key, B value) {
            this.key = key;
            this.value = value;
        }
    }
}
