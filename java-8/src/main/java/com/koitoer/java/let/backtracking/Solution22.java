package com.koitoer.java.let.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

/**
 * 22. Generate Parentheses
 * This is backtracking to the number of n*2;
 * We will discard combinations that does not make any sense.
 */
public class Solution22 {

    @Test
    public void test(){
        new Solution22().generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        createAll(n, 0, "", result);
        return result;
    }

    private void createAll(int size, int index, String prefix, List<String> result) {
        if (prefix.length() == size * 2) {
            if (isValid2(prefix)) {
                result.add(prefix);
            }
            return;
        }

        List<String> a = Arrays.asList("(", ")");
        for (int i = 0; i < a.size(); i++) {
            String p = a.get(i);
            createAll(size, i, prefix + p, result);
        }

    }

    public boolean isValid(String prefix) {
        if (prefix.startsWith(")") || prefix.endsWith("(")) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        int countO = 0;
        int countC = 0;
        for (char a : prefix.toCharArray()) {
            stack.push(a);
            if (a == '(') {
                countO++;
            } else {
                countC++;
            }
        }

        if (countC != countO) {
            return false;
        }

        return true;

    }

    public boolean isValid2(String prefix) {
        if (prefix.startsWith(")") || prefix.endsWith("(")) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (char a : prefix.toCharArray()) {
            if (a == '(') {
                stack.push(a);
            } else {
                if (stack.isEmpty()) {
                    stack.push(a);
                    continue;
                }
                char s = stack.peek();
                if (s == '(') {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();

    }

}
