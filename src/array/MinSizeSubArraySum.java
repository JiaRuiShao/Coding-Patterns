/*
* Given an array of positive numbers and a positive number ‘S,’
* find the length of the smallest contiguous subarray whose sum
* is greater than or equal to ‘S’. Return 0 if no such subarray exists.
*
* Input: [2, 1, 5, 2, 3, 2], S=7
* Output: 2
 * Explanation: The smallest subarray with a sum greater than or equal to '7' is [5, 2].
*
* */

package array;

public class MinSizeSubArraySum {

    /**
     * Sliding Window.
     *
     * Time: O(N)
     * Space: O(1)
     *
     * @param S the target sum
     * @param arr the input array
     * @return the smallest size to get to the sum S
     */
    public static int findMinSubArray(int S, int[] arr) {

        // corner case
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int sum = 0, size = Integer.MAX_VALUE, start = 0;

        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];
            while (sum >= S && start <= end) {
                size = Math.min(size, end - start + 1);
                sum -= arr[start++];
            }
        }
        if (size <= arr.length) {
            return size;
        }
        return 0;
    }

    public static void main(String[] args) {
        int result = MinSizeSubArraySum.findMinSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 });
        System.out.println("Smallest subarray length: " + result);
        result = MinSizeSubArraySum.findMinSubArray(7, new int[] { 2, 1, 5, 2, 8 });
        System.out.println("Smallest subarray length: " + result);
        result = MinSizeSubArraySum.findMinSubArray(8, new int[] { 3, 4, 1, 1, 6 });
        System.out.println("Smallest subarray length: " + result);
    }

}
