package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mmena on 4/28/19.
 */
public class SolutionWork {

    private Set<String> permutations = new HashSet<>();

    private Map<Integer, Integer> counts = new HashMap<>();

    private List<List<Integer>> finalList = new ArrayList<>();

    public static void main(String[] args) {
       // int[] nums = { 1, 1, 2 };

        int[] nums = {-1,2,-1,2,1,-1,2,1} ;
        SolutionWork solution = new SolutionWork();
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
        permutation(nums, "", 0);
        return createFinalList(permutations);
    }

    private List<List<Integer>> createFinalList(Set<String> permutations) {

        for(String value : permutations){
            List<Integer> newList = new ArrayList<>();
            for(Character character : value.toCharArray()){
                newList.add(Integer.valueOf(character.toString()));
            }
            finalList.add(newList);
        }
        return finalList;
    }

    private void permutation(int[] nums, String prefix, int i) {
        if (prefix.length() == nums.length) {
            if (isValidToAdd(prefix)) {
                permutations.add(prefix);
            }
            return;
        }

        for (int idx = 0; idx < nums.length; idx++) {
            String newPrefix = prefix + String.valueOf(nums[idx]);
            permutation(nums, newPrefix, idx);
        }
    }

    private boolean isValidToAdd(String prefix) {

        System.out.println(prefix);
        HashMap<Integer, Integer> internal = new HashMap<>();

        for (Character character : prefix.toCharArray()) {
            Integer intValue = Integer.valueOf(character.toString());
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
