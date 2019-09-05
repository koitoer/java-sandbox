package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mmena on 4/28/19.
 */
public class Solution {

    private Set<List<Integer>> permutations = new HashSet<>();

    private Map<Integer, Integer> counts = new HashMap<>();

    private HashMap<Integer, Integer> internal = new HashMap<>();

    public static void main(String[] args) {
        //int[] nums = { 1, 1, 2 };

        //int[] nums = {-1,2,-1,2,1,-1,2,1} ;

        int[] nums = { 1, 2, 3 };
        Solution solution = new Solution();
        System.out.println(solution.permuteUnique(nums));
    }

    private void countDifferentNumbers(int[] nums) {
        for (Integer num : nums) {
            Integer times = counts.get(num);
            if (times == null) {
                counts.put(num, 1);
            } else {
                counts.put(num, times + 1);
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.countDifferentNumbers(nums);
        permutation(nums, new ArrayList<>());
        return new ArrayList<>(permutations);
    }

    private void permutation(int[] nums, List<Integer> prefix) {

        if (!isValidToAdd()) {
            return;
        }

        if (prefix.size() == nums.length) {
            permutations.add(prefix);
            return;
        }

        for (int idx = 0; idx < nums.length; idx++) {

            prefix.add(nums[idx]);
            addInternal(nums[idx]);

            permutation(nums, new ArrayList<>(prefix));

            int removed = prefix.remove(prefix.size() - 1);
            removeInternal(removed);
        }
    }

    private void removeInternal(int removed) {
        Integer counter = internal.get(removed);
        if (counter == null) {
            throw new IllegalArgumentException("here is not allowed");
        } else {
            internal.put(removed, counter - 1);
        }
    }

    private void addInternal(Integer a) {
        Integer counter = internal.get(a);
        if (counter == null) {
            internal.put(a, 1);
        } else {
            internal.put(a, counter + 1);
        }
    }

    private boolean isValidToAdd() {
        for (Map.Entry<Integer, Integer> entry : internal.entrySet()) {
            if (counts.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

}
