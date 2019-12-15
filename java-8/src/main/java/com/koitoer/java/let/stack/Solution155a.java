package com.koitoer.java.let.stack;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 155. Min Stack
 * We can have two stacks the first one will contains all the elements.
 * The second one only the minimum elements.
 */
public class Solution155a {

    @Test
    public void test() {
        Solution155a sol = new Solution155a();
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

    Stack<Integer> stk = new Stack<>();

    Stack<Integer> stk2 = new Stack<>();

    public void push(int x) {
        stk.push(x);                             //Always push into main stack
        if (stk2.isEmpty() || x <= stk2.peek())   //Push when empty OR x<= current smallest value
            stk2.push(x);
    }

    public void pop() {
        if (stk.pop().equals(stk2.peek()))    //Main stk always pop
            stk2.pop();                      //Other stk pops only if it equal to main stk's min
    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return stk2.peek();
    }
}
