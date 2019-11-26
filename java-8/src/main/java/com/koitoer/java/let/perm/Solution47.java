package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Solution47 {

    @Test
    public void test() {
        int a[] = new int[] { 1, 1, 2 };
        new Solution47().permuteUnique(a);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int n = nums.length;
        boolean[] v = new boolean[n];
        Arrays.sort(nums);
        permute(nums, v, new ArrayList<>(), result);
        return result;
    }

    private void permute(int[] nums, boolean[] v, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(current);
            System.out.println(current);
            return;

        }

        for (int i = 0; i < nums.length; i++) {
            if (v[i] || (i > 0 && nums[i] == nums[i - 1] && !v[i - 1])) // remove duplicate
                continue;

            current.add(nums[i]);
            v[i] = true;
            permute(nums, v, current, result);
            current.remove(current.size() - 1);
            v[i] = false;
        }
    }
}
