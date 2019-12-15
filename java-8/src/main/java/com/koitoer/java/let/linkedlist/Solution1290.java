package com.koitoer.java.let.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1290. Convert Binary Number in a Linked List to Integer
 * We iterate over the collection and collect the numbers in a list.
 * We reverse the list and execute the operation (num[i] * Math.pow(2,i));
 */
public class Solution1290 {

    public int getDecimalValue(ListNode head) {
        if (head == null) {
            return 0;
        } else if (head.next == null) {
            return head.val;
        }
        int result = 0;
        ListNode node = head;
        List<Integer> list = new ArrayList();

        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            double pot = Math.pow(2, i);
            result += pot * list.get(i);
        }

        return result;
    }
}
