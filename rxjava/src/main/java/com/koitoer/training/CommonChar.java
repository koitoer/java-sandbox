package com.koitoer.training;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;

/**
 * Created by mmena on 2/23/18.
 */
public class CommonChar {

    public static void main(String[] args) {
        Assertions.assertThat(new CommonChar().commonCharacterCount("aabcc","adcaa")).isEqualTo(3);
        Assertions.assertThat(new CommonChar().commonCharacterCount("zzzz","zzzzzzz")).isEqualTo(4);
        Assertions.assertThat(new CommonChar().commonCharacterCount("abca","xyzbac")).isEqualTo(3);

    }


    int commonCharacterCount(String s1, String s2){
        char[] ss2 = s2.toCharArray();
        int sum = 0 ;
        for(char letter :  s1.toCharArray()){
            for(int i = 0 ; i< ss2.length ; i ++){
                if(ss2[i] == letter){
                    ss2[i] = '0';
                    sum++;
                    break;
                }
            }
        }
        return sum;
    }


    int commonCharacterCount2(String s1, String s2) {
        Map result = new HashMap<Character, Integer>();
        for(Character letter : s1.toCharArray()){
            int index = s2.indexOf(letter);
            if(s2.indexOf(letter) >= 0 ){




                if(result.get(letter) == null){
                    result.put(letter, 1);
                }
            }
        }
        return result.size();
    }

}
