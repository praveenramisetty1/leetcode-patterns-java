package com.leetcode.patterns.binarysearch;

/**
 * LeetCode 33: Search in Rotated Sorted Array
 *
 * Pattern: Modified Binary Search
 * Difficulty: Medium
 *
 * Problem: Given a rotated sorted array and a target value,
 * return the index of target if found, otherwise return -1.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class SearchInRotatedArray {

    /**
     * Searches in rotated sorted array using modified binary search.
     * Key insight: At least one half is always sorted.
     *
     * @param nums rotated sorted array
     * @param target target value
     * @return index of target or -1
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Determine which half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    // Target is in sorted left half
                    right = mid - 1;
                } else {
                    // Target is in right half
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    // Target is in sorted right half
                    left = mid + 1;
                } else {
                    // Target is in left half
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * Alternative: First find pivot, then do binary search.
     */
    public int searchWithPivot(int[] nums, int target) {
        int pivot = findPivot(nums);

        // If no rotation
        if (pivot == 0) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }

        // Determine which half to search
        if (target >= nums[0]) {
            return binarySearch(nums, 0, pivot - 1, target);
        } else {
            return binarySearch(nums, pivot, nums.length - 1, target);
        }
    }

    private int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedArray solution = new SearchInRotatedArray();

        // Example 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = " + target1);
        System.out.println("Output: " + solution.search(nums1, target1));
        System.out.println("Expected: 4\n");

        // Example 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = " + target2);
        System.out.println("Output: " + solution.search(nums2, target2));
        System.out.println("Expected: -1\n");

        // Example 3
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Input: nums = [1], target = " + target3);
        System.out.println("Output: " + solution.search(nums3, target3));
        System.out.println("Expected: -1");
    }
}
