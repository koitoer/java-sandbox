package com.koitoer.java.let.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionBinaryCombinations {

    public static void main(String[] args) {
        int n = 8;
        int[] a = new int[n];
        List<String> r = new ArrayList<>();
        int num = 2;
        backtrack(a, num, 0, r);
    }



    private static void backtrack(int[] a, int num, int index, List<String> r) {

        /**
        if (hasNumOnes(a, num)) {
            System.out.println(Arrays.toString(a));
            r.add(Arrays.toString(a));
            return;
        }**/

        if(a.length == index){
            return;
        }


        a[index] = 1;
        backtrack(a, num, index + 1, r);
        a[index] = 0;
        backtrack(a, num, index + 1, r);

    }

    private static boolean hasNumOnes(int[] a, int num) {
        int count = 0;
        for (int aux : a) {
            if (aux == 1) {
                count++;
            }

            if (count == num) {
                return true;
            }
        }
        return false;

    }

}
