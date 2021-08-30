// Binary Search Variant
/* Given a sorted array of integers, return the low and high index of the given key. */

package array;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Jessie Shao
 * Binary Search twice to find the high and low index of the key.
 * Time: O(logn)
 * Space: O(1) if iterative; O(logn) if recursive
 */
public class LowHighIndex {

    int left, right, mid;

    public int findLowIndex(List<Integer> arr, int key) {
        left = 0;
        right = arr.size() - 1;

        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr.get(mid) == key) right = mid;
            else if (arr.get(mid) < key) left = mid + 1;
            else right = mid - 1;
        }
        if (left >= 0 && left < arr.size() && arr.get(left) == key) return left;
        return -1;
    }

    public int findHighIndex(List<Integer> arr, int key) {
        left = 0;
        right = arr.size() - 1;

        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr.get(mid) == key) left = mid;
            else if (arr.get(mid) < key) left = mid + 1;
            else right = mid - 1;
        }
        if (right >= 0 && right < arr.size() && arr.get(right) == key) return right;
        return -1;
    }

    public static void main(String[] args) {
        LowHighIndex bs = new LowHighIndex();
        List<Integer> array = Arrays.asList(1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6);
        int key = 5;
        int low = bs.findLowIndex(array, key);
        int high = bs.findHighIndex(array, key);
        System.out.println("Low Index of " + key + ": " + low);
        System.out.println("High Index of " + key + ": " + high);

        key = -2;
        low = bs.findLowIndex(array, key);
        high = bs.findHighIndex(array, key);
        System.out.println("Low Index of " + key + ": " + low);
        System.out.println("High Index of " + key + ": " + high);
    }

}
