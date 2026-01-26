package com.leetcode.patterns.binarysearch;

/**
 * LeetCode 704: Binary Search
 *
 * Pattern: Binary Search
 * Difficulty: Easy
 *
 * Problem: Given a sorted array of integers and a target value,
 * return the index if target is found. If not, return -1.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class BinarySearch {

    /**
     * Classic binary search implementation.
     *
     * @param nums sorted array
     * @param target target value
     * @return index of target or -1 if not found
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Avoid overflow: use left + (right - left) / 2
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;  // Search right half
            } else {
                right = mid - 1;  // Search left half
            }
        }

        return -1;  // Not found
    }

    /**
     * Recursive binary search (for educational purposes).
     */
    public int searchRecursive(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }

    private int binarySearchHelper(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return binarySearchHelper(nums, target, mid + 1, right);
        } else {
            return binarySearchHelper(nums, target, left, mid - 1);
        }
    }

    public static void main(String[] args) {
        BinarySearch solution = new BinarySearch();

        // Example 1
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        System.out.println("Input: nums = [-1,0,3,5,9,12], target = " + target1);
        System.out.println("Output: " + solution.search(nums1, target1));
        System.out.println("Expected: 4\n");

        // Example 2
        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        System.out.println("Input: nums = [-1,0,3,5,9,12], target = " + target2);
        System.out.println("Output: " + solution.search(nums2, target2));
        System.out.println("Expected: -1");
    }
}
