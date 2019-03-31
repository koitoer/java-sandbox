package com.koitoer.training;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

/**
 * Created by mmena on 2/23/18.
 */
public class AllLongest {

    public static void main(String[] args) {
        String [] a = new String[]{"aba", "aa", "ad", "vcd", "aba"};
        Assertions.assertThat(new AllLongest().allLongestStrings(a)).contains("aba","vcd", "aba");
    }

    String[] allLongestStrings(String[] inputArray) {
        List<String> result = new ArrayList<>(inputArray.length);
        int maxSize = 0;
        for(String word : inputArray){
            if(word.length() > maxSize){
                result.clear();
                result.add(word);
                maxSize = word.length();
            }else if(word.length() == maxSize){
                result.add(word);
            }
        }
        return result.toArray(new String[result.size()]);
    }

}
