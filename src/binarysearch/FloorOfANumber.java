package binarysearch;

/*
 * Given an array of numbers sorted in ascending order, find the floor of a given number ‘key’.
 * The floor of the ‘key’ will be the biggest element in the given array smaller than or equal to the ‘key’
 * Write a function to return the index of the floor of the ‘key’. If there isn’t a floor, return -1.
 *
 * Input: [4, 6, 10], key = 6
 * Output: 1
 * Explanation: The biggest number smaller than or equal to '6' is '6' having index '1'.
 *
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 3
 * Explanation: The biggest number smaller than or equal to '12' is '10' having index '3'.
 *
 * Input: [4, 6, 10], key = 17
 * Output: 2
 * Explanation: The biggest number smaller than or equal to '17' is '10' having index '2'.
 *
 * Input: [4, 6, 10], key = -1
 * Output: -1
 * Explanation: There is no number smaller than or equal to '-1' in the given array.
 */

public class FloorOfANumber {

    /**
     * Time: O(log_2(n)) where ‘N’ is the total elements in the given array -- we are reducing the search range by half at every step
     * Space: O(1)
     *
     * @param arr the given input array
     * @param key the target number
     * @return the index of the largest number that is smaller than the target; else return -1;
     */
    public int findFloor(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            else if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        // terminate when left == right + 1
        // return arr[right] if right >= 0
        return right >= 0 ? right : -1;
    }

    public static void main(String[] args) {
        FloorOfANumber floor = new FloorOfANumber();
        System.out.println(floor.findFloor(new int[]{4, 6, 10}, 6));
        System.out.println(floor.findFloor(new int[]{1, 3, 8, 10, 15}, 12));
        System.out.println(floor.findFloor(new int[]{4, 6, 10}, 17));
        System.out.println(floor.findFloor(new int[]{4, 6, 10}, -1));
    }

}
