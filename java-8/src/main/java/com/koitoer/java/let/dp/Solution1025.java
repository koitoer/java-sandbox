package com.koitoer.java.let.dp;

public class Solution1025 {

    public boolean divisorGame2(int N) {
        int x = 1;
        boolean alice = false;

        while (x < N && N % x == 0) {
            N = N - x;
            while (N % x != 0) {
                x++;
            }
            alice = !alice;
        }

        return alice;
    }

    public boolean divisorGame3(int N) {
        return N % 2 == 0;
    }

    public boolean divisorGame(int N) {
        if (N == 1) {
            return false;
        } else {
            return !divisorGame(N - 1);
        }
    }
}
