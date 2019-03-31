package com.koitoer.training;

import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by mmena on 2/27/18.
 */
public class ReverseString {

    String reverse(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {

            stringBuilder.append(c);
        }

        return stringBuilder.toString();

    }

    String reverseParentheses2(String s) {

        System.out.println("Input :" + s);

            int start =0 ,end =0, deep = 0;
            char[] a = s.toCharArray();
            for(int i = 0; i< s.length() ; i++){
                if(a[i] == '('){
                    if(deep == 0) {
                        start = i;
                    }
                    deep++;
                }

                if(a[i] == ')'){
                    deep--;
                    if(deep == 0){
                        end = i;
                    }
                }
            }


            String sub = s.substring(start+1, end);
            String rev = new StringBuffer(sub).reverse().toString();
            String first = s.substring(0, start);
            String last = s.substring(end + 1, s.length());


        System.out.println("first :" + first + ": last :" + last);

        StringBuilder stringBuilder= new StringBuilder();
            stringBuilder.append(first);
            for (char p : rev.toCharArray()){
                if(p == '('){
                    stringBuilder.append(")");
                }else if(p == ')'){
                    stringBuilder.append("(");
                }else {
                    stringBuilder.append(p);
                }
            }
        stringBuilder.append(last);

        System.out.println("Output :" + stringBuilder.toString());
        return stringBuilder.toString();
    }


    String reverseParentheses(String s) {
        while(s.contains("(")){
            s= reverseParentheses2(s);
        }
        return s;
    }




        @Test
    public void test() {
            Assertions.assertThat(new ReverseString().reverseParentheses("(ab(de)f)")).isEqualTo("fdeba");

            Assertions.assertThat(new ReverseString().reverseParentheses("(brown) (fox)")).isEqualTo("nworb xof");


        Assertions.assertThat(new ReverseString().reverseParentheses("(codef)")).isEqualTo("fedoc");
        Assertions.assertThat(new ReverseString().reverseParentheses("co(de)f")).isEqualTo("coedf");



        Assertions.assertThat(new ReverseString().reverseParentheses("co(de)f")).isEqualTo("coedf");

        Assertions.assertThat(new ReverseString().reverseParentheses("co(de(fight)s)")).isEqualTo("cosfighted");
        Assertions.assertThat(new ReverseString().reverseParentheses("Code(Cha(lle)nge)")).isEqualTo("CodeegnlleahC");
        Assertions.assertThat(new ReverseString().reverseParentheses("The ((quick (brown) (fox) jumps over the lazy) dog)"))
            .isEqualTo("The god quick nworb xof jumps over the lazy");

    }

}
