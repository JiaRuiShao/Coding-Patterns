/*
 * Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.
 *
 * Input: [5, 12, 11, 3, 11], K = 2
 * Output: [11, 5] or [11, 12] or [11, 3]
 * Explanation: Only '11' appeared twice, all other numbers appeared once.
 *
 * Input: [1, 3, 5, 12, 11, 12, 11], K = 2
 * Output: [12, 11]
 * Explanation: Both '11' and '12' appeared twice.
 */
package topknumbers;

import java.util.*;

public class TopKFrequentNumbers {

    /**
     * Time: O(n + nlogk + klogk)
     * Space: O(n + k) = O(n)
     *
     * @param nums the input arr
     * @param k k
     * @return the numbers with top k frequencies
     */
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        List<Integer> topNumbers = new ArrayList<>(k);

        // initialize the freqMap for the input array nums
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // use a minHeap to store the numbers with the top k frequency
        Queue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> Integer.compare(freq.get(a), freq.get(b)));
        for (Integer num : freq.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // create a list of top k numbers
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) {
            topNumbers.add(0, minHeap.poll());
        }

        return topNumbers;
    }

    public static void main(String[] args) {
        List<Integer> result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[]{1, 3, 5, 12, 11, 12, 11}, 2);
        System.out.println("Here are the K frequent numbers: " + result);

        result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[]{5, 12, 11, 3, 11}, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    }

}
