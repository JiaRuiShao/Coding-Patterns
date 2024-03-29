package binarysearch;

/**
 * Find the maximum value in a given Bitonic array. An array is considered bitonic if
 * it is monotonically increasing and then monotonically decreasing.
 *
 * Monotonically increasing or decreasing means that for any index i in the array arr[i] != arr[i+1].
 *
 * Input: [1, 3, 8, 12, 4, 2]
 * Output: 12
 * Explanation: The maximum number in the input bitonic array is '12'.
 *
 * Input: [3, 8, 3, 1]
 * Output: 8
 *
 * Input: [1, 3, 8, 12]
 * Output: 12
 *
 * Input: [10, 9, 8]
 * Output: 10
 */

public class BitonicArrMax {

    /**
     * A bitonic array is a sorted array; the only difference is that
     * its first part is sorted in ascending order and the second part is sorted in descending order.
     * We can use a similar approach as discussed in Order-agnostic Binary Search.
     * Since no two consecutive numbers are same (as the array is monotonically increasing or decreasing),
     * whenever we calculate the middle, we can compare the numbers pointed out by
     * the index middle and middle+1 to find if we are in the ascending or the descending part. So:
     * - If arr[middle] > arr[middle + 1], we are in the second (descending) part of the bitonic array.
     *   Therefore, our required number could either be pointed out by middle or will be before middle.
     *   This means we will be doing: end = middle.
     * - If arr[middle] < arr[middle + 1], we are in the first (ascending) part of the bitonic array.
     *   Therefore, the required number will be after middle. This means we will be doing: start = middle + 1.
     *
     * We can break when start == end. Due to the two points mentioned above,
     * both start and end will be pointing at the maximum number of the bitonic array.
     *
     * @param arr the given bitonic arr
     * @return the largest number in the given bitonic arr
     */
    public int getMaxNum(int[] arr) {
        int left = 0, right = arr.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) right = mid; // in the right half
            else if (arr[mid] < arr[mid + 1]) left = mid + 1; // in the left half
        }
        return arr[left]; // left == right
    }

    public static void main(String[] args) {
        BitonicArrMax bitArr = new BitonicArrMax();
        System.out.println(bitArr.getMaxNum(new int[] { 1, 3, 8, 12, 4, 2 }));
        System.out.println(bitArr.getMaxNum(new int[] { 3, 8, 3, 1 }));
        System.out.println(bitArr.getMaxNum(new int[] { 1, 3, 8, 12 }));
        System.out.println(bitArr.getMaxNum(new int[] { 10, 9, 8 }));
    }
}
