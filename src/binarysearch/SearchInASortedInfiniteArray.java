package binarysearch;

/**
 * Given an infinite sorted array (or an array with unknown size),
 * find if a given number ‘key’ is present in the array.
 * Write a function to return the index of the ‘key’ if it is present in the array,
 * otherwise return -1.
 *
 * Since it is not possible to define an array with infinite (unknown) size,
 * you will be provided with an interface ArrayReader to read elements of the array.
 * ArrayReader.get(index) will return the number at index;
 * if the array’s size is smaller than the index, it will return Integer.MAX_VALUE.
 *
 * Input: [4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30], key = 16
 * Output: 6
 * Explanation: The key is present at index '6' in the array.
 *
 * Input: [4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30], key = 11
 * Output: -1
 * Explanation: The key is not present in the array.
 *
 * Input: [1, 3, 8, 10, 15], key = 15
 * Output: 4
 * Explanation: The key is present at index '4' in the array.
 *
 * Input: [1, 3, 8, 10, 15], key = 200
 * Output: -1
 * Explanation: The key is not present in the array.
 */

class ArrayReader {
    int[] arr;

    ArrayReader(int[] arr) {
        this.arr = arr;
    }

    public int get(int index) {
        if (index >= arr.length)
            return Integer.MAX_VALUE;
        return arr[index];
    }
}

public class SearchInASortedInfiniteArray {

    /**
     * Time: O(log_2(2n))
     * Space: O(1)
     *
     * @param reader the arr reader
     * @param key the target number
     * @param left the left range
     * @param right the right range
     * @return the index of the target number if found; else return -1
     */
    private int binarySearch(ArrayReader reader, int key, int left, int right) {
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (reader.get(mid) == key) return mid; // found
            else if (reader.get(mid) < key) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // not found
    }

    /**
     * Time: O(log_2(n)) where n is the number of elements in the array
     * Space: O(1)
     *
     * @param reader arr reader
     * @param key the target number
     * @return the index of the target number if found; else return -1
     */
    public int findNumber(ArrayReader reader, int key) {
        // find the possible range of the key
        int start = 1, end = 2 * start + 1;
        while (reader.get(end) != Integer.MAX_VALUE && reader.get(end) < key) {
            start = end;
            end = 2 * start + 1;
        }
        return binarySearch(reader, key, start, end);
    }

    public static void main(String[] args) {
        SearchInASortedInfiniteArray search = new SearchInASortedInfiniteArray();
        ArrayReader reader = new ArrayReader(new int[] { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 });
        System.out.println(search.findNumber(reader, 16));
        System.out.println(search.findNumber(reader, 11));

        reader = new ArrayReader(new int[] { 1, 3, 8, 10, 15 });
        System.out.println(search.findNumber(reader, 15));
        System.out.println(search.findNumber(reader, 200));
    }

}
