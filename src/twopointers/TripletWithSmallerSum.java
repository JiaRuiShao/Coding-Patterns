// Triplets with Smaller Sum (medium)
// Given an array arr of unsorted numbers and a target sum, count all triplets in it such that
// arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices.
// Write a function to return the count of such triplets.

package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletWithSmallerSum {

    /**
     * Two Pointers -- Medium.
     * TC: O(nlogn + n^2) -> O(n^2)
     * SC: O(n)
     *
     * @param arr    the input int arr
     * @param target the target sum
     * @return the num of triplets that their sum is less than the target
     */
    public int findTripletCount(int[] arr, int target) {
        int tripletCount = 0, j, k;
        // check corner case
        if (arr == null || arr.length < 3) {
            return tripletCount;
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            j = i + 1;
            k = arr.length - 1;
            while (j < k) {
                if (arr[i] + arr[j] + arr[k] < target) {
                    // tripletCount++; // this is not right b/c for this [i, j]
                    // the arr[k] here satisfy the requirement, which means that indices bw
                    // i & k all should be count into the triplet count
                    tripletCount += k - j;
                    j++;
                } else { // arr[i] + arr[j] + arr[k] >= target
                    k--;
                }
            }
        }
        return tripletCount;
    }

    /**
     * A similar question.
     * TC: O(nlogn + n^3)
     * SC: O(n + 3^n)
     *
     * @param arr    the input int arr
     * @param target the target sum
     * @return the triplets whose sum is less than the target
     */
    public List<List<Integer>> findTriplet(int[] arr, int target) {
        List<List<Integer>> triplet = new ArrayList<>();
        int j, k;
        // check corner case
        if (arr == null || arr.length < 3) {
            return triplet;
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            j = i + 1;
            k = arr.length - 1;
            while (j < k) {
                if (arr[i] + arr[j] + arr[k] < target) {
                    while (j < k) {
                        triplet.add(Arrays.asList(arr[i], arr[j], arr[k--]));
                    }
                    k = arr.length - 1;
                    j++;
                } else { // arr[i] + arr[j] + arr[k] >= target
                    k--;
                }
            }
        }
        return triplet;
    }

    public static void main(String[] args) {
        TripletWithSmallerSum tp = new TripletWithSmallerSum();
        System.out.println(tp.findTripletCount(new int[]{-1, 0, 2, 3}, 3));
        System.out.println(tp.findTriplet(new int[]{-1, 0, 2, 3}, 3));
        System.out.println(tp.findTripletCount(new int[]{-1, 4, 2, 1, 3}, 5));
        System.out.println(tp.findTriplet(new int[]{-1, 4, 2, 1, 3}, 5)); // -1 1 2 3 4
    }
}
