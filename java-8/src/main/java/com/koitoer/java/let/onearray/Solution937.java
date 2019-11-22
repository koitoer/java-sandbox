package com.koitoer.java.let.onearray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 937. Reorder Data in Log Files
 * We will create two list, the first one save the letter and the second the digits.
 * We will sort the first one according to the rules, the second one will be appended once we already sort the first one.
 */
public class Solution937 {

    @Test
    public void test() {
        String[] a = new String[] { "a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo", "a2 act car" };
        Assertions.assertThat(new Solution937().reorderLogFiles(a))
            .containsExactly("a2 act car", "g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1", "zo4 4 7");
    }

    public String[] reorderLogFiles2(String[] logs) {
        List<String> alpha = new ArrayList<>();
        List<String> digit = new ArrayList<>();

        for (String s : logs) {
            String second1 = s.split(" ")[1];
            if (Character.isDigit(second1.charAt(0))) {
                digit.add(s);
            } else {
                alpha.add(s);
            }
        }

        Collections.sort(alpha, new Sorter());
        String[] fin = new String[logs.length];
        for (int i = 0; i < fin.length; i++) {
            if (i < alpha.size()) {
                fin[i] = alpha.get(i);
            } else {
                fin[i] = digit.get(i - alpha.size());
            }
        }

        return fin;
    }

    public String[] reorderLogFiles(String[] logs) {
        List<String> alpha = new ArrayList<>();
        List<String> digit = new ArrayList<>();

        for (String s : logs) {
            String second1 = s.split(" ")[1];
            if (Character.isDigit(second1.charAt(0))) {
                digit.add(s);
            } else {
                alpha.add(s);
            }
        }

        Collections.sort(alpha, new Sorter());
        alpha.addAll(digit);

        return alpha.toArray(new String[alpha.size()]);
    }

    class Sorter implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {

            String second1 = o1.split(" ")[1];
            String second2 = o2.split(" ")[1];

            int result = second1.compareTo(second2);
            if (result == 0) {
                String first1 = o1.substring(o1.indexOf(" "));
                String first2 = o2.substring(o2.indexOf(" "));
                int result2 = first1.compareTo(first2);

                if (result2 == 0) {
                    return o1.compareTo(o2);
                } else {
                    return result2;
                }
            }
            return result;
        }
    }
}
