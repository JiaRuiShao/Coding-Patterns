package topknumbers;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KLargestNumbers {

    /**
     * Time: O((n + k)logk) = O(nlogk)
     * Space: O(k)
     *
     * @param nums the given int arr
     * @param k the kth number
     * @return a list that contains the largest k numbers from the input nums
     */
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        List<Integer> numbers = new LinkedList<>();
        if (nums == null || nums.length == 0) return numbers;

        Queue<Integer> minHeap = new PriorityQueue<>(k);
        for (int num : nums) { // O(nlogk)
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) { // O(klohk)
            numbers.add(minHeap.poll());
        }

        return numbers;
    }

    public static void main(String[] args) {
        List<Integer> result = KLargestNumbers.findKLargestNumbers(new int[]{3, 1, 5, 12, 2, 11}, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = KLargestNumbers.findKLargestNumbers(new int[]{5, 12, 11, -1, 12}, 3);
        System.out.println("Here are the top K numbers: " + result);
    }
}
