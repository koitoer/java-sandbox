package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;

public class Solution47a {

    @Test
    public void test() {
        int a[] = new int[] { 1, 1, 2 };
        new Solution47a().permute(a);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<Integer> result = new ArrayList<>();
        List A = Lists.newArrayList(nums);
        dfs(0, nums, results, result);
        return results;
    }

    public void dfs(int index, int[] nums, List<List<Integer>> results, List<Integer> A) {
        if (index == A.size() - 1) {
            List<Integer> temp = new ArrayList<>(A);
            results.add(temp);
            System.out.println(temp);
            return;
        }

        for (int j = index; j < A.size(); ++j) {
            Collections.swap(A, index, j);
            dfs(index + 1, nums, results, A);
            Collections.swap(A, index, j);
        }
    }

    private void swap(int[] nums, int index, int i) {
        int aux = nums[index];
        nums[index] = nums[i];
        nums[i] = nums[aux];
    }
}
