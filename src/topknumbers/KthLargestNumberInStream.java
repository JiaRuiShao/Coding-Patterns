/*
 * Design a class to efficiently find the Kth largest element in a stream of numbers.
 * The class should have the following two things:
 * - The constructor of the class should accept an integer array containing initial numbers from the stream and an integer ‘K’.
 * - The class should expose a function add(int num) which will store the given number and return the Kth largest number.
 */

package topknumbers;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestNumberInStream {

    Queue<Integer> minHeap; // used to store the k largest numbers
    int size;

    /**
     * Time: O(nlogk)
     * Space: O(k)
     *
     * @param nums the given int arr
     * @param k k
     */
    public KthLargestNumberInStream(int[] nums, int k) {
        if (k <= 0) k = 1;
        minHeap = new PriorityQueue<>(k + 1);
        size = k;
        for (int num : nums) {
            add(num);
        }
    }

    /**
     * Time: O(logk)
     * Space: O(k)
     *
     * @param num the num to be added
     * @return the kth largest num
     */
    public int add(int num) {
        minHeap.offer(num);
        if (minHeap.size() > size) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
        KthLargestNumberInStream kthLargestNumber = new KthLargestNumberInStream(input, 4);
        System.out.println("4th largest number is: " + kthLargestNumber.add(6));
        System.out.println("4th largest number is: " + kthLargestNumber.add(13));
        System.out.println("4th largest number is: " + kthLargestNumber.add(4));
    }

}
