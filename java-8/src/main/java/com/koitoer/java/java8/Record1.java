package com.koitoer.java.java8;

public class Record1 {

    public static void main(String[] args) {
        new Record1().longestPalindromeSubseq("bbbab");
    }

    public int longestPalindromeSubseq(String s) {

        int maximum = -1;
        for (int i = 0; i < s.length(); i++) {
            String inital = s.substring(0, i + 1);
            String fin = s.substring(i + 1, s.length());
            //System.out.println("sting -> " + fin + " : " + inital);
            String myString = fin + inital;

            System.out.println("Check string -> " + myString);
            for (int k = 0; k < myString.length(); k++) {
                for (int j = k; j <= myString.length(); j++) {
                    String aux = myString.substring(k, j);
                    System.out.println("    aux -> " + aux);

                    if (isPalindrome(aux)) {
                        System.out.println("        possible -> "+ aux + " : " +  aux.length());
                        maximum = Math.max(maximum, aux.length());
                    }
                }
            }
        }

        System.out.println(maximum);
        return maximum;
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        //System.out.println("string -> " + s);
        for (int i = 0; i < s.length(); i++) {
            int start = i;
            int fin = s.length() - 1 - i;
            char a = s.charAt(start);
            char b = s.charAt(fin);

            if (a == b) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

}
