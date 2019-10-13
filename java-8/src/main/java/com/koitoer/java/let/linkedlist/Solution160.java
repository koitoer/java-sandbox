package com.koitoer.java.let.linkedlist;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Solution160 {

    @Test
    public void test() {
        ListNode l0 = new ListNode(0);
        ListNode l1 = new ListNode(1);
        ListNode l9 = new ListNode(9);
        ListNode l2 = new ListNode(2);
        ListNode l4 = new ListNode(4);
        ListNode l3 = new ListNode(3);

        l0.next = l1;
        l1.next = l9;
        l9.next = l2;
        l2.next = l4;
        l3.next = l2;

        Assertions.assertThat(new Solution160().getIntersectionNode(l0, l3)).isEqualTo(l2);
        Assertions.assertThat(new Solution160().getIntersectionNode(l0, l3)).isEqualTo(l2);

    }

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode a = headA;
        while (a != null) {
            ListNode b = headB;
            while (b != null) {
                if (a.val == b.val) {
                    return a;
                }
                b = b.next;
            }
            a = a.next;
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Map<Integer, ListNode> list = new HashMap();
        ListNode a = headA;
        while (a != null) {
            list.put(a.val, a);
            a = a.next;
        }

        ListNode b = headB;
        while (b != null) {
            ListNode aux = list.get(b.val);
            if (aux != null) {
                return aux;
            }
            b = b.next;
        }

        return null;
    }

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
