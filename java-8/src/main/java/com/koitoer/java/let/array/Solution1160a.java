package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 1160. Find Words That Can Be Formed by Characters
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation:
 * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 * Result
 * Runtime: 5 ms, faster than 80.26% of Java online submissions for Find Words That Can Be Formed by Characters.
 * Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Find Words That Can Be Formed by Characters.
 * ARRAY SOLUTION
 */
public class Solution1160a {

    @Test
    public void test1() {
        String aux[] = { "cat", "bt", "hat", "tree" };
        String a = "atach";
        Assertions.assertThat(new Solution1160a().countCharacters(aux, a)).isEqualTo(6L);
    }

    public int countCharacters(String[] words, String chars) {
        int map[] = new int[26];

        for (char a : chars.toCharArray()) {
            map[a - 'a']++;
        }

        int total = 0;
        for (String word : words) {
            int auxMap[] = map.clone();

            boolean subtotal = true;
            for (char aux : word.toCharArray()) {
                if (auxMap[aux - 'a'] == 0) {
                    subtotal = false;
                    break;
                } else {
                    auxMap[aux - 'a']--;
                }

            }
            if (subtotal) {
                total = total + word.length();
            }
        }

        return total;

    }
}
