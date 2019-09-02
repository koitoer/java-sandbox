package com.koitoer.java.java8;

import java.util.ArrayList;
import java.util.List;

public class ABc {

    class ListNode {

        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int realValue = 807;
        System.out.println("bbccb " + realValue);
        String r = Integer.valueOf(realValue).toString();

        r.charAt(0);
        System.out.println("bbb " + r.charAt(0));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode l1aux = l1;
        ListNode l2aux = l2;
        int exp = 0;
        int realValue = 0;
        int carrier = 0;

        List<Integer> values = new ArrayList();
        while (l1aux != null || l2aux != null || carrier != 0) {

            int val1 = l1aux == null ? 0 : l1aux.val;
            int val2 = l2aux == null ? 0 : l2aux.val;
            int value = val1 + val2 + carrier;
            carrier = 0;

            if (value >= 10) {
                carrier = carrier + 1;
                value = value - 10;
            } else {
                carrier = 0;
                value = value;
            }

            values.add(value);
            l1aux = l1aux == null ? null : l1aux.next;
            l2aux = l2aux == null ? null : l2aux.next;
        }

        //ListNode result = new ListNode(values.get(0));
        ListNode first = new ListNode(values.get(0));
        ListNode aux = first;
        for (int i = 1; i < values.size(); i++) {
            ListNode nuevo = new ListNode(values.get(i));
            aux.next = nuevo;
            aux = aux.next;
        }

        return first;
    }


     class LinkedList {

         ListNode head;

         public LinkedList(int value) {

             head = new ListNode(value);

         }
     }
}
