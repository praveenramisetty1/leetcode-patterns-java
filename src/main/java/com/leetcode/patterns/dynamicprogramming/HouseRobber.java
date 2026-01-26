package com.leetcode.patterns.dynamicprogramming;

/**
 * LeetCode 198: House Robber
 *
 * Pattern: Dynamic Programming (1D)
 * Difficulty: Medium
 *
 * Problem: You are a robber planning to rob houses along a street.
 * Each house has a certain amount of money. Adjacent houses have security systems
 * that will alert police if two adjacent houses are broken into on the same night.
 * Given an array representing the amount of money in each house,
 * return the maximum amount you can rob without alerting the police.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) for tabulation, O(1) for optimized
 */
public class HouseRobber {

    /**
     * Approach 1: Dynamic Programming (Tabulation)
     * dp[i] = max money that can be robbed up to house i
     * dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     *
     * @param nums array of money in each house
     * @return maximum amount that can be robbed
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            // Either skip current house or rob it (and skip previous)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    /**
     * Approach 2: Space Optimized DP
     * Only need last two values.
     */
    public int robOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int prev2 = nums[0];  // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]);  // dp[i-1]

        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * Approach 3: Even more optimized with better variable names
     */
    public int robCleaner(int[] nums) {
        int rob = 0;      // Max money if we rob current house
        int notRob = 0;   // Max money if we don't rob current house

        for (int num : nums) {
            int newRob = notRob + num;  // Rob current + max from not robbing previous
            int newNotRob = Math.max(rob, notRob);  // Max of robbing or not robbing previous

            rob = newRob;
            notRob = newNotRob;
        }

        return Math.max(rob, notRob);
    }

    /**
     * Java 17+ approach using records for state
     */
    record RobState(int robbed, int notRobbed) {
        int max() {
            return Math.max(robbed, notRobbed);
        }
    }

    public int robModern(int[] nums) {
        RobState state = new RobState(0, 0);

        for (int num : nums) {
            state = new RobState(
                state.notRobbed + num,
                Math.max(state.robbed, state.notRobbed)
            );
        }

        return state.max();
    }

    public static void main(String[] args) {
        HouseRobber solution = new HouseRobber();

        // Example 1
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Input: [1,2,3,1]");
        System.out.println("Output (DP): " + solution.rob(nums1));
        System.out.println("Output (Optimized): " + solution.robOptimized(nums1));
        System.out.println("Output (Cleaner): " + solution.robCleaner(nums1));
        System.out.println("Expected: 4 (rob house 1 and 3)\n");

        // Example 2
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("Input: [2,7,9,3,1]");
        System.out.println("Output (DP): " + solution.rob(nums2));
        System.out.println("Output (Optimized): " + solution.robOptimized(nums2));
        System.out.println("Output (Cleaner): " + solution.robCleaner(nums2));
        System.out.println("Expected: 12 (rob house 1, 3, and 5)");
    }
}
