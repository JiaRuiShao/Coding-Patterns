/*
 * Given a sorted array of numbers, find if a given number ‘key’ is present in the array.
 * Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
 * You should assume that the array can have duplicates.
 * Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.
 *
 * Input: [4, 6, 10], key = 10
 * Output: 2
 *
 * Input: [10, 6, 4], key = 10
 * Output: 0
 *
 * Input: [10, 6, 4], key = 4
 * Output: 2
 */

package binarysearch;

public class FindKInSortedArray {

    /**
     * Time: O(log_2(n)) where ‘N’ is the total elements in the given array -- we are reducing the search range by half at every step
     * Space: O(1)
     *
     * @param arr the given input array
     * @param key the target number we are looking for
     * @return the index of the target number if found; else return -1;
     */
    public static int search(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        boolean isAscending = arr[right] >= arr[left];
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            else if (isAscending ? arr[mid] < key : arr[mid] > key) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(FindKInSortedArray.search(new int[]{4, 6, 10}, 10));
        System.out.println(FindKInSortedArray.search(new int[]{1, 2, 3, 4, 5, 6, 7}, 5));
        System.out.println(FindKInSortedArray.search(new int[]{10, 6, 4}, 10));
        System.out.println(FindKInSortedArray.search(new int[]{10, 6, 4}, 4));
    }
}
