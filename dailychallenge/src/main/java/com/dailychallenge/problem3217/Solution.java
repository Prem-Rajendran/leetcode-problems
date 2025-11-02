package com.dailychallenge.problem3217;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static void printLinkedList(ListNode head) {
        System.out.println();
        while (head != null){
            System.out.print(head.val);
            System.out.print(" -> ");
            head = head.next;
        }
        System.out.print("NULL");
    }

    private static Set<Integer> convertToSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums){
            set.add(i);
        }

        return set;
    }

    private static ListNode modifiedList(int[] nums, ListNode head) {
        var set = convertToSet(nums);
        ListNode prev = new ListNode(0, head);
        head = prev;

        while (prev.next != null) {
            if (set.contains(prev.next.val)) {
                prev.next = prev.next.next;
            }
            else {
                prev = prev.next;
            }
        }

        return head.next;
    }

    public static void main(String[] args) {
        System.out.println("3217. Delete Nodes From Linked List Present in Array");

        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int[] nums = {1,2,5};

        var modifiedNode = modifiedList(nums, node);
        printLinkedList(modifiedNode);
    }
}