package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Recursive approach including and excluding the next element.
 * Basically we recurse not using the current number and after the recursive we added and we recurse again.
 */
public class Solution78a {

    @Test
    public void test() {
        int a[] = new int[] { 3, 2, 4, 1 };
        Assertions.assertThat(new Solution78a().subsets(a).size()).isEqualTo(16);
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        //Arrays.sort(nums);
        List result = new ArrayList();
        result.add(new ArrayList());

        createDistinct(nums, 0, new ArrayList(), result);
        return result;
    }

    private void createDistinct(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index > nums.length - 1) {
            return;
        }

        List<Integer> newCurrent = new ArrayList(current);
        createDistinct(nums, index + 1, newCurrent, result); // Generate new subset without adding current number.
        newCurrent.add(nums[index]);
        createDistinct(nums, index + 1, newCurrent, result); //Generate new subset with current number added.
        result.add(newCurrent);
        System.out.println(newCurrent);
    }

}
