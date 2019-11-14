package com.koitoer.java.let.linkedlist;

/**
 * 19. Remove Nth Node From End of List
 */
public class Solution19 {

    /**
     * We need to use a dummy record at the begging of the list.
     * When we reach the condition we just need to skip that node.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode d = new ListNode(0);
        d.next = head;

        ListNode p1 = d;
        ListNode p2 = d;

        for (int i = 0; i <= n; i++) {
            p2 = p2.next;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        //We just skip the element.
        p1.next = p1.next.next;
        return d.next;
    }
}
