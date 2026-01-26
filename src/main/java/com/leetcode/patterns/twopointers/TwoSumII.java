package com.leetcode.patterns.twopointers;

import java.util.Arrays;

/**
 * LeetCode 167: Two Sum II - Input Array Is Sorted
 *
 * Pattern: Two Pointers
 * Difficulty: Medium
 *
 * Problem: Given a 1-indexed array of integers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class TwoSumII {

    /**
     * Finds two numbers in a sorted array that add up to target.
     * Uses two pointers approach - one at start, one at end.
     *
     * @param numbers sorted array of integers (1-indexed in problem, 0-indexed here)
     * @param target the target sum
     * @return array of two indices (1-indexed) where numbers add up to target
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // Return 1-indexed positions
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                // Need larger sum, move left pointer right
                left++;
            } else {
                // Need smaller sum, move right pointer left
                right--;
            }
        }

        // No solution found (problem guarantees exactly one solution)
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        TwoSumII solution = new TwoSumII();

        // Example 1
        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Input: " + Arrays.toString(numbers1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(numbers1, target1)));
        System.out.println("Expected: [1, 2]\n");

        // Example 2
        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        System.out.println("Input: " + Arrays.toString(numbers2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(numbers2, target2)));
        System.out.println("Expected: [1, 3]\n");

        // Example 3
        int[] numbers3 = {-1, 0};
        int target3 = -1;
        System.out.println("Input: " + Arrays.toString(numbers3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(numbers3, target3)));
        System.out.println("Expected: [1, 2]");
    }
}
