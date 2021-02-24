// Minimum Window Sort (medium)
// Given an array, find the length of the smallest subarray in it
// which when sorted will make sure the whole array is in ascending order.

package twopointers;

public class MinWindowSort {

    /**
     * Two Pointers Practice Problem #3 -- Medium.
     * Algorithm:
     * From the beginning and end of the array, find the first elements that are out of the sorting order.
     * The two elements will be our candidate subarray.
     * Find the maximum and minimum of this subarray.
     * Extend the subarray from beginning to include any number which is bigger than the minimum of the subarray.
     * Similarly, extend the subarray from the end to include any number which is smaller than the maximum of the subarray.
     *
     * TC: O(n)
     * SC: O(1)
     *
     * @param arr the input Integer arr
     * @return the min length of the subarray
     */
    public int getLength(int[] arr) {

        if (arr == null || arr.length == 0) return 0;

        int left = 1, right = arr.length - 2;

        // find the first element that is smaller than its left-hand elements
        while (left < arr.length) {
            if (arr[left] < arr[left - 1]) {
                left--;
                break;
            } else {
                left++;
            }
        }

        if (left == arr.length) return 0; // all sorted

        // find the first element that is larger than its right-hand elements
        while (right >= 0) {
            if (arr[right] > arr[right + 1]) {
                right++;
                break;
            } else {
                right--;
            }
        }

        // record the min and max value within the subarray
        int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            minValue = Math.min(minValue, arr[i]);
            maxValue = Math.max(maxValue, arr[i]);
        }

        // extend to the left side to find if there's any number larger than the minValue
        for (int j = 0; j <= left; j++) {
            if (arr[j] > minValue) {
                left = j;
                break;
            }
        }

        // extend to the right side to find if there's any number less than the maxValue
        for (int k = arr.length - 1; k >= right; k--) {
            if (arr[k] < maxValue) {
                right = k;
                break;
            }
        }

        return right - left + 1;
    }

    public static void main(String[] args) {
        MinWindowSort tp = new MinWindowSort();
        System.out.println(tp.getLength(new int[]{1, 2, 5, 3, 7, 10, 9, 12}));
        System.out.println(tp.getLength(new int[]{1, 3, 2, 0, -1, 7, 10}));
        System.out.println(tp.getLength(new int[]{1, 2, 3}));
        System.out.println(tp.getLength(new int[]{3, 2, 1}));
    }

}
