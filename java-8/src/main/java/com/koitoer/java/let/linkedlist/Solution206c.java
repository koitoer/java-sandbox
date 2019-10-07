package com.koitoer.java.let.linkedlist;

import org.junit.Test;

/**
 * 206. Reverse Linked List
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
 * Memory Usage: 36.9 MB, less than 98.92% of Java online submissions for Reverse Linked List.
 */
public class Solution206c {

    public class ListNode {

        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override public String toString() {
            return "ListNode{" +
                "val=" + val +
                '}';
        }
    }

    @Test
    public void test1() {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);

        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = null;

        ListNode aux = new Solution206c().reverseList(listNode);
    }

    /**
     * Recursive function.
     */
    public ListNode reverseList(ListNode head) {

        // If the head or the head.next are null let's return that value
        // which will be the end of the linked list.
        if (head == null || head.next == null) {
            return head;
        }

        //This value will be the output, the head.next will go internally to the linked list.
        ListNode p = reverseList(head.next);

        System.out.println(p + "  head -> " + head + " head.next -> " + head.next + " head.next.next " + head.next.next);

        //this will actually reverse the list
        head.next.next = head;
        //remove the previous link
        head.next = null;

        System.out.println(p + "  head -> " + head + " head.next -> " + head.next );

        return p;
    }

}
