package com.koitoer.java.let.perm;

/**
 * Created by mmena on 4/3/19.
 */
public class Permutacion2 {

    public static void main(String[] args) {
        String[] elementos = "a,b,c,d".split(",");
        Perm2(elementos, "", elementos.length);
    }

    private static void Perm2(String[] elem, String act, int n) {
        if (n == 0) {
            System.out.println(act);
        } else {
            for (int i = 0; i < elem.length; i++) {
                    Perm2(elem, act + elem[i] + ", ", n - 1);
            }
        }
    }
}