package binarysearch;

public class NumberRange {

    /**
     * Time: O(n) where n is the total element # in the givne arr.
     * Space: O(1)
     *
     * @param arr the given int arr
     * @param idx the idx of the target number
     * @return the range of that target number
     */
    private int[] getRange(int[] arr, int idx) {
        int left = --idx, right = ++idx;
        while (left >= 0 && arr[left] == arr[idx]) left--;
        while (right < arr.length && arr[right] == arr[idx]) right++;
        return new int[]{left + 1, right - 1};
    }

    /**
     * Time: O(logn) where n is the element # in the given arr
     * Space: O(1)
     *
     * @param arr the given int arr
     * @param key the target number
     * @return the range of the target number if found; else return [-1, -1]
     */
    public int[] findNumRange(int[] arr, int key) {
        int[] range = new int[]{-1, -1};
        // bs to find the number
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                range = getRange(arr, mid);
                break;
            } else if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return range;
    }

    public static void main(String[] args) {
        NumberRange numbers = new NumberRange();
        int[] result = numbers.findNumRange(new int[]{4, 6, 6, 6, 9}, 6);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = numbers.findNumRange(new int[]{1, 3, 8, 10, 15}, 10);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = numbers.findNumRange(new int[]{1, 3, 8, 10, 15}, 12);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    }
}
