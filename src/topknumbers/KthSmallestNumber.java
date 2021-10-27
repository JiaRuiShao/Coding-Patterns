package topknumbers;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestNumber {

    /**
     * Find the kth smallest number in a given arr.
     * Time: O(nlogk)
     * Space: O(k)
     *
     * @param nums the given int arr
     * @param k k
     * @return the kth smallest number in the given int arr
     */
    public static int findKthSmallestNumber(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) return -1;

        Queue<Integer> maxHeap = new PriorityQueue<>(k, (a, b) -> Integer.compare(b, a)); // used to store the smallest top k numbers, larger num has higher priority
        for (int num : nums) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else if (num < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }

        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int result = KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
        System.out.println("Kth smallest number is: " + result);

        // since there are two 5s in the input array, our 3rd and 4th smallest numbers should be a '5'
        result = KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result);

        result = KthSmallestNumber.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Kth smallest number is: " + result);
    }
}
