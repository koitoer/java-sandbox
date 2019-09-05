package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mmena on 4/28/19.
 */
public class SolutionNew {

    private Set<List<Integer>> permutations = new HashSet<>();

    private Map<Integer, Integer> counts = new HashMap<>();

    private List<List<Integer>> finalList = new ArrayList<>();

    public static void main(String[] args) {
       //int[] nums = { 1, 1, 2 };

        //int[] nums = {-1,2,-1,2,1,-1,2,1} ;

        int[] nums = {1,2,3};
        SolutionNew solution = new SolutionNew();
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
        this.countDifferentNumbers(nums);
        permutation(nums, new LinkedList<>(), 0);
        return createFinalList(permutations);
    }

    private List<List<Integer>> createFinalList(Set<List<Integer>> permutations) {

        for(List<Integer> queue : permutations){
            List<Integer> newList = new ArrayList<>();
            for(Integer valid : queue){
                newList.add(Integer.valueOf(valid));
            }
            finalList.add(newList);
        }
        return finalList;
    }

    private void permutation(int[] nums, List<Integer> prefix, int i) {
        if (prefix.size() == nums.length) {
            //System.out.println(prefix);
            if (isValidToAdd(prefix)) {
                permutations.add(prefix);
            }
            return;
        }

        if (!isValidToAdd(prefix)) {
            return;
        }

        for (int idx = 0; idx < nums.length; idx++) {
            prefix.add(nums[idx]);
            permutation(nums, new ArrayList<>(prefix), idx);
            prefix.remove(prefix.size() - 1);
        }
    }

    private boolean isValidToAdd(List<Integer> prefix) {

        HashMap<Integer, Integer> internal = new HashMap<>();

        for (Integer intValue : prefix) {
            Integer counter = internal.get(intValue);
            if (counter == null) {
                internal.put(intValue, 1);
            } else {
                internal.put(intValue, counter + 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : internal.entrySet()){
            if(counts.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }

        return true;
    }

}
