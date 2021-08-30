/* Given a sorted array of integers, return the index of the given key.
Return -1 if the key is not found. */

package array;

public class BinarySearch {

    /**
     * Find the index of a given integer with recursive binary search.
     *
     * @param arr the sorted arr given (Ascending)
     * @param key the target number
     * @return the index of the target number
     *
     * Time: O(log_2(n))
     * Space: O(log_2(n))
     */
    public int recursiveFind(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        return recursiveBS(arr, key, 0, arr.length - 1);
    }

    private int recursiveBS(int[] arr, int key, int left, int right) {
        // base case
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            else if (arr[mid] < key) return recursiveBS(arr, key, mid + 1, right);
            else return recursiveBS(arr, key, left, mid - 1);
        }
        return -1;
    }

    /**
     * Find the index of a given integer with iterative binary search.
     *
     * @param arr the sorted arr given
     * @param key the target number
     * @return the index of the target number
     *
     * Time: O(log_2(n))
     * Space: O(1)
     */
    public int iterativeFind(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            else if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] arr = {1, 10, 20, 47, 59, 63, 75, 88, 99, 107, 120, 133, 155, 162, 176, 188, 199, 200, 210, 222};
        int[] inputs = {10, 49, 99, 110, 176};

        for (int input : inputs) {
            System.out.println(input + ": " + bs.recursiveFind(arr, input));
            System.out.println(input + ": " + bs.iterativeFind(arr, input));
        }
    }

}
