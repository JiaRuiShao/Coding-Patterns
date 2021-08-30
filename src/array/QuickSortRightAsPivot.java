package array;

import java.util.Arrays;

public class QuickSortRightAsPivot {

    public boolean isSorted(int[] arr) {
        // corner cases
        if (arr == null || arr.length == 0) {
            return false;
        }

        // loop to check whether the arr is in desc/asc order
        for (int i = 1; i < arr.length; i++) {
            // check ascending order
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quicksort(int[] unsorted) {
        // corner case
        if (unsorted == null || unsorted.length == 0) {
            return;
        }
        quickSortRec(unsorted, 0, unsorted.length - 1);
    }

    private void quickSortRec(int[] arr, int left, int right) {
        // base case
        if (left >= right || left >= arr.length || right < 0) {
            return;
        }

        // partition using two pointers
        int leftPointer = left, rightPointer = right - 1, pivot = arr[right];
        while (leftPointer <= rightPointer) {
            while (leftPointer <= right && arr[leftPointer] < pivot) {
                leftPointer++;
            }
            while (rightPointer >= left && arr[rightPointer] > pivot) {
                rightPointer--;
            }
            if (leftPointer <= rightPointer) {
                swap(arr, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }

        // place the pivot to its right position
        swap(arr, right, leftPointer);

        // recursion
        quickSortRec(arr, left, leftPointer - 1);
        quickSortRec(arr, leftPointer + 1, right);
    }

    public static void main(String[] args) {
        QuickSortRightAsPivot solution = new QuickSortRightAsPivot();
        int[] array = new int[]{3, 2, 3};
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));

        array = new int[]{3, 2};
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));

        array = new int[]{42, 12, 89, 27, 94, 63, 3, 78};
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));

        array = new int[]{42, 89, 63, 12, 94, 27, 78, 3, 50, 36};
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));
    }
}