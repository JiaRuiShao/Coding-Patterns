// Triplet Sum to Zero (medium)
// Given an array of unsorted numbers, find all unique triplets in it that add up to zero.

package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletSumToZero {

    /**
     * Two Pointers -- Medium.
     * TC: O(nlogn + n^2) -> O(n^2)
     * SC: O(n + 3^(n-3))
     *
     * @param arr the input Integer arr
     * @return list of triplets that add up to zero
     */
    public List<List<Integer>> findTriplets(int[] arr) {

        // corner cases
        if (arr == null || arr.length < 3) {
            return null;
        }

        // default use quicksort for primitive types O(nlogn)
        List<List<Integer>> triplets = new ArrayList<>();
        int left, right;
        Arrays.sort(arr);

        for (int target = 0; target < arr.length - 2; target++) { // target: [0, arr.length - 3]
            if (target > 0 && arr[target] == arr[target - 1]) { // repeating number
                continue; // ignore them
            }
            // use typical two pointers to find two Integers whose sum is -arr[target]
            left = target + 1;
            right = arr.length - 1;
            while (left < right) {
                if (arr[left] + arr[right] + arr[target] == 0) {
                    triplets.add(Arrays.asList(arr[target], arr[left++], arr[right--]));
                    while (left < right && arr[left] == arr[left - 1]) {
                        left++;
                    }
                    while (left < right && arr[right] == arr[right + 1]) {
                        right--;
                    }
                } else if (arr[left] + arr[right] + arr[target] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        TripletSumToZero tp = new TripletSumToZero();
        System.out.println(tp.findTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2}));
        System.out.println(tp.findTriplets(new int[]{-5, 2, -1, -2, 3}));
    }

}
