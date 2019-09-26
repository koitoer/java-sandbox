package com.koitoer.java.let.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. Binary Watch
 * Copied from the current solutions.
 * Initial though was generate the combination of an int[] = [ 0 0 0 0 , 0 0 0 0 0]
 * Depending on the number of allowed num = 1
 * And then translate that to hours.
 */
public class Solution401 {

    public List<String> readBinaryWatch(int num) {
        int[] led = { 8, 4, 2, 1, 32, 16, 8, 4, 2, 1 };
        List<String> ans = new ArrayList<>();
        int h = 0, min = 0;

        backtrack(num, led, ans, h, min, 0);
        return ans;
    }

    public void backtrack(int num, int[] led, List ans, int hour, int min, int start) {

        //Outside for a valid hour or minute.
        if (hour > 11 || min > 59)
            return;

        //We are reducing the num value which is the number in each iteration, we stop at zero
        if (num == 0) {
            //Generate the string and save it on the list.
            String s = (min < 10) ? (hour + ":" + "0" + min) : (hour + ":" + min);
            ans.add(s);
            return;
        }

        //The position in which we start iterating to the array, the led is the value
        for (int i = start; i < led.length; i++) {

            //if is hour add that to hour the same for minute.
            if (i <= 3)
                hour += led[i];
            else
                min += led[i];

            //Reduce the number of leds that are going to be turned off, same led array
            //same list for answer, the current hour and time, plus change the start time
            backtrack(num - 1, led, ans, hour, min, i + 1);

            //Revert the previous change
            if (i <= 3)
                hour -= led[i];
            else
                min -= led[i];
        }
    }
}
