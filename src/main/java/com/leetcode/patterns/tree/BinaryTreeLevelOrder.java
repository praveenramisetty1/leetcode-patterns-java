package com.leetcode.patterns.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102: Binary Tree Level Order Traversal
 *
 * Pattern: Tree BFS
 * Difficulty: Medium
 *
 * Problem: Given the root of a binary tree, return the level order traversal
 * of its nodes' values (i.e., from left to right, level by level).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(w) where w is max width of tree
 */
public class BinaryTreeLevelOrder {

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
     * BFS approach using queue for level order traversal.
     *
     * @param root root of binary tree
     * @return list of lists representing each level
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // Add children for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    /**
     * DFS approach (recursive) - tracks level depth.
     */
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsHelper(root, 0, result);
        return result;
    }

    private void dfsHelper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // Create new level list if needed
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }

        // Add current node to its level
        result.get(level).add(node.val);

        // Recurse on children
        dfsHelper(node.left, level + 1, result);
        dfsHelper(node.right, level + 1, result);
    }

    /**
     * Helper method to create tree from array (level order).
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
        BinaryTreeLevelOrder solution = new BinaryTreeLevelOrder();

        // Example 1: [3,9,20,null,null,15,7]
        TreeNode root1 = createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("Input: [3,9,20,null,null,15,7]");
        System.out.println("Output (BFS): " + solution.levelOrder(root1));
        System.out.println("Output (DFS): " + solution.levelOrderDFS(root1));
        System.out.println("Expected: [[3],[9,20],[15,7]]\n");

        // Example 2: [1]
        TreeNode root2 = createTree(new Integer[]{1});
        System.out.println("Input: [1]");
        System.out.println("Output (BFS): " + solution.levelOrder(root2));
        System.out.println("Expected: [[1]]\n");

        // Example 3: []
        TreeNode root3 = null;
        System.out.println("Input: []");
        System.out.println("Output (BFS): " + solution.levelOrder(root3));
        System.out.println("Expected: []");
    }
}
