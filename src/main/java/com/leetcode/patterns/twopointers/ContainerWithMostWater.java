package com.leetcode.patterns.twopointers;

/**
 * LeetCode 11: Container With Most Water
 *
 * Pattern: Two Pointers
 * Difficulty: Medium
 *
 * Problem: Given n non-negative integers representing heights of vertical lines,
 * find two lines that together with the x-axis form a container that holds the most water.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ContainerWithMostWater {

    /**
     * Finds the maximum area of water that can be contained.
     * Uses two pointers starting from both ends, moving the pointer with smaller height.
     *
     * @param height array of heights
     * @return maximum water area
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // Calculate current area
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentArea = width * currentHeight;

            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer with smaller height
            // This is the key insight: moving the taller pointer can only decrease area
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Example 1
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Input: [1,8,6,2,5,4,8,3,7]");
        System.out.println("Output: " + solution.maxArea(height1));
        System.out.println("Expected: 49\n");

        // Example 2
        int[] height2 = {1, 1};
        System.out.println("Input: [1,1]");
        System.out.println("Output: " + solution.maxArea(height2));
        System.out.println("Expected: 1");
    }
}
