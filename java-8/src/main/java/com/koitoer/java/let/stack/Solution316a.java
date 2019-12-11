package com.koitoer.java.let.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 316. Remove Duplicate Letters
 * This is not the correct solution as this is not in out-putting in the correct order.
 */
public class Solution316a {

    @Test
    public void test() {
        String a = "bcabc";
        Assertions.assertThat(new Solution316a().removeDuplicateLetters(a)).isEqualTo("abc");
    }

    @Test
    public void test2() {
        String a = "cbacdcbc";
        Assertions.assertThat(new Solution316a().removeDuplicateLetters(a)).isEqualTo("acdb");
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

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < characterArray.length; i++) {
            addReorganize(stack, characterArray[i]);
        }

        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.isEmpty()) {
            stringBuffer.append(stack.pop());
        }

        return stringBuffer.reverse().toString();
    }

    private void addReorganize(Stack<Character> stack, Character character) {
        if (stack.isEmpty()) {
            stack.push(character);
        } else {
            //Stack is not empty, check if element already exists and give the pos.
            int index = stack.search(character);
            if (index == -1) {
                //element does not exist then push it
                stack.push(character);
            } else {
                //Element do exists, look for it and remove in the case

                //Order is not correct remove element
                List<Character> order = new ArrayList<>();

                int minumum = Integer.MAX_VALUE;
                for (int j = 0; j < index - 1; j++) {
                    Character cc = stack.pop();
                    order.add(cc);
                    minumum = Math.min(minumum, Integer.valueOf(cc.charValue()));
                }

                boolean pushAtTheEnd = false;
                if (order.get(order.size() - 1).compareTo(character) < 0) {
                    stack.pop();
                    pushAtTheEnd = true;
                }

                for (int i = order.size(); i > 0; i--) {
                    stack.push(order.get(i - 1));
                }
                //And push it
                if (pushAtTheEnd) {
                    stack.push(character);
                }

            }
        }
    }
}
