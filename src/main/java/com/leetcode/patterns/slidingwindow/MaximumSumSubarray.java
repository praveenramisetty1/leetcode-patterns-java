package com.leetcode.patterns.slidingwindow;

/**
 * Maximum Sum Subarray of Size K
 *
 * Pattern: Sliding Window (Fixed Size)
 * Difficulty: Easy
 *
 * Problem: Given an array of integers and a number k, find the maximum sum
 * of any contiguous subarray of size k.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MaximumSumSubarray {

    /**
     * Finds maximum sum of subarray of size k using sliding window.
     *
     * @param arr input array
     * @param k window size
     * @return maximum sum
     */
    public int maxSumSubarray(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            throw new IllegalArgumentException("Invalid input");
        }

        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        // Slide the window: add next element, remove first element
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum + arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSumSubarray solution = new MaximumSumSubarray();

        // Example 1
        int[] arr1 = {2, 1, 5, 1, 3, 2};
        int k1 = 3;
        System.out.println("Input: [2,1,5,1,3,2], k = " + k1);
        System.out.println("Output: " + solution.maxSumSubarray(arr1, k1));
        System.out.println("Expected: 9 (subarray [5,1,3])\n");

        // Example 2
        int[] arr2 = {2, 3, 4, 1, 5};
        int k2 = 2;
        System.out.println("Input: [2,3,4,1,5], k = " + k2);
        System.out.println("Output: " + solution.maxSumSubarray(arr2, k2));
        System.out.println("Expected: 7 (subarray [3,4])");
    }
}
