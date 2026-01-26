package com.leetcode.patterns.topkelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 347: Top K Frequent Elements
 *
 * Pattern: Top K Elements
 * Difficulty: Medium
 *
 * Problem: Given an integer array and an integer k,
 * return the k most frequent elements.
 *
 * Time Complexity: O(n log k) with heap, O(n) with bucket sort
 * Space Complexity: O(n)
 */
public class TopKFrequentElements {

    /**
     * Approach 1: Min Heap based on frequency
     */
    public int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Min heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // Keep k most frequent elements
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Extract results
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }

    /**
     * Approach 2: Bucket Sort (O(n) time)
     * Use frequency as bucket index.
     */
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create buckets: index = frequency, value = list of numbers
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Fill buckets
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int frequency = entry.getValue();
            buckets[frequency].add(entry.getKey());
        }

        // Collect top k from highest frequency buckets
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            result.addAll(buckets[i]);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Java 17+ approach using streams and modern features
     */
    public int[] topKFrequentModern(int[] nums, int k) {
        return Arrays.stream(nums)
            .boxed()
            .collect(HashMap<Integer, Long>::new,
                    (map, num) -> map.merge(num, 1L, Long::sum),
                    HashMap::putAll)
            .entrySet()
            .stream()
            .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
            .limit(k)
            .mapToInt(Map.Entry::getKey)
            .toArray();
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();

        // Example 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println("Input: [1,1,1,2,2,3], k = " + k1);
        System.out.println("Output (Heap): " + Arrays.toString(solution.topKFrequent(nums1, k1)));
        System.out.println("Output (Bucket): " + Arrays.toString(solution.topKFrequentBucketSort(nums1, k1)));
        System.out.println("Expected: [1,2]\n");

        // Example 2
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("Input: [1], k = " + k2);
        System.out.println("Output (Heap): " + Arrays.toString(solution.topKFrequent(nums2, k2)));
        System.out.println("Output (Bucket): " + Arrays.toString(solution.topKFrequentBucketSort(nums2, k2)));
        System.out.println("Expected: [1]");
    }
}
