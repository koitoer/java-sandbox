package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution5Let {

    @Test
    public void test1() {
        String a =
            "klvxwqyzugrdoaccdafdfrvxiowkcuedfhoixzipxrkzbvpusslsgfjocvidnpsnkqdfnnzzawzsslwnvvjyoignsfbxkgrokzyusxikxumrxlzzrnbtrixxfioormoyyejashrowjqqzifacecvoruwkuessttlexvdptuvodoavsjaepvrfvbdhumtuvxufzzyowiswokioyjtzzmevttheeyjqcldllxvjraeyflthntsmipaoyjixygbtbvbnnrmlwwkeikhnnmlfspjgmcxwbjyhomfjdcnogqjviggklplpznfwjydkxzjkoskvqvnxfzdrsmooyciwulvtlmvnjbbmffureoilszlonibbcwfsjzguxqrjwypwrskhrttvnqoqisdfuifqnabzbvyzgbxfvmcomneykfmycevnrcsyqclamfxskmsxreptpxqxqidvjbuduktnwwoztvkuebfdigmjqfuolqzvjincchlmbrxpqgguwuyhrdtwqkdlqidlxzqktgzktihvlwsbysjeykiwokyqaskjjngovbagspyspeghutyoeahhgynzsyaszlirmlekpboywqdliumihwnsnwjc";
        Assertions.assertThat(new Solution5Let().longestPalindrome(a)).isEqualTo("wnsnw");
    }

    public String longestPalindrome(String s) {

        if (s.length() == 1) {
            return s;
        }

        char ar[] = s.toCharArray();
        int max = 1;
        String maxPalindrome = s.substring(0, 1);

        String aux;

        for (int i = 0; i < ar.length - 1; i++) {
            aux = "" + ar[i];
            for (int j = i + 1; j < ar.length; j++) {
                aux += ar[j];
                if (isPal(aux)) {
                    if (aux.length() > max) {
                        maxPalindrome = aux;
                        max = aux.length();
                    }
                }
            }
        }

        return maxPalindrome;
    }

    private boolean isPal(String a) {
        StringBuffer sb = new StringBuffer(a);
        return a.equals(sb.reverse().toString());
    }
}