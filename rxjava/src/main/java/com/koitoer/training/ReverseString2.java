package com.koitoer.training;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by mmena on 2/27/18.
 */
public class ReverseString2 {

    String reverseParentheses2(String s) {

        StringBuilder a = new StringBuilder();
        char[] array = s.toCharArray();
        char[] nuevo = new char[array.length];


        return s;
    }

    String reverseParentheses(String s) {
        System.out.println("");
        StringBuilder a = new StringBuilder();
        while (s.contains("(")) {
            s = reverseParentheses2(s);
        }

        return s;
    }

    @Test
    public void test() {

        Assertions.assertThat(new ReverseString2().reverseParentheses("The ((quick (brown) (fox) jumps over the lazy) dog)"))
            .isEqualTo("The god quick nworb xof jumps over the lazy");
        Assertions.assertThat(new ReverseString2().reverseParentheses("(brown) (fox) ")).isEqualTo("nworb xof");
        Assertions.assertThat(new ReverseString2().reverseParentheses("(codef)")).isEqualTo("fedoc");
        Assertions.assertThat(new ReverseString2().reverseParentheses("co(de)f")).isEqualTo("coedf");

        Assertions.assertThat(new ReverseString2().reverseParentheses("(ab(de)f)")).isEqualTo("fdeba");

        Assertions.assertThat(new ReverseString2().reverseParentheses("co(de)f")).isEqualTo("coedf");

        Assertions.assertThat(new ReverseString2().reverseParentheses("co(de(fight)s)")).isEqualTo("cosfighted");
        Assertions.assertThat(new ReverseString2().reverseParentheses("Code(Cha(lle)nge)")).isEqualTo("CodeegnlleahC");
        Assertions.assertThat(new ReverseString2().reverseParentheses("The ((quick (brown) (fox) jumps over the lazy) dog)"))
            .isEqualTo("The god quick nworb xof jumps over the lazy");

    }

}
