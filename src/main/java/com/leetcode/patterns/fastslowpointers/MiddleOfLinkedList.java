package com.leetcode.patterns.fastslowpointers;

/**
 * LeetCode 876: Middle of the Linked List
 *
 * Pattern: Fast & Slow Pointers
 * Difficulty: Easy
 *
 * Problem: Given the head of a singly linked list, return the middle node.
 * If there are two middle nodes, return the second middle node.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MiddleOfLinkedList {

    /**
     * Definition for singly-linked list node.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * Finds the middle node using fast & slow pointers.
     * When fast reaches end, slow is at middle.
     *
     * @param head head of linked list
     * @return middle node
     */
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Fast moves 2 steps, slow moves 1 step
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;  // Slow is at middle
    }

    /**
     * Helper method to create linked list from array.
     */
    private static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Helper method to print list from a node.
     */
    private static void printList(ListNode node) {
        System.out.print("[");
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(",");
            }
            node = node.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        MiddleOfLinkedList solution = new MiddleOfLinkedList();

        // Example 1: Odd length
        int[] values1 = {1, 2, 3, 4, 5};
        ListNode head1 = createList(values1);
        System.out.println("Input: [1,2,3,4,5]");
        System.out.print("Output: ");
        printList(solution.middleNode(head1));
        System.out.println("Expected: [3,4,5]\n");

        // Example 2: Even length
        int[] values2 = {1, 2, 3, 4, 5, 6};
        ListNode head2 = createList(values2);
        System.out.println("Input: [1,2,3,4,5,6]");
        System.out.print("Output: ");
        printList(solution.middleNode(head2));
        System.out.println("Expected: [4,5,6]");
    }
}
