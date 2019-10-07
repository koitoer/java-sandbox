package com.koitoer.java.let.linkedlist;

import java.util.Stack;

import org.junit.Test;

/**
 * 206. Reverse Linked List
 * Runtime: 1 ms, faster than 5.54% of Java online submissions for Reverse Linked List.
 * Memory Usage: 36.9 MB, less than 98.92% of Java online submissions for Reverse Linked List.
 */
public class Solution206b {

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

        ListNode aux = new Solution206b().reverseList(listNode);
    }

    /**
     * Using a stack
     *
     */
    public ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        Stack<ListNode> listNodeStack = new Stack();
        ListNode current = head;

        while (current != null) {
            //push element to the stack
            listNodeStack.push(current);
            current = current.next;
        }

        //Push all the element to the stack.

        //Mark the new head to the pop element of the stack
        ListNode newHead = listNodeStack.pop();
        //Avoid lost the new Head use and aux that will change in each iteration
        ListNode aux = newHead;
        //Until we take everything out of the stack
        while (!listNodeStack.empty()) {
            //Pop another element
            ListNode p = listNodeStack.pop();
            //previous pop element is going to be the next value.
            aux.next = p;
            //reset the aux to be the last element we pop
            aux = p;
        }
        //Avoid infinite loop mark the end of the linked list.
        aux.next = null;

        return newHead;
    }

}
