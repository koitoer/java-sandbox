package com.koitoer.java.let.array;

import org.junit.Test;

public class Solution5 {

    @Test
    public void test1() {
        removeDuplicates(new int[] { 1, 1, 1, 2, 2, 3 });
    }

    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;

        print(nums);
        return i;
    }

    //Trick here is have two cursor, one pointing to the last correct position.
    public int removeDuplicates(int[] nums) {

        //If the length is less than 3 we should not continue and the length is the value
        if (nums.length < 3)
            return nums.length;

        //Number of elements that are maximum two repeated values. This also is the pointer
        int count = 1;

        // Count duplicate elements
        int dup = 0;

        for (int i = 1; i < nums.length; i++) {

            //If the number is not equal to the previous
            if (nums[i] != nums[i - 1]) {
                //Swap value from i to the correct position and move to the next correct position
                nums[count] = nums[i];
                //Count as different element
                count++;
                //Reset the duplicate value
                dup = 0;
            } else if(dup == 0){
                //Swap value from i to the correct position and move to the next correct position
                nums[count] = nums[i];
                //Move the cursor
                count++;
                //Increase the duplicate counter.
                dup++;
            }
        }

        print(nums);

        return count;
    }

    public void swap(int a[], int original, int index) {
        int aux = a[original];
        a[original] = a[index];
        a[index] = aux;
    }

    public void print(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " : ");
        }
    }

}