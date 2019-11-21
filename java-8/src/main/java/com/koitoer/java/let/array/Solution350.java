package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 350. Intersection of Two Arrays II
 * One approach is using a HashMap to track the count of the element and then reduce that count but also add to a list of results.
 * A better approach is have two pointers one the arrays have been sorted. But also a count pointer to increment the store position.
 */
class Solution350 {

    public int[] intersect(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int p1 = 0, p2 = 0, count = 0;
        //Create an array as large as the larger array.
        int[] tmp = new int[nums1.length >= nums2.length ? nums1.length : nums2.length], res;

        //Until one of the arrays is exhausted.
        while (p1 < nums1.length && p2 < nums2.length) {
            //If there are equals move two cursors and save position.
            if (nums1[p1] == nums2[p2]) {
                tmp[count++] = nums1[p1];
                p1++;
                p2++;
            //In this case o
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }

        int[] r = new int[count];
        for (int i = 0; i < count; i++) {
            r[i] = tmp[i];
        }

        return r;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList();
        Map<Integer, Integer> map = new HashMap();

        for (int elem : nums1) {
            map.put(elem, map.getOrDefault(elem, 0) + 1);
        }

        for (int elem : nums2) {

            if (map.size() == 0) {
                break;
            }

            if (map.get(elem) != null && map.get(elem) > 0) {
                map.put(elem, map.get(elem) - 1);
                result.add(elem);
            }
        }

        Iterator<Integer> itr = result.iterator();
        int[] r = new int[result.size()];
        int i = 0;
        while (itr.hasNext()) {
            r[i] = itr.next();
            i++;
        }

        return r;
    }

}
