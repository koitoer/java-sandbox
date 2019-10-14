package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 162. Find Peak Element
 * We are in almost a sorted array we need to find any peak.
 * Note do this in log time, rather than linear
 */
public class Solution389 {

    @Test
    public void test(){
        int a[] = {1,2,1,3,5,6,4};
        Assertions.assertThat(new Solution389().findPeakElement(a)).isIn(1,5);
    }

    /**
     * To do log time we need to use binary search, we use to pointers, one left and one right
     * 1 2 3  -- 4 -- 5
     * left <= middle
     * right >= middle
     *
     * [1,2,1,3,5,6,4]
     * Ite 0 - left = 0 - right = 6 -- middle = 3
     * Ite 1 - left = 4 - right = 6 -- middle = 5
     * Ite 2 - left = 4 - right = 5 -- middle = 4
     *
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length -1;

        //We stop once the cursors does not match this condition.
        while(left < right){

            //Get the middle element
            int middle = left + (right - left) / 2;

            if(nums[middle] < nums[middle + 1]){
                left= middle + 1;
            }else{
                right = middle;
            }
        }

        return left;
    }


}