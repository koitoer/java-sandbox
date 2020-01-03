package com.koitoer.java.let.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution665 {

    @Test
    public void test() {
        int a[] = new int[] { -1, 4, 2, 3 };
        Assertions.assertThat(new Solution665().checkPossibility(a)).isTrue();
    }

    @Test
    public void testA() {
        int a[] = new int[] { 3, 4, 2, 3 };
        Assertions.assertThat(new Solution665().checkPossibility(a)).isFalse();
    }

    @Test
    public void testB() {
        int a[] = new int[] { 3, 3, 2, 2 };
        Assertions.assertThat(new Solution665().checkPossibility(a)).isFalse();
    }

    @Test
    public void testC() {
        int a[] = new int[] { 1, 1, 1 };
        Assertions.assertThat(new Solution665().checkPossibility(a)).isTrue();
    }

    @Test
    public void testD() {
        int a[] = new int[] { 4, 2, 1 };
        Assertions.assertThat(new Solution665().checkPossibility(a)).isFalse();
    }

    public boolean checkPossibility(int[] nums) {
        int count = 0;
        int index = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                index = i;
                count++;
            }
        }

        if (count == 0) {
            return true;
        }

        if (count >= 2) {
            return false;
        }

        if (index == 0 || index == nums.length - 2) {
            return true;
        }

        //At this point we only have two possibilities.
        //We could change A[p] to be between A[p-1] and A[p+1] OR
        //We could change A[p+1] to be between A[p] and A[p+2]

        return nums[index - 1] <= nums[index + 1] || nums[index] <= nums[index + 2];
        // Ex (-1, 4, 2, 3) index = 1  [-1 <= 2] YES || [4 <= 3 ] NO return true;
        // Ex (3, 4, 2, 3) index = 1  [3 <= 2] NO || [4 <= 3 ] NO  return false;
        // Ex (3, 3, 2, 2) index = 1  [3 <= 2] NO || [3 <= 2 ] NO  return false;
    }

    /**
     def checkPossibility(self, A):
     p = None
     for i in xrange(len(A) - 1):
     if A[i] > A[i+1]:
     if p is not None:
     return False
     p = i

     return (p is None or p == 0 or p == len(A)-2 or
     A[p-1] <= A[p+1] or A[p] <= A[p+2])
     **/
}
