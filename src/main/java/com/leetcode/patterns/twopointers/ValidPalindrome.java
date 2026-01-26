package com.leetcode.patterns.twopointers;

/**
 * LeetCode 125: Valid Palindrome
 *
 * Pattern: Two Pointers
 * Difficulty: Easy
 *
 * Problem: Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ValidPalindrome {

    /**
     * Checks if a string is a valid palindrome.
     * Uses two pointers from both ends, comparing alphanumeric characters.
     *
     * @param s input string
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /**
     * Java 17+ version using modern features
     */
    public boolean isPalindromeModern(String s) {
        // Filter and normalize the string
        String normalized = s.chars()
            .filter(Character::isLetterOrDigit)
            .mapToObj(c -> Character.toLowerCase((char) c))
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();

        // Check if it's equal to its reverse
        return normalized.contentEquals(new StringBuilder(normalized).reverse());
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();

        // Example 1
        String s1 = "A man, a plan, a canal: Panama";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: " + solution.isPalindrome(s1));
        System.out.println("Expected: true\n");

        // Example 2
        String s2 = "race a car";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: " + solution.isPalindrome(s2));
        System.out.println("Expected: false\n");

        // Example 3
        String s3 = " ";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: " + solution.isPalindrome(s3));
        System.out.println("Expected: true");
    }
}
