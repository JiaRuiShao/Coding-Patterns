/*
* Given an array of positive numbers and a positive number ‘k,’
* find the maximum sum of any contiguous subarray of size ‘k’.
*
* Input: [2, 3, 4, 1, 5], k=2
* Output: 7
* Explanation: Subarray with maximum sum is [3, 4].
*
* */

package array;

public class MaxSumSubArrayOfSizeK {

    /**
     * Sliding Window -- Easy.
     * Time: O(n)
     * Space: O(1)
     *
     * @param k num of sum num
     * @param arr input array
     * @return the max sum of k numbers in arr
     */
    public int findMaxSumSubArray(int k, int[] arr) {
        if (arr == null || arr.length < k) {
            return -1;
        }
        int maxSum = 0, sum = 0;

        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        maxSum = sum;

        for (int j = k; j < arr.length; j++) {
            sum -= arr[j - k];
            sum += arr[j];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumSubArrayOfSizeK maxSumSubArrayOfSizeK = new MaxSumSubArrayOfSizeK();
        System.out.println("Maximum sum of a subarray of size K: "
                + maxSumSubArrayOfSizeK.findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
        System.out.println("Maximum sum of a subarray of size K: "
                + maxSumSubArrayOfSizeK.findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));
    }

}
