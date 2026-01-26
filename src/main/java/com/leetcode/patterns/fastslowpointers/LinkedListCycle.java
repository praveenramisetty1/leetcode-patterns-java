package com.leetcode.patterns.fastslowpointers;

/**
 * LeetCode 141: Linked List Cycle
 *
 * Pattern: Fast & Slow Pointers (Floyd's Cycle Detection)
 * Difficulty: Easy
 *
 * Problem: Given head of a linked list, determine if the linked list has a cycle.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LinkedListCycle {

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
     * Detects if linked list has a cycle using Floyd's algorithm.
     * Fast pointer moves 2 steps, slow pointer moves 1 step.
     * If they meet, there's a cycle.
     *
     * @param head head of linked list
     * @return true if cycle exists, false otherwise
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps

            if (slow == fast) {
                return true;  // Cycle detected
            }
        }

        return false;  // No cycle
    }

    /**
     * Finds the start of the cycle if it exists.
     * After detecting cycle, reset one pointer to head and move both at same speed.
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Cycle found, now find the start
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;  // Start of cycle
            }
        }

        return null;  // No cycle
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Example 1: Cycle exists
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;  // Creates cycle

        System.out.println("Example 1: [3,2,0,-4] with cycle at position 1");
        System.out.println("Has cycle: " + solution.hasCycle(head1));
        System.out.println("Expected: true\n");

        // Example 2: No cycle
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);

        System.out.println("Example 2: [1,2] with no cycle");
        System.out.println("Has cycle: " + solution.hasCycle(head2));
        System.out.println("Expected: false");
    }
}
