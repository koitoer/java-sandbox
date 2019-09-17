package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution496 {

    @Test
    public void test1(){
        Assertions.assertThat(new Solution496().nextGreaterElement(new int[]{4,1,2}, new int[]{ 1,3,4,2}))
            .contains(new int[]{-1,3,-1});
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] r = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int currentElement = nums1[i];
            int index2 = 0;

            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == currentElement) {
                    index2 = j;
                    break;
                }
            }

            boolean found = false;

            for (int j = index2; j < nums2.length; j++) {
                if (nums2[j] > currentElement) {
                    found = true;
                    r[i] = nums2[j];
                    break;
                }
            }

            if (!found) {
                r[i] = -1;
            }
        }

        return r;

    }

}
