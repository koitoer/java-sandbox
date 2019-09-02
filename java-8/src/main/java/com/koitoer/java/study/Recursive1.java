package com.koitoer.java.study;

import org.junit.Test;

public class Recursive1 {

    @Test
    public void revertAString(){
        printReverse("perro".toCharArray());
    }

    private static void printReverse(char [] str) {
        helper(0, str);
    }

    private static void helper(int index, char [] str) {
        if (str == null || index >= str.length) {
            return;
        }
        helper(index + 1, str);
        System.out.print(str[index]);
    }
}
