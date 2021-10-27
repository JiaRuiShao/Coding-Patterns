package binarysearch;

/**
 * Given an array of numbers sorted in ascending order,
 * find the element in the array that has the minimum difference with the given ‘key’.
 */
public class MinDiffElem {

    /**
     * Time: O(log_2(n)) where n is the number of elements in the given arr
     * Space: O(1)
     *
     * @param arr the given int arr
     * @param key the target num
     * @return the element who has the smallest difference with the target
     */
    public int findMinDiffEle(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) return arr[mid]; // minDiff = 0
            else if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        } // terminate when left == right + 1
        if (right < 0) return arr[left];
        if (left > arr.length - 1) return arr[right];
        return Math.abs(arr[left] - key) < Math.abs(arr[right] - key) ? arr[left] : arr[right];
    }

    public static void main(String[] args) {
        MinDiffElem diff = new MinDiffElem();
        System.out.println(diff.findMinDiffEle(new int[]{4, 6, 10}, 7));
        System.out.println(diff.findMinDiffEle(new int[]{4, 6, 10}, 4));
        System.out.println(diff.findMinDiffEle(new int[]{1, 3, 8, 10, 15}, 12));
        System.out.println(diff.findMinDiffEle(new int[]{4, 6, 10}, 17));
    }
}
