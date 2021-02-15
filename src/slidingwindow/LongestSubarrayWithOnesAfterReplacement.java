// Given an array containing 0s and 1s, if you are allowed to replace no more than ‘k’ 0s with 1s,
// find the length of the longest contiguous subarray having all 1s.

package slidingwindow;

public class LongestSubarrayWithOnesAfterReplacement {

    /**
     * Sliding Window -- Hard (Easy)
     * TC: O(n)
     * SC: O(1)
     *
     * @param arr input array that only contains 0s and 1s
     * @param k max number of 0s that can be replaced
     * @return max length of 1s after replacement
     */
    public int getLength(int[] arr, int k) {
        int winStart = 0, maxFreq = 0, maxLength = 0, numberOfOne = 0;
        for (int winEnd = 0; winEnd < arr.length; winEnd++) {
            if (arr[winEnd] == 1) {
                numberOfOne++;
            }
            if (winEnd - winStart + 1 > k + numberOfOne) {
                if (arr[winStart++] == 1) {
                    numberOfOne--;
                }
            }
            maxLength = Math.max(maxLength, winEnd - winStart + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubarrayWithOnesAfterReplacement sw = new LongestSubarrayWithOnesAfterReplacement();
        System.out.println(sw.getLength(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
        System.out.println(sw.getLength(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));
    }

}
