package com.koitoer.java.let.array;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 1160. Find Words That Can Be Formed by Characters
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation:
 * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 *
 * Result
 * Runtime: 23 ms, faster than 35.31% of Java online submissions for Find Words That Can Be Formed by Characters.
 * Memory Usage: 38.1 MB, less than 100.00% of Java online
 */
public class Solution1160 {

    @Test
    public void test1() {
        String aux[] = { "cat", "bt", "hat", "tree" };
        String a = "atach";
        Assertions.assertThat(new Solution1160().countCharacters(aux, a)).isEqualTo(6L);
    }

    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> originalMap = new HashMap();

        for (char a : chars.toCharArray()) {
            originalMap.put(a, originalMap.getOrDefault(a, 0) + 1);
        }

        int total = 0;

        for (String word : words) {
            Map<Character, Integer> auxMap = new HashMap();
            auxMap.putAll(originalMap);

            boolean subtotal = true;
            for (char aux : word.toCharArray()) {
                int extra = auxMap.getOrDefault(aux, 0);
                if (extra == 0) {
                    subtotal = false;
                    break;
                } else {
                    auxMap.put(aux, extra - 1);
                }

            }
            if (subtotal) {
                total = total + word.length();
            }
        }

        return total;

    }
}
