// Given an array of positive numbers and a positive number ‘k,’
// find the maximum sum of any contiguous sub-array of size ‘k’.

package slidingwindow;

public class MaxSumOfKSubArray {

    /**
     * Sliding window -- Easy.
     * TC: O(n)
     * SC: O(1)
     *
     * @param arr sample array
     * @param k k elements subarray
     * @return the max sum value of k elements
     */
    public int findLength(int[] arr, int k) {
        int startIdx = 0;
        int maxSum = -1, windowSum = 0; // b/c the elements in arr is all positive numbers
        // or else we'll have to use Integer.MIN_VALUE
        for (int endIdx = 0; endIdx < arr.length; endIdx++) {
            windowSum += arr[endIdx];
            if (endIdx >= k - 1) {
                // maxSum = windowSum > maxSum ? windowSum : maxSum;
                maxSum = Math.max(windowSum, maxSum);
                windowSum -= arr[startIdx++]; //subtract the first element of the window
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumOfKSubArray sw = new MaxSumOfKSubArray();
        int[] array = new int[]{2, 1, 5, 1, 3, 2};
        System.out.println(sw.findLength(array, 3));
        array = new int[]{2, 3, 4, 1, 5};
        System.out.println(sw.findLength(array, 2));
    }

}
