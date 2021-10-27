package binarysearch;

/**
 * Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number,
 * find if a given ‘key’ is present in it.
 *
 * Write a function to return the index of the ‘key’ in the rotated array.
 * If the ‘key’ is not present, return -1. You can assume that the given array does not have any duplicates.
 *
 * Input: [10, 15, 1, 3, 8], key = 15
 * Output: 1
 * Explanation: '15' is present in the array at index '1'.
 *
 * Input: [4, 5, 7, 9, 10, -1, 2], key = 10
 * Output: 4
 * Explanation: '10' is present in the array at index '4'.
 */

public class SearchRotatedArray {

    /**
     * Treat either side as a regular sorted arr using binary search.
     * Time: O(logN) where N is the number of elements in the given rotated arr
     * Space: O(1)
     *
     * @param arr rotated arr
     * @param key the target number
     * @return the index of the target number if found; else return -1;
     */
    public int search(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            if (arr[mid] < arr[right]) { // right part is sorted in ascending order
                if (key > arr[mid] && key <= arr[right]) left = mid + 1;
                else right = mid - 1;
            } else { // left part is sorted in ascending order: arr[mid] > arr[left]
                if (key >= arr[left] && key < arr[mid]) right = mid -1;
                else left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * The only problematic scenario is whenthe numbers at indices start, middle, and end are the same,
     * as in this case, we can’t decide which part of the array is sorted.
     * In such a case, the best we can do is to skip one number from both ends: start = start + 1 & end = end - 1.
     *
     * Time: O(N) where N is the number of elements in the given arr
     * Space: O(1)
     *
     * @param arr the given int arr
     * @param key the target
     * @return the index of the target number if it exists in the given arr; else return -1
     */
    public int searchWithDup(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            // if numbers at indexes start, mid, and end are same, we can't choose a side
            // the best we can do, is to skip one number from both ends as key != arr[mid]
            if (arr[left] == arr[mid] && arr[right] == arr[mid]) {
                left++;
                right--;
            } else if (arr[mid] <= arr[right]) { // right part is sorted in ascending order
                if (key > arr[mid] && key <= arr[right]) left = mid + 1;
                else right = mid - 1;
            } else { // left part is sorted in ascending order: arr[mid] >= arr[left]
                if (key >= arr[left] && key < arr[mid]) right = mid -1;
                else left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchRotatedArray().search(new int[] { 10, 15, 1, 3, 8 }, 15));
        System.out.println(new SearchRotatedArray().search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
        System.out.println(new SearchRotatedArray().searchWithDup(new int[] {3, 7, 3, 3, 3}, 7));
    }
}
