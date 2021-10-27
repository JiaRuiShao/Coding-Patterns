/*
 * Given an array of numbers sorted in an ascending order, find the ceiling of a given number ‘key’.
 * The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key’.
 * Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling return -1.
 *
 * Input: [4, 6, 10], key = 6
 * Output: 1
 * Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.
 *
 * Input: [4, 6, 10], key = 17
 * Output: -1
 * Explanation: There is no number greater than or equal to '17' in the given array.
 *
 * Input: [4, 6, 10], key = -1
 * Output: 0
 * Explanation: The smallest number greater than or equal to '-1' is '4' having index '0'.
 */

package binarysearch;

public class CeilingOfANumber {

    /**
     * Time: O(log_2(n)) where ‘N’ is the total elements in the given array -- we are reducing the search range by half at every step
     * Space: O(1)
     *
     * @param arr the given input array
     * @param key the target number
     * @return the index of the smallest number that is larger than the target; else return -1;
     */
    public int findCeiling(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (mid == 0 ? arr[mid] >= key : arr[mid - 1] < key && arr[mid] >= key) return mid;
            else if (arr[mid] < key) left = mid + 1;
            else right = mid;
        }
        return -1;
    }

    public int findCeiling2(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            else if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        } // terminate the while loop when left = right + 1

        // we are not able to find the element in the given array, so the next big number will be arr[left]
        return left < arr.length ? left : -1; // take care of the corner case where left == arr.length
    }

    public static void main(String[] args) {
        CeilingOfANumber ceiling = new CeilingOfANumber();
        System.out.println(ceiling.findCeiling(new int[]{4, 6, 10}, 6));
        System.out.println(ceiling.findCeiling2(new int[]{4, 6, 10}, 6));

        System.out.println(ceiling.findCeiling(new int[]{1, 3, 8, 10, 15}, 12));
        System.out.println(ceiling.findCeiling2(new int[]{1, 3, 8, 10, 15}, 12));

        System.out.println(ceiling.findCeiling(new int[]{4, 6, 10}, 17));
        System.out.println(ceiling.findCeiling2(new int[]{4, 6, 10}, 17));

        System.out.println(ceiling.findCeiling(new int[]{4, 6, 10}, -1));
        System.out.println(ceiling.findCeiling2(new int[]{4, 6, 10}, -1));
    }
}
