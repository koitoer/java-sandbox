package com.koitoer.java.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;

public class Recursion {

    @Test
    public void createAllPermutations() {
        String a[] = new String[] { "7", "3", "5" };
        ArrayList<String> result = new ArrayList<>();
        permutations(0, a, result);
        System.out.println(result/**/);
    }

    /**
     * a[7] +
     * a[3]
     * + a[5]
     * a[5]
     * + a[3]
     * a[3]
     * a[7]
     * + a[5]
     */
    private void permutations(int index, String[] a, ArrayList<String> result) {
        if (index == a.length - 1) {
            result.add(printArray(a));
            return;
        }

        for (int i = index; i < a.length ; i++) {
            swapElement(a, index, i);
            permutations(index + 1, a, result);
            swapElement(a, index, i);
        }
    }

    private void swapElement(String a[], int index1, int index2){
        String original = a[index1];
        a[index1] = a[index2];
        a[index2] = original;
    }

    private String printArray(String a[]) {
        String aux = Arrays.asList(a).stream()
            .collect(Collectors.joining(","));
        return aux;

    }

}
