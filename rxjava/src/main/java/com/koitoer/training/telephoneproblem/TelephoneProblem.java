package com.koitoer.training.telephoneproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mmena on 3/23/18.
 */
public class TelephoneProblem {

    private static Set<String> dictionary = new HashSet<>();

    private static Map<String, List<Character>> telephone = new HashMap();

    @Before
    public void init() {
        dictionary.add("tree");
        dictionary.add("cats");
        dictionary.add("dogs");
        dictionary.add("free");
        dictionary.add("peer");
        dictionary.add("code");
        dictionary.add("beer");
        dictionary.add("tred");

        telephone.put("1", Collections.emptyList());
        telephone.put("2", Arrays.asList('a', 'b', 'c'));
        telephone.put("3", Arrays.asList('d', 'e', 'f'));
        telephone.put("4", Arrays.asList('g', 'h', 'i'));
        telephone.put("5", Arrays.asList('j', 'k', 'l'));
        telephone.put("6", Arrays.asList('m', 'n', 'o'));
        telephone.put("7", Arrays.asList('p', 'q', 'r'));
        telephone.put("8", Arrays.asList('s', 't', 'u'));
        telephone.put("9", Arrays.asList('v', 'w', 'x', 'y', 'z'));
    }

    @Test
    public void test() {
        Assertions.assertThat(new TelephoneProblem().getValidWords("8733", dictionary).containsAll(Arrays.asList("tree", "tred")));
    }

    private void getCombinations(String number, int index, String base, List<String> results, Set<String> dictionary) {
        if (base.length() == 4) {
            if (dictionary.contains(base)) {
                results.add(base);
            }
            return;
        }

        List<Character> characters = telephone.get(String.valueOf(number.charAt(index)));
        for (int i = 0; i < characters.size(); i++) {
            String letter = String.valueOf(characters.get(i));
            int j = index + 1;
            getCombinations(number, j, base + letter, results, dictionary);
        }

    }

    public List<String> getValidWords(String number, Set<String> dictionary) {
        List<String> result = new ArrayList<>();
        getCombinations(number, 0, "", result, dictionary);
        return result;
    }

}
