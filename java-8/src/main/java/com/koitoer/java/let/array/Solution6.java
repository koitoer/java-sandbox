package com.koitoer.java.let.array;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Solution6 {

    @Test
    public void test1() {
        checkInclusion2("hello", "ooolleoooleh");

        //checkInclusion("ab", "acdabar");

    }


    public boolean checkInclusion2(String s, String t) {
        if(s.length() > t.length()) return false;
        int[] sMap = new int[26];
        int[] tMap = new int[26];
        for(int i = 0; i < s.length(); i++) {
            sMap[s.charAt(i) - 'a']++;
            tMap[t.charAt(i) - 'a']++;
        }

        if(checkMaps(sMap, tMap)) return true;

        for(int i = s.length(); i < t.length(); i++) {
            tMap[t.charAt(i - s.length()) - 'a']--;
            tMap[t.charAt(i) - 'a']++;
            if(checkMaps(sMap, tMap)) return true;
        }

        return false;
    }

    private boolean checkMaps(int[] sMap, int[] tMap) {
        for(int i = 0; i < 26; i++) {
            if(sMap[i] != tMap[i]) return false;
        }
        return true;
    }

    public boolean checkInclusion(String s1, String s2) {

        if(s1.length() > s2.length()) {
            return false;
        }


        Map<String, Integer> map = new HashMap();

        for (int i = 0; i < s1.length(); i++) {
            map.put("" + s1.charAt(i), map.getOrDefault("" + s1.charAt(i), 0) + 1);
        }

        int i = 0;
        while (i < s2.length()) {

            //Curent s2 value
            String current = "" + s2.charAt(i);

            //Check if the next char might start a window.
            if (map.get(current) != null) {
                //Value is present check the next n string.
                boolean resultOfcheck = check(s2, i, s1.length(), map);

                if (resultOfcheck) {
                    return true;
                }
            }

            //The value is not in s1, we wont' check
            i++;

        }

        return false;
    }

    private boolean check(String s2, int index, int length, Map<String, Integer> map) {
        //Get substring to check
        if(index + length > s2.length()){
            return false;
        }

        String probable = s2.substring(index, index + length);

        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < probable.length(); i++) {
            map2.put("" + probable.charAt(i), map2.getOrDefault("" + probable.charAt(i), 0) + 1);
        }

        return map.equals(map2);
    }

    private boolean checkMaps(Map<String, Integer> originalMap, Map<String, Integer> map2) {

        //originalMap.
        return true;
    }

}
