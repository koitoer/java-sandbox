package com.koitoer.java.let.stack;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 155. Min Stack
 * this is only using one single stack but pushing the min value every time it changes.
 * and popping the value twice when we discard.
 */
public class Solution155b {

    @Test
    public void test() {
        Solution155b sol = new Solution155b();
        sol.push(-1);
        sol.push(-1);
        sol.push(20);
        Assertions.assertThat(sol.getMin()).isEqualTo(-1);
        Assertions.assertThat(sol.getMin()).isEqualTo(-1);
        Assertions.assertThat(sol.top()).isEqualTo(20);
        sol.push(-2);
        Assertions.assertThat(sol.getMin()).isEqualTo(-2);
        sol.pop();
        Assertions.assertThat(sol.getMin()).isEqualTo(-1);
    }

    Stack<Integer> stack = new Stack<>();

    int min = Integer.MAX_VALUE;

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.peek() == min) {
            stack.pop();
            min = stack.pop();
        } else
            stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
