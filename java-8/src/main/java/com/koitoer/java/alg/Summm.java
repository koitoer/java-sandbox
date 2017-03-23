package com.koitoer.java.alg;

import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * For example, given array S = {-1 0 1 2 -1 -4},
 * A solution set is:  (-1, 0, 1)  -   (-1, -1, 2)
 * Created by koitoer on 8/14/16.
 */
public class Summm {


    public static void main(String[] args) {
        int a[] = {-1, -1, 0, 1, 0, 1, 2, -1, -4};
        System.out.println(Summm.threeSum(a));
        System.out.println(Summm.threeSum2(a));
    }

    /**
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        //Validate if input have values and have minimum 3 elements that can be the sum
        if(nums == null || nums.length<3)
            return result;

        //Let's sort the array to avoid duplication, once we found a possible match
        Arrays.sort(nums);

        //Iterate over the array from 0 to the maximum -2, so we can always have three possible values
        for(int i=0; i<nums.length-2; i++){

            //Verify we don't use the duplicate number to run this loop
            if(i==0 || nums[i] > nums[i-1]){

                //Pointer to the beginning
                int j=i+1;
                //Pointer to the end
                int k=nums.length-1;

                //While the first pointer is first than the second
                while(j<k){

                    //If match, save in array and move
                    if(nums[i]+nums[j]+nums[k]==0){
                        List<Integer> l = new ArrayList<Integer>();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[k]);
                        result.add(l);

                        j++;
                        k--;

                        //handle duplicate here for first pointer
                        //while(j<k && nums[j]==nums[j-1])
                          //  j++;
                        //Move for the second pointer
                        //while(j<k && nums[k]==nums[k+1])
                          //  k--;

                        //If the sum is negative move the first pointer
                    }else if(nums[i]+nums[j]+nums[k]<0){
                        j++;
                        //If the sum is positive move the second pointer
                    }else{
                        k--;
                    }
                }
            }
        }

        return result;
    }




    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);
        Map<String, Boolean> tripMap = new HashMap<String, Boolean>();


        for(int i = 0; i < nums.length - 2; i++){
            int j = i + 1;
            int k = nums.length - 1;

            while ( j < k) {
                if(nums[i] + nums[j] + nums[k] == 0){
                    List<Integer> trip = new ArrayList<Integer>(Arrays.asList(new Integer[]{nums[i], nums[j] , nums[k]}));
                    String tripKey = nums[i] + "" + nums[j] + "" + nums[k];
                    if(!tripMap.containsKey(tripKey)) {
                        result.add(trip);
                        tripMap.put(tripKey, true);
                    }
                    j++;
                    k--;

                }else if(nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return result;

    }
}
