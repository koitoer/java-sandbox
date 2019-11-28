package com.koitoer.java.let.numbers;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 9. Palindrome Number
 * First solution use toCharArray();
 * Second solution use a proper stack
 * Third option use reverse the int and check if the input and this one are the same.
 */
public class Solution9 {

    @Test
    public void test2() {
        Assertions.assertThat(new Solution9().isPalindrome3(41214)).isTrue();
        Assertions.assertThat(new Solution9().isPalindrome3(-123)).isFalse();
        Assertions.assertThat(new Solution9().isPalindrome3(153423646)).isTrue();
    }

    public boolean isPalindrome(int x) {

        String s = Integer.toString(x);
        int p = 0;
        int p2 = s.length() - 1;

        char[] a = s.toCharArray();
        while (p < p2) {
            if (a[p] != a[p2]) {
                return false;
            }
            p++;
            p2--;
        }

        return true;
    }

    public boolean isPalindrome2(int x) {
        //base case (number is negative)
        if (x < 0)
            return false;

        //stack for digits
        Stack<Integer> stack = new Stack();

        //fill stack w/ digits from right to left
        int n = x;
        while (n > 0) {
            //push digit to stack
            stack.push(n % 10);
            n /= 10;
        }

        n = x;
        while (n > 0) {
            //check in reverse order from stack to see if digits match
            if (n % 10 != stack.pop())
                return false;
            n /= 10;
        }

        //get here its true
        return true;
    }

    /**
     * Process for reverting the number.
     */
    public boolean isPalindrome3(int x) {
        if (x == 0)
            return true;
        if (x < 0)
            return false;

        int temp = 0;
        int tx = x;
        while (tx > 0) {
            temp = temp * 10 + (tx % 10);
            tx /= 10;
        }
        if (x == temp)
            return true;

        return false;
    }
}
