package com.koitoer.java.let.design;

import java.util.PriorityQueue;

public class KthLargest {

    private PriorityQueue<Integer> pq;

    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue(k);
        for (int a : nums) {
            this.add(a);
        }
    }

    public int add(int val) {

        if (pq.size() < k) {
            pq.offer(val);
            return pq.peek();
        }

        if (pq.peek() == null || pq.peek() < val) {
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }

}
