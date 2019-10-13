package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 763. Partition Labels
 * The key here is a recursive function that pass the rest of the string. Ex ababcbacadefegdehijhklij
 * visit(ababcbacadefegdehijhklij, 0, 9)
 * visit(defegdehijhklij,0,8) <-- in this case the index of one of the char that are inside is outside, so we increment the range.
 * visit(hijhklij, 0, 8)
 */
public class Solution763 {

    @Test
    public void test1() {
        Assertions.assertThat(new Solution763().partitionLabels("ababcbacadefegdehijhklij")).contains(9, 7, 8);
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> part = new ArrayList();
        visit(S, 0, S.lastIndexOf(S.charAt(0)) + 1, part);
        return part;
    }

    private void visit(String s, int down, int up, List part) {

        String sub = s.substring(down, up);
        int i = 0;
        while (i != up) {

            char c = sub.charAt(i++);

            //Lookg for max
            int lastIndex = s.lastIndexOf(c);
            if (lastIndex >= up) {
                up = lastIndex + 1;
                sub = s.substring(down, up);
            }
        }

        part.add(sub.length());
        String subString = s.substring(up);

        if (!subString.isEmpty()) {
            visit(subString, 0, subString.lastIndexOf(subString.charAt(0)) + 1, part);
        }

    }

}
