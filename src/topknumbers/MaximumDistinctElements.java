/*
 * Given an array of numbers and a number ‘K’, we need to remove ‘K’ numbers from the array
 * such that we are left with maximum distinct numbers.
 * ===========================================================================================
 * Input: [7, 3, 5, 8, 5, 3, 3], and K=2
 * Output: 3
 * Explanation: We can remove two occurrences of 3 to be left with 3 distinct numbers [7, 3, 8],
 * we have to skip 5 because it is not distinct and appeared twice.
 *
 * Another solution could be to remove one instance of '5' and '3' each to be left with three distinct numbers
 * [7, 5, 8], in this case, we have to skip 3 because it appeared twice.
 *
 * Input: [3, 5, 12, 11, 12], and K=3
 * Output: 2
 * Explanation: We can remove one occurrence of 12, after which all numbers will become distinct.
 * Then we can delete any two numbers which will leave us 2 distinct numbers in the result.
 *
 * Input: [1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5], and K=2
 * Output: 3
 * Explanation: We can remove one occurrence of '4' to get three distinct numbers.
 */

package topknumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumDistinctElements {

    /**
     * Solution:
     * First, we will find the frequencies of all the numbers.
     * Then, push all numbers that are not distinct (i.e., have a frequency higher than one) in a MinHeap based on their frequencies.
     * At the same time, we will keep a running count of all the distinct numbers.
     *
     * Following a greedy approach, in a stepwise fashion, we will remove the least frequent number from the heap
     * (i.e., the top element of the min-heap), and try to make it distinct.
     * We will see if we can remove all occurrences of a number except one.
     * If we can, we will increment our running count of distinct numbers.
     * We have to also keep a count of how many removals we have done.
     *
     * If after removing elements from the heap, we are still left with some deletions,
     * we have to remove some distinct elements.
     *
     * Time: O(2nlogn + klogn)
     * Space: O(2n)
     *
     * @param nums the given input arr
     * @param k    k
     * @return the max distinct numbers after k removals in the given nums
     */
    public int findMaximumDistinctElements(int[] nums, int k) {
        int distNums = 0;

        // initialize a hashmap to store the frequencies of the input nums
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // find the distinct num count and non-distinct numbers, store them in a minHeap
        Queue<Integer> minHeap = new PriorityQueue<>(freq.size(), (a, b) -> Integer.compare(freq.get(a), freq.get(b)));
        for (int num : freq.keySet()) {
            if (freq.get(num) == 1) distNums++;
            else minHeap.offer(num);
        }

        // competitively remove the least frequent number from the minHeap to try to make it distinct
        while (k > 0 && !minHeap.isEmpty()) {
            int temp = minHeap.peek();
            k -= freq.get(temp) - 1;
            if (k > 0) {
                minHeap.poll();
                freq.put(temp, 1);
                distNums++;
            } else {
                freq.put(temp, freq.get(temp) - k);
            }
        }

        if (k > 0) distNums -= k;

        return distNums;
    }

    public static void main(String[] args) {
        MaximumDistinctElements elements = new MaximumDistinctElements();

        int result = elements.findMaximumDistinctElements(new int[]{7, 3, 5, 8, 5, 3, 3}, 2);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);

        result = elements.findMaximumDistinctElements(new int[]{3, 5, 12, 11, 12}, 3);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);

        result = elements.findMaximumDistinctElements(new int[]{1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5}, 2);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);
    }

}
