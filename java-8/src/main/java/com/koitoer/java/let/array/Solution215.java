package com.koitoer.java.let.array;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution215 {

    @Test
    public void test() {
        int nums[] = { 3, 2, 1, 5, 6, 4 };
        Assertions.assertThat(new Solution215().findKthLargest(nums, 2)).isEqualTo(5);
    }

    //Solution using a min-heap
    //Priority queue will store the elements in a semi-order will return them in order.
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int a : nums) {
            pq.add(a);
            if (pq.size() > k) {
                pq.remove();
            }
        }
        // Each poll will return the lower priority.
        return pq.poll();
    }

    //Solution sorting the array then pick length - k
    public int findKthLargest2(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
