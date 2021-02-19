// Triplet Sum Close to Target (medium)
// Given an array of unsorted numbers and a target number, find a triplet in the array
// whose sum is as close to the target number as possible, return the sum of the triplet.
// If there are more than one such triplet, return the sum of the triplet with the smallest sum.

package twopointers;

import java.util.Arrays;

public class TripletSumCloseToTarget {

    /**
     * Two Pointers -- Medium.
     * TC: O(nlogn + n^2) -> O(n^2)
     * SC: O(n)
     *
     * @param arr    the input Integer arr
     * @param target the target sum
     * @return null if there's no valid answer, the smallest sum of the triplet among the triplets
     * whose sum is the closest to the target sum.
     */
    public Integer findMinDiff(int[] arr, int target) {
        // corner case
        if (arr == null || arr.length < 3) {
            return null;
        }
        int minDiff = Integer.MAX_VALUE, diff, left, right;
        Arrays.sort(arr);
        for (int k = 0; k < arr.length - 2; k++) {
            left = k + 1;
            right = arr.length - 1;
            while (left < right) {
                // comparing the sum of three numbers to the target can cause overflow
                // so, we will try to find a target difference
                // diff = target - (arr[k] + arr[left] + arr[right]);
                diff = target - arr[k] - arr[left] - arr[right];
                if (diff == 0) {
                    // found exactly answer
                    return target;
                }
                // when we have more than one solution, store the largest diff so that we can get the smallest sum
                if (Math.abs(diff) < Math.abs(minDiff) || (Math.abs(diff) == Math.abs(minDiff) && diff > minDiff)) {
                    minDiff = diff;
                } else if (diff < 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return target - minDiff;
    }

    public static void main(String[] args) {
        TripletSumCloseToTarget tp = new TripletSumCloseToTarget();
        System.out.println(tp.findMinDiff(new int[]{-2, 0, 1, 2}, 2));
        System.out.println(tp.findMinDiff(new int[]{-3, -1, 1, 2}, 1));
        System.out.println(tp.findMinDiff(new int[]{1, 0, 1, 1}, 100));
    }

}
