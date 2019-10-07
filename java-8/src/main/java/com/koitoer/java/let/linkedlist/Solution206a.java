package com.koitoer.java.let.linkedlist;

import org.junit.Test;

public class Solution206a {

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

        ListNode aux = new Solution206a().reverseList(listNode);
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

    //prev = null -> prev.next = null -> current = 1 --> current.next = 2
    //aux = 2 : current.next = null ; prev = 1 ; current = 2
    //aux = 3 : current.next = 1 ; prev = 2 ; current = 3
    //aux = 4 : current.next = 2 ; prev = 3 ; current = 4
    //aux = 5 : current.next = 3 ; prev = 4 ; current = 5
    //aux = NULL : current.next = 4 ; prev = 5 ; current = NULL
}
