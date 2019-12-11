package com.koitoer.java.let.stack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 316. Remove Duplicate Letters
 * This is not the correct solution as this is not in out-putting in the correct order.
 */
public class Solution316 {

    @Test
    public void test() {
        String a = "bcabc";
        Assertions.assertThat(new Solution316().removeDuplicateLetters(a)).isEqualTo("abc");
    }

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] ss = s.toCharArray();
        Character[] characterArray = new Character[ss.length];
        for (int i = 0; i < ss.length; i++) {
            characterArray[i] = ss[i];
        }

        Arrays.sort(characterArray, Comparator.reverseOrder());

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < characterArray.length; i++) {
            if (!stack.isEmpty()) {
                if (stack.peek() != characterArray[i]) {
                    stack.push(characterArray[i]);
                }
            } else {
                stack.push(characterArray[i]);
            }

        }

        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.isEmpty()) {
            stringBuffer.append(stack.pop());
        }

        return stringBuffer.toString();
    }

}
