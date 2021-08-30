/*
Given an integer array, return the maximum subarray sum.
The array may contain both positive and negative integers and is unsorted.
*/

package array;

import java.util.Arrays;

public class FindMax {
    public static int findMaxSumSubArray(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int currMax = arr[0];
        int globalMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (currMax < 0) {
                currMax = arr[i];
            } else {
                currMax += arr[i];
            }

            if (globalMax < currMax) {
                globalMax = currMax;
            }
        }
        return globalMax;
    }

    public static void main(String args[]) {
        int[] arr1 = {1, 7, -2, -5, 10, -1};
        System.out.println("Array: " + Arrays.toString(arr1));
        System.out.println("Sum: " + findMaxSumSubArray(arr1));
    }
}
