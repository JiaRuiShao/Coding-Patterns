package binarysearch;

/**
 * Given a Bitonic array, find if a given ‘key’ is present in it. An array is considered bitonic if it is
 * monotonically increasing and then monotonically decreasing. Monotonically increasing or decreasing means that
 * for any index i in the array arr[i] != arr[i+1].
 *
 * Write a function to return the index of the ‘key’. If the ‘key’ is not present, return -1.
 */
public class SearchBitonicArray {

    private int getMaxIndex(int[] arr) {
        int left = 0, right = arr.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) left = mid + 1;
            else right = mid; // arr[mid] > arr[mid + 1]
        }
        return left;
    }

    private int binarySearch(int[] arr, int key, int left, int right) {
        // corner case
        if (left > arr.length - 1) return -1;

        int mid;
        boolean isAsc = arr[left] < arr[right];
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            else if (isAsc ? arr[mid] < key : arr[mid] > key) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public int findNum(int[] arr, int key) {
        int max = getMaxIndex(arr);
        int searchLeft = binarySearch(arr, key, 0, max);
        if (searchLeft != -1) return searchLeft;
        return binarySearch(arr, key, max + 1, arr.length - 1); // searchRight
    }

    public static void main(String[] args) {
        SearchBitonicArray bitonic = new SearchBitonicArray();
        System.out.println(bitonic.findNum(new int[] { 1, 3, 8, 4, 3 }, 4));
        System.out.println(bitonic.findNum(new int[] { 3, 8, 3, 1 }, 8));
        System.out.println(bitonic.findNum(new int[] { 1, 3, 8, 12 }, 12));
        System.out.println(bitonic.findNum(new int[] { 10, 9, 8 }, 10));
    }

}
