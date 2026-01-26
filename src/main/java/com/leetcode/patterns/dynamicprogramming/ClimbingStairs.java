package com.leetcode.patterns.dynamicprogramming;

/**
 * LeetCode 70: Climbing Stairs
 *
 * Pattern: Dynamic Programming (1D)
 * Difficulty: Easy
 *
 * Problem: You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) for tabulation, O(1) for optimized
 */
public class ClimbingStairs {

    /**
     * Approach 1: Dynamic Programming (Tabulation)
     * dp[i] = number of ways to reach step i
     * dp[i] = dp[i-1] + dp[i-2]
     *
     * @param n number of steps
     * @return number of distinct ways
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;  // 1 way to reach step 1
        dp[2] = 2;  // 2 ways to reach step 2

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Approach 2: Space Optimized DP
     * Only need last two values, not entire array.
     */
    public int climbStairsOptimized(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1;  // dp[i-2]
        int prev1 = 2;  // dp[i-1]

        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * Approach 3: Memoization (Top-Down)
     */
    public int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        return climbStairsHelper(n, memo);
    }

    private int climbStairsHelper(int n, int[] memo) {
        if (n <= 2) {
            return n;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Mathematical approach: This is actually Fibonacci sequence!
     * Can use matrix exponentiation for O(log n) time.
     */
    public int climbStairsFibonacci(int n) {
        if (n <= 2) {
            return n;
        }

        // Using golden ratio formula (less precise for large n)
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;

        return (int) Math.round((Math.pow(phi, n + 1) - Math.pow(psi, n + 1)) / sqrt5);
    }

    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();

        // Example 1
        int n1 = 2;
        System.out.println("Input: n = " + n1);
        System.out.println("Output (DP): " + solution.climbStairs(n1));
        System.out.println("Output (Optimized): " + solution.climbStairsOptimized(n1));
        System.out.println("Output (Memo): " + solution.climbStairsMemo(n1));
        System.out.println("Expected: 2\n");

        // Example 2
        int n2 = 3;
        System.out.println("Input: n = " + n2);
        System.out.println("Output (DP): " + solution.climbStairs(n2));
        System.out.println("Output (Optimized): " + solution.climbStairsOptimized(n2));
        System.out.println("Output (Memo): " + solution.climbStairsMemo(n2));
        System.out.println("Expected: 3\n");

        // Example 3
        int n3 = 5;
        System.out.println("Input: n = " + n3);
        System.out.println("Output (DP): " + solution.climbStairs(n3));
        System.out.println("Output (Optimized): " + solution.climbStairsOptimized(n3));
        System.out.println("Output (Memo): " + solution.climbStairsMemo(n3));
        System.out.println("Expected: 8");
    }
}
