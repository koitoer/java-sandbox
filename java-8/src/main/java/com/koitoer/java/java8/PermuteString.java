package com.koitoer.java.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * A way, especially one of several possible variations, in which a set or number of things can be ordered or arranged
 */
public class PermuteString {

    @Test
    public void testPermuteString3() {
        String string = "abc";
        Assertions.assertThat(new PermuteString().getPermutations(string).size()).isEqualTo(6);
        Assertions.assertThat(new PermuteString().getPermutations(string).size()).isEqualTo(6);
    }

    private List<String> getPermutations(String string) {
        List<String> results = new ArrayList<>();
        permuteString(string, "", results);
        return results;
    }

    /**
     * permuteString("abc", 0, "")
     * permuteString("abc", 0, "a")
     * permuteString("abc", 0, "ab")
     * permuteString("abc", 0, "abc")
     * permuteString("abc", 0, "ac")
     * permuteString("abc", 0, "acb")
     */
    private void permuteString(String string, String prefix, List<String> results) {
        if (string.length() == 0) {
            results.add(prefix);
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < string.length(); i++) {
            permuteString(string.substring(0, i) + string.substring(i+1),
                prefix + string.charAt(i), results);
        }
    }

}
