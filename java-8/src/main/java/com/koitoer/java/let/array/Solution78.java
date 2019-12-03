package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Solution78 {

    @Test
    public void test() {
        int a[] = new int[] { 3, 2, 4, 1 };
        new Solution78().subsets(a);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> out = new ArrayList();
        //Add the empty list
        out.add(new ArrayList());

        for (int i = 0; i < nums.length; i++) {
            // create a new subset
            List<List<Integer>> newList = new ArrayList();

            // walking thought all subsets and add current number from num to each item
            for (int n = 0; n < out.size(); n++) {
                List<Integer> c = new ArrayList(out.get(n));
                c.add(nums[i]);
                newList.add(c);
            }
            System.out.println(newList);
            out.addAll(newList);
        }

        return out;
    }

}
