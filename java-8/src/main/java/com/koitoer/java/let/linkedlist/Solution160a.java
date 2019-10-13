package com.koitoer.java.let.linkedlist;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution160a {

    @Test
    public void test() {
        ListNode l0 = new ListNode(0);
        ListNode l1 = new ListNode(1);
        ListNode l9 = new ListNode(9);
        ListNode l2 = new ListNode(2);
        ListNode l4 = new ListNode(4);
        ListNode l3 = new ListNode(3);

        l0.next = l9;
        l9.next = l1;
        l1.next = l2;
        l2.next = l4;
        l3.next = l2;

        Assertions.assertThat(new Solution160a().getIntersectionNode(l0, l3)).isEqualTo(l2);
        //Assertions.assertThat(new Solution160a().getIntersectionNode(l0, l3)).isEqualTo(l2);

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode rA = reverseList(headA);
        ListNode rB = reverseList(headB);

        ListNode aux = null;
        while (rA.val == rB.val) {
            rA = rA.next;
            rB = rB.next;
            aux = rA;
        }

        return aux;
    }

    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            //Save the next element in auxiliary variable
            ListNode aux = current.next;
            //The current next will be the prev
            current.next = prev;
            //The prev will be the current value
            prev = current;
            //The current value will be the next value.
            current = aux;
        }

        return prev;
    }


    class ListNode {

        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override public String toString() {
            return "ListNode{" +
                "val=" + val +
                '}';
        }
    }
}
