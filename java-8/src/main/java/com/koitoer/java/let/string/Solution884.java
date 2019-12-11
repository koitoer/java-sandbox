package com.koitoer.java.let.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 884. Uncommon Words from Two Sentences
 */
public class Solution884 {

    public String[] uncommonFromSentences(String A, String B) {

        Map<String, Integer> count = new HashMap();
        for (String word : A.split(" ")) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        for (String word : B.split(" ")) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        List<String> result = new ArrayList();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }

        return result.toArray(new String[result.size()]);
    }
}
