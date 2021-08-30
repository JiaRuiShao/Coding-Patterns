/* Given a sorted array of integers that has been rotated by some arbitrary number,
return the index of the given key. Return -1 if the key is not found. */

package array;

public class BinarySearchRotated {

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
    public int recursiveFindRotated(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        return recursiveBS(arr, key, 0, arr.length - 1);
    }

    private int recursiveBS(int[] arr, int key, int left, int right) {
        // base case
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            else if (arr[mid] < arr[right] && arr[mid] < key && arr[right] >= key)
                return recursiveBS(arr, key, mid + 1, right);
            else if (arr[mid] > arr[left] && arr[mid] > key && arr[left] <= key)
                return recursiveBS(arr, key, left, mid - 1);
            else if (arr[mid] > arr[right]) return recursiveBS(arr, key, mid + 1, right); // the right side is modified
            else if (arr[mid] < arr[left]) return recursiveBS(arr, key, left, mid - 1); // the left side is modified
            else return -1;
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
    public int iterativeFindRotated(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            // if the key is on the sorted side
            else if (arr[mid] < arr[right] && arr[mid] < key && arr[right] >= key)
                left = mid + 1;
            else if (arr[mid] > arr[left] && arr[mid] > key && arr[left] <= key)
                right = mid - 1;
            // if the key is on the unsorted side, keep dividing until the key side is sorted
            else if (arr[mid] > arr[right])
                left = mid + 1;
            else if (arr[mid] < arr[left])
                right = mid - 1;
            // not found
            else
                return -1;
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        BinarySearchRotated bs = new BinarySearchRotated();
        int[] v1 = {6, 7, 1, 2, 3, 4, 5};
        System.out.println("Key(3) found at: " + bs.recursiveFindRotated(v1, 3));
        System.out.println("Key(3) found at: " + bs.iterativeFindRotated(v1, 3));

        System.out.println("Key(6) found at: " + bs.recursiveFindRotated(v1, 6));
        System.out.println("Key(6) found at: " + bs.iterativeFindRotated(v1, 6));

        System.out.println("Key(6) found at: " + bs.recursiveFindRotated(v1, 0));
        System.out.println("Key(6) found at: " + bs.iterativeFindRotated(v1, 0));

        int[] v2 = {4, 5, 6, 1, 2, 3};
        System.out.println("Key(3) found at: " + bs.recursiveFindRotated(v2, 3));
        System.out.println("Key(3) found at: " + bs.iterativeFindRotated(v2, 3));

        System.out.println("Key(6) found at: " + bs.recursiveFindRotated(v2, 6));
        System.out.println("Key(6) found at: " + bs.iterativeFindRotated(v2, 6));

        System.out.println("Key(6) found at: " + bs.recursiveFindRotated(v2, 0));
        System.out.println("Key(6) found at: " + bs.iterativeFindRotated(v2, 0));
    }

}
