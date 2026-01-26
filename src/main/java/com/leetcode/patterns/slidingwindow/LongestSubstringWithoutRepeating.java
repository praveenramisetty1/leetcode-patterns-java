package com.leetcode.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3: Longest Substring Without Repeating Characters
 *
 * Pattern: Sliding Window (Variable Size)
 * Difficulty: Medium
 *
 * Problem: Given a string, find the length of the longest substring
 * without repeating characters.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(min(n, m)) where m is charset size
 */
public class LongestSubstringWithoutRepeating {

    /**
     * Finds length of longest substring without repeating characters.
     * Uses sliding window with HashMap to track character positions.
     *
     * @param s input string
     * @return length of longest substring
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);

            // If character is already in window, move start pointer
            if (charIndexMap.containsKey(rightChar)) {
                // Move windowStart to the right of the last occurrence
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            }

            // Update character's latest position
            charIndexMap.put(rightChar, windowEnd);

            // Calculate current window length
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    /**
     * Alternative approach using array for ASCII characters (faster).
     */
    public int lengthOfLongestSubstringOptimized(String s) {
        int[] charIndex = new int[128]; // ASCII characters
        int maxLength = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);

            // Update window start if character was seen
            windowStart = Math.max(windowStart, charIndex[rightChar]);

            // Update max length
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);

            // Store next position (1-indexed to handle default 0)
            charIndex[rightChar] = windowEnd + 1;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();

        // Example 1
        String s1 = "abcabcbb";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: " + solution.lengthOfLongestSubstring(s1));
        System.out.println("Expected: 3 (\"abc\")\n");

        // Example 2
        String s2 = "bbbbb";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: " + solution.lengthOfLongestSubstring(s2));
        System.out.println("Expected: 1 (\"b\")\n");

        // Example 3
        String s3 = "pwwkew";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: " + solution.lengthOfLongestSubstring(s3));
        System.out.println("Expected: 3 (\"wke\")");
    }
}
