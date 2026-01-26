package com.leetcode.patterns.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 104: Maximum Depth of Binary Tree
 *
 * Pattern: Tree DFS/BFS
 * Difficulty: Easy
 *
 * Problem: Given the root of a binary tree, return its maximum depth.
 * Maximum depth is the number of nodes along the longest path from root to leaf.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h) for DFS, O(w) for BFS
 */
public class MaximumDepth {

    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * DFS approach (recursive) - most intuitive.
     *
     * @param root root of binary tree
     * @return maximum depth
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * BFS approach using level order traversal.
     */
    public int maxDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return depth;
    }

    /**
     * Java 17+ approach using ternary operator (cleaner than if-else)
     */
    public int maxDepthModern(TreeNode root) {
        return root == null ? 0 :
            1 + Math.max(maxDepthModern(root.left), maxDepthModern(root.right));
    }

    /**
     * Helper method to create tree from array.
     */
    private static TreeNode createTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();

            if (i < values.length && values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        MaximumDepth solution = new MaximumDepth();

        // Example 1: [3,9,20,null,null,15,7]
        TreeNode root1 = createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("Input: [3,9,20,null,null,15,7]");
        System.out.println("Output (DFS): " + solution.maxDepth(root1));
        System.out.println("Output (BFS): " + solution.maxDepthBFS(root1));
        System.out.println("Expected: 3\n");

        // Example 2: [1,null,2]
        TreeNode root2 = createTree(new Integer[]{1, null, 2});
        System.out.println("Input: [1,null,2]");
        System.out.println("Output (DFS): " + solution.maxDepth(root2));
        System.out.println("Output (BFS): " + solution.maxDepthBFS(root2));
        System.out.println("Expected: 2");
    }
}
