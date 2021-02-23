// Subarrays with Product Less than a Target (medium)
// Given an array with positive numbers and a target number,
// find all of its contiguous subarrays whose product is less than the target number.

package twopointers;

import java.util.LinkedList;
import java.util.List;

public class SubarrayProductLessThanTarget {

    /**
     * Two Pointers + Sliding Window -- Medium.
     * TC: O(n^2)
     * SC: At most we have O(n^2) sublist, and each sublist can be O(n) SC in the worst case, thus the SC here is O(n^3)
     *
     * @param arr    the input array
     * @param target the target num
     * @return a list of contiguous subarray whose product is less than the target
     */
    public List<List<Integer>> findSubArray(int[] arr, int target) {
        List<List<Integer>> subarray = new LinkedList<>();
        List<Integer> sub = new LinkedList<>();
        int product = 1, left = 0;
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];
            while (product >= target && left < arr.length) {
                product /= arr[left++];
            }
            sub.clear();

            /*// this is going to get duplicate results
            for (int i = left; i <= right; i++) {
                sub.add(arr[i]);
                subarray.add(new ArrayList<>(sub));
            }*/

            // since the product of all numbers from left to right is less than the target therefore,
            // all subarrays from left to right will have a product less than the target too; to avoid
            // duplicates, we will start with a subarray containing only arr[right] and then extend it
            for (int i = right; i >= left; i--) {
                sub.add(0, arr[i]);
                subarray.add(new LinkedList<>(sub));
            }
        }
        return subarray;
    }

    /**
     * A similar -- easier version.
     * Your are given an array of positive integers nums.
     * Count and print the number of (contiguous) subarrays where the product
     * of all the elements in the subarray is less than the target.
     *
     * TC: O(n)
     * SC: O(1)
     *
     * @param arr    the input array
     * @param target the target num
     * @return the num of contiguous subarray whose product is less than the target
     */
    public int findSubArrayCount(int[] arr, int target) {
        int count = 0, left = 0, product = 1;
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];
            // constraint
            while (product >= target && left < arr.length) {
                product /= arr[left++];
            }
            if (left <= right) {
                count += right - left + 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SubarrayProductLessThanTarget tp = new SubarrayProductLessThanTarget();
        System.out.println(tp.findSubArray(new int[]{2, 5, 3, 10}, 30));
        System.out.println(tp.findSubArrayCount(new int[]{2, 5, 3, 10}, 30));
        System.out.println(tp.findSubArray(new int[]{8, 2, 6, 5}, 50));
        System.out.println(tp.findSubArrayCount(new int[]{8, 2, 6, 5}, 50));
        System.out.println(tp.findSubArray(new int[]{1, 1, 1, 1}, 0));
        System.out.println(tp.findSubArrayCount(new int[]{1, 1, 1, 1}, 0));
    }
}
