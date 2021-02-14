// Given an array of positive numbers and a positive number ‘S,’
// find the length of the smallest contiguous subarray whose
// sum is greater than or equal to ‘S’.
// Return 0 if no such subarray exists.

package slidingwindow;

public class SmallestLengthOfSubArrayWithGivenSum {

    /**
     * Sliding Window -- Easy.
     * TC: O(n) worstTC: O(2n) -> O(n) when each element is >= S
     * SC: O(1)
     * @param arr sample array
     * @param s int value
     * @return the smallest subarray length whose sum >= S; 0 if sum of all elements < S
     */
    public int findLength(int[] arr, int s) {
        int startIdx = 0;
        int windowSum = 0;
        int minArrayLength = Integer.MAX_VALUE;
        for (int endIdx = 0; endIdx < arr.length; endIdx++) {
            windowSum += arr[endIdx]; // add the next element value
            // keep shrink the subarray as long as the sum >= s
            while (windowSum >= s) {
                minArrayLength = Math.min(minArrayLength, endIdx - startIdx + 1);
                windowSum -= arr[startIdx++]; // subtract the 1st element value within the window and slide the window ahead
                // maintain the endIdx when the sum of current window still larger than or equal to s
            }
        }
        return minArrayLength == Integer.MAX_VALUE ? 0 : minArrayLength;
    }

    public static void main(String[] args) {
        SmallestLengthOfSubArrayWithGivenSum sw = new SmallestLengthOfSubArrayWithGivenSum();
        int[] array = new int[]{2, 1, 5, 2, 3, 2};
        System.out.println(sw.findLength(array, 7));
        array = new int[]{2, 1, 5, 2, 8};
        System.out.println(sw.findLength(array, 7));
        array = new int[]{3, 4, 1, 1, 6};
        System.out.println(sw.findLength(array, 8));
    }

}
