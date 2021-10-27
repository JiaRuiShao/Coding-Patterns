package binarysearch;

/**
 * Given an array of numbers which is sorted in ascending order and is rotated ‘k’ times around a pivot, find ‘k’.
 * You can assume that the array does not have any duplicates.
 *
 * Input: [10, 15, 1, 3, 8]
 * Output: 2
 * Explanation: The array has been rotated 2 times.
 *
 * Input: [4, 5, 7, 9, 10, -1, 2]
 * Output: 5
 * Explanation: The array has been rotated 5 times.
 *
 * Input: [1, 3, 8, 10]
 * Output: 0
 * Explanation: The array has been not been rotated.
 */

public class RotationCount {
    public int findRotation(int[] arr) {
        int left = 0, right = arr.length - 1, mid;
        int rightNum = arr[right], leftNum = arr[left];
        while (left <= right) {
            mid = left + (right - left) / 2;
            // notice here if the mid == arr.length - 1, arr[mid] == rightNum, it won't be able to proceed to check the second statement
            if (arr[mid] > rightNum && arr[mid + 1] < leftNum) return mid + 1;
            else if (arr[mid] < rightNum) right = mid - 1; // right side is sorted, go left
            else left = mid + 1; // left side is sorted, go right
        }
        return 0;
    }

    public int findRotationWithDup(int[] arr) {
        // https://www.educative.io/courses/grokking-the-coding-interview/R1v4P0R7VZw#Similar-Problems
        // fking complicated
        return 0;
    }

    public static void main(String[] args) {
        RotationCount rotationCount = new RotationCount();
        System.out.println(rotationCount.findRotation(new int[]{10, 15, 1, 3, 8}));
        System.out.println(rotationCount.findRotation(new int[]{4, 5, 7, 9, 10, -1, 2}));
        System.out.println(rotationCount.findRotation(new int[]{1, 3, 8, 10}));
        System.out.println(rotationCount.findRotation(new int[]{10, 3, 8}));
    }
}
