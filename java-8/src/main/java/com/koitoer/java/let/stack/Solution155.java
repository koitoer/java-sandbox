package com.koitoer.java.let.stack;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 155. Min Stack
 * Don't forget to reinitialize the correct minimum when you pop elements.
 */
public class Solution155 {

    @Test
    public void test() {
        Solution155 sol = new Solution155();
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

    Stack<MyItem> stack = new Stack();

    int currentMinimum = Integer.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public Solution155() {

    }

    public void push(int x) {
        if (x <= currentMinimum) {
            currentMinimum = x;
        }
        stack.push(new MyItem(x, currentMinimum));
    }

    /**
     * When you pop you need to reset the minimum for future comparisons
     */
    public void pop() {
        stack.pop();
        if (!stack.isEmpty()) {
            currentMinimum = stack.peek().minimum;
        } else {
            currentMinimum = Integer.MAX_VALUE;
        }
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().minimum;
    }

    class MyItem {

        public int value;

        public int minimum;

        public MyItem(int value, int minimum) {
            this.value = value;
            this.minimum = minimum;
        }
    }
}
