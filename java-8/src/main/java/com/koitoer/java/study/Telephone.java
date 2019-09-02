package com.koitoer.java.study;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Telephone {

    public Set<String> dic = new HashSet<>();

    public Set<String> all = new HashSet<>();

    public static void main2(String[] args) {
        Telephone telephone = new Telephone();
        telephone.dic.addAll(Arrays.asList("cats", "dogs", "tree", "puts", "alls"));

        Assertions.assertThat(telephone.validWords(telephone.dic, "1177")).contains("cats");
    }

    @Test
    public void test_1() {
        Telephone telephone = new Telephone();
        telephone.dic.addAll(Arrays.asList("cats", "dogs", "tree", "puts", "alls"));
        Assertions.assertThat(telephone.validWords(telephone.dic, "1177")).contains("cats");
    }

    @Test
    public void test_2() {
        Telephone telephone = new Telephone();
        telephone.dic.addAll(Arrays.asList("cats", "dogs", "tree", "puts", "alls"));
        Assertions.assertThat(telephone.validWords(telephone.dic, "2537")).contains("dogs");
    }

    public List<String> validWords(Set<String> dictionary, String number) {

        for (int i = 0; i < number.length() - 1; i++) {
            createString(i, number, "");
        }

        dictionary.retainAll(all);
        return dictionary.stream().collect(Collectors.toList());
    }

    public void createString(int index, String number, String current) {

        if (index == number.length()) {
            System.out.println(current);
            all.add(current);
        }

        List<String> append = getLetters(Character.toString(number.charAt(index)));
        for (String aux : append) {
            System.out.println(current);
            createString(index + 1, number, current + aux);
        }
    }

    public List<String> getLetters(String singleDigitNumber) {
        switch (singleDigitNumber) {
        case "1":
            return Arrays.asList("a", "b", "c");
        case "2":
            return Arrays.asList("d", "e", "f");
        case "3":
            return Arrays.asList("g", "h", "i");
        case "4":
            return Arrays.asList("j", "k", "l");
        case "5":
            return Arrays.asList("m", "n", "o");
        case "6":
            return Arrays.asList("p", "q", "r");
        case "7":
            return Arrays.asList("s", "t", "u");
        case "8":
            return Arrays.asList("v", "w", "x");
        case "9":
            return Arrays.asList("y", "z");
        }
        throw new IllegalStateException("no number");
    }

}
