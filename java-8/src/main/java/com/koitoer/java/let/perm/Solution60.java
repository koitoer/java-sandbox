package com.koitoer.java.let.perm;

import org.junit.Test;

public class Solution60 {

    @Test
    public void test1(){
        new Solution60().getPermutation(3,3);
        String r = "a";
        method(r);
        System.out.println(r);
    }

    private void method(String r) {
        r += "aaa";
    }

    String result = "";
    int p = 0;

    public String getPermutation(int n, int k) {
        String string = "";
        for (int i = 1; i <= n; i++) {
            string += i;
        }

        permuteString(string, "", result, 1, k);
        return result;
    }

    /**
     * permuteString("abc", 0, "")
     * permuteString("abc", 0, "a")
     * permuteString("abc", 0, "ab")
     * permuteString("abc", 0, "abc")
     * permuteString("abc", 0, "ac")
     * permuteString("abc", 0, "acb")
     */
    private void permuteString(String string, String prefix, String r, int current, int k) {
        if (string.length() == 0) {
            p++;
            if (p == k) {
                result = prefix;
                return;
            }
            return;
        }

        for (int i = 0; i < string.length(); i++) {
            permuteString(string.substring(0, i) + string.substring(i + 1),
                prefix + string.charAt(i), r, current++, k);

            if(result !=""){
                break;
            }
        }
    }

}
