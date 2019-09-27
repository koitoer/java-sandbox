package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 60. Permutation Sequence
 * We are passing a List and AtomicInteger to deal with the return values.
 * This will stop in the moment we generate the number that we are looking for.
 * However, it might be a simple solution generating the number directly.
 */
public class Solution60a {

    @Test
    public void test1(){
        Assertions.assertThat(new Solution60a().getPermutation(3,3)).isEqualTo("213");
        Assertions.assertThat(new Solution60a().getPermutation(8,27428)).isEqualTo("64158273");

    }

    public String getPermutation(int n, int k) {
        String string = "";
        for (int i = 1; i <= n; i++) {
            string += i;
        }

        List<String> result = new ArrayList(1);
        permuteString(string, "", result, 1, k, new AtomicInteger());
        return result.get(0);
    }

    /**
     * permuteString("abc", 0, "")
     * permuteString("abc", 0, "a")
     * permuteString("abc", 0, "ab")
     * permuteString("abc", 0, "abc")
     * permuteString("abc", 0, "ac")
     * permuteString("abc", 0, "acb")
     */
    private void permuteString(String string, String prefix, List<String> r, int current, int k, AtomicInteger a) {
        if (string.length() == 0) {
            a.incrementAndGet();
            if (a.get() == k) {
                r.add(prefix);
            }
            return;
        }

        for (int i = 0; i < string.length(); i++) {
            permuteString(string.substring(0, i) + string.substring(i + 1),
                prefix + string.charAt(i), r, current++, k, a);

            if(!r.isEmpty()){
                break;
            }
        }
    }

}
