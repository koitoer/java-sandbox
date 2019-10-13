package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 763. Partition Labels
 * Video explanation -> https://www.youtube.com/watch?v=ED4ateJu86I
 */
public class Solution763a {

    @Test
    public void test1() {
        Assertions.assertThat(new Solution763a().partitionLabels("ababcbacadefegdehijhklij")).contains(9, 7, 8);
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> part = new ArrayList();

        //Create an array with the latest index of each letter.
        int[] lastIndexes = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastIndexes[S.charAt(i) - 'a'] = i;
        }

        int i = 0;
        //While is something
        while (i < S.length()) {

            //Get a end for a letter.
            int end = lastIndexes[S.charAt(i) - 'a'];
            // From the i index to in front
            int j = i;

            // While both of them does not match.
            while (j != end) {
                //For the next element verify the lastIndex is lower than end if not update that
                //until we comply
                end = Math.max(end, lastIndexes[S.charAt(j++) - 'a']);
            }

            //We have i is initial - j is final index + 1 as is zero base
            part.add(j - i + 1);
            //We already check j, so we add one and start over.
            i = j + 1;
        }

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
