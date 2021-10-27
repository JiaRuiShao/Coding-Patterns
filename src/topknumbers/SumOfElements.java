/*
 * Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of that array.
 * Input: [1, 3, 12, 5, 15, 11], and K1=3, K2=6
 * Output: 23
 * Explanation: The 3rd smallest number is 5 and 6th smallest number 15. The sum of numbers coming
 * between 5 and 15 is 23 (11+12).
 *
 * Input: [3, 5, 8, 7], and K1=1, K2=4
 * Output: 12
 * Explanation: The sum of the numbers between the 1st smallest number (3) and the 4th smallest
 * number (8) is 12 (5+7).
 *
 */

package topknumbers;

import java.util.PriorityQueue;
import java.util.Queue;

public class SumOfElements {

    /**
     * Use a min-heap to store the n elements.
     * Time: O(nlogn + k2logn)
     *
     * @param nums the given input arr
     * @param k1   k1 th smallest num
     * @param k2   k2 th smallest num
     * @return the sum of elements between the k1th smallest element and k2th smallest element
     */
    public int findSumOfElements(int[] nums, int k1, int k2) {
        int sum = 0, count = 0;
        Queue<Integer> minHeap = new PriorityQueue<>(nums.length);
        for (int num : nums) {
            minHeap.offer(num);
        }

        while (!minHeap.isEmpty()) {
            int temp = minHeap.poll();
            if (++count > k1 && count < k2) {
                sum += temp;
            }
        }

        return sum;
    }

    /**
     * Use a max-heap to store the smallest k2 elements.
     * Time: O(nlogk2 + (k2-k1-1)logk2)
     * Space: O(k2)
     *
     * @param nums the given input arr
     * @param k1   k1 th smallest num
     * @param k2   k2 th smallest num
     * @return the sum of elements between the k1th smallest element and k2th smallest element
     */
    public int findSumOfElementsImproved(int[] nums, int k1, int k2) {
        int sum = 0, count = 0;
        // initialize a maxHeap to store the k2 - 1 smallest numbers in the nums
        Queue<Integer> maxHeap = new PriorityQueue<>(k2, (a, b) -> Integer.compare(b, a));
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() >= k2) maxHeap.poll();
        }
        // poll (k2 - k1 - 1) times to get the sum
        for (int i = 0; i < k2 - k1 - 1 && !maxHeap.isEmpty(); i++) {
            sum += maxHeap.poll();
        }
        return sum;
    }

    public static void main(String[] args) {
        SumOfElements elements = new SumOfElements();
        int result = elements.findSumOfElements(new int[]{1, 3, 12, 5, 15, 11}, 3, 6);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
        result = elements.findSumOfElementsImproved(new int[]{1, 3, 12, 5, 15, 11}, 3, 6);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
        System.out.println();

        result = elements.findSumOfElements(new int[]{3, 5, 8, 7}, 1, 4);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
        result = elements.findSumOfElementsImproved(new int[]{3, 5, 8, 7}, 1, 4);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
        System.out.println();
    }

}
