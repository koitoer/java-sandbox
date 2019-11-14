package com.koitoer.java.let.linkedlist;

/**
 * 141. Linked List Cycle
 * We use two pointers a slow and a faster, for each movement of the slow, two are being done by the faster
 * Eventually if there is any loop they will find each other.
 */
public class Solution141 {

    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode p1 = head;
        ListNode p2 = head.next.next;

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next == null ? null : p2.next.next;

            if (p1 == p2) {
                return true;
            }
        }

        return false;

    }

    /**
     * Only change is check for null is being done in the if and switch statements.
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode p1 = head;
        ListNode p2 = head.next.next;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;

            if (p1 == p2) {
                return true;
            }
        }

        return false;
    }
}
