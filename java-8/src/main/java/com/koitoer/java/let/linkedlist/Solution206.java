package com.koitoer.java.let.linkedlist;

import org.junit.Test;

public class Solution206 {

    public class ListNode {

        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
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

        ListNode aux = new Solution206().reverseList(listNode);
    }

    public ListNode end;

    public ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        } else if (head.next == null) {
            return head;
        }
        visit(head);

        return end;
    }

    public ListNode visit(ListNode li) {
        if (li.next == null) {
            ListNode listNode = new ListNode(li.val);
            end = listNode;
            return listNode;
        }
        ListNode listNode = visit(li.next);
        if (listNode != null) {
            listNode.next = new ListNode(li.val);
        }
        return listNode.next;
    }

}
