package com.koitoer.java.let.perm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mmena on 4/2/19.
 */
public class SolutionOld {

    private Map<Integer, Integer> chars = new HashMap<>();

    public static void main(String[] args) {
        //int[] a = { -1,2,-1,2,1,-1,2,1};
        int[] a = { -1, 2, -1 };
        List<List<Integer>> fin = new SolutionOld().permuteUnique(a);
        System.out.println("aAA");
        System.out.println(fin);

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        initChars(nums);
        System.out.println(chars);
        List<List<Integer>> finalList = new ArrayList<>();
        permutation(nums, new ArrayList<>(), 0, finalList);

        return finalList;
        //HashSet<List<Integer>> hashSet = new HashSet(finalList);
        //return hashSet.stream().collect(Collectors.toList());

    }

    private void initChars(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            Integer value = chars.get(nums[i]);
            if (value == null) {
                chars.put(nums[i], 1);
            } else {
                chars.put(nums[i], value + 1);
            }
        }
    }

    void permutation(int[] a, List<Integer> subList, int index, List<List<Integer>> finalList) {
        if (a.length == index) {
            if (!finalList.contains(subList)) {
                finalList.add(new ArrayList<>(subList));
            }
            //System.out.println(subList);
            subList.remove(index - 1);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (isAllowed(subList, a[i])) {
                //if (subList.contains(Integer.valueOf(a[i]))) {
                subList.add(a[i]);
                permutation(a, subList, index + 1, finalList);
                //}
            }
        }
        if (subList.size() != 0) {
            subList.remove(subList.size() - 1);
        }
    }

    private boolean isAllowed(List<Integer> subList, Integer a) {
        long countNumberOfInteger = subList.stream().filter(x -> x.equals(a)).count();
        int originalNumber = chars.get(a);
        if (originalNumber > countNumberOfInteger) {
            return true;
        }
        return false;
    }

}
