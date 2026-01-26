package com.leetcode.patterns.topkelements;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * LeetCode 215: Kth Largest Element in an Array
 *
 * Pattern: Top K Elements
 * Difficulty: Medium
 *
 * Problem: Find the kth largest element in an unsorted array.
 *
 * Time Complexity: O(n log k) with heap, O(n) average with QuickSelect
 * Space Complexity: O(k) with heap, O(1) with QuickSelect
 */
public class KthLargestElement {

    /**
     * Approach 1: Min Heap of size k
     * Maintains k largest elements, root is kth largest.
     *
     * @param nums input array
     * @param k kth position
     * @return kth largest element
     */
    public int findKthLargestHeap(int[] nums, int k) {
        // Min heap to keep k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            // Keep only k largest elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Root of min heap is kth largest
        return minHeap.peek();
    }

    /**
     * Approach 2: QuickSelect (Hoare's selection algorithm)
     * Average O(n), worst case O(n²) but can be optimized.
     */
    public int findKthLargest(int[] nums, int k) {
        // Convert to 0-indexed: kth largest = (n-k)th smallest
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        // Random pivot for better average performance
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);

        pivotIndex = partition(nums, left, right, pivotIndex);

        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];

        // Move pivot to end
        swap(nums, pivotIndex, right);

        int storeIndex = left;

        // Move all smaller elements to left
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // Move pivot to final position
        swap(nums, storeIndex, right);

        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Java 17 approach using streams
     */
    public int findKthLargestModern(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();

        // Example 1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Input: [3,2,1,5,6,4], k = " + k1);
        System.out.println("Output (Heap): " + solution.findKthLargestHeap(nums1, k1));
        System.out.println("Output (QuickSelect): " + solution.findKthLargest(nums1.clone(), k1));
        System.out.println("Expected: 5\n");

        // Example 2
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println("Input: [3,2,3,1,2,4,5,5,6], k = " + k2);
        System.out.println("Output (Heap): " + solution.findKthLargestHeap(nums2, k2));
        System.out.println("Output (QuickSelect): " + solution.findKthLargest(nums2.clone(), k2));
        System.out.println("Expected: 4");
    }
}
