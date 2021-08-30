package array;

import java.util.Arrays;

public class quickSortMedianAsPivot {

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private void quickSortRec(int[] arr, int left, int right) {
        // base case
        if (left >= right || left >= arr.length || right < 0) {
            return;
        }

        // partition
        int leftPointer = left, rightPointer = right, pivot = arr[left + (right - left) / 2];
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

        // recursion
        quickSortRec(arr, left, rightPointer);
        quickSortRec(arr, leftPointer, right);
    }

    public void quicksort(int[] unsorted) {
        // corner case
        if (unsorted == null || unsorted.length == 0) {
            return;
        }
        // recursive call quicksort on the array
        quickSortRec(unsorted, 0, unsorted.length - 1);
    }

    public static void main(String[] args) {
        quickSortMedianAsPivot solution = new quickSortMedianAsPivot();
        int[] array = new int[]{3, 2, 3};
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));

        array = new int[]{3, 2};
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));

        array = new int[]{42, 12, 89, 27, 94, 63, 3, 78};
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));

        array = new int[]{42, 89, 63, 12, 94, 27, 78, 3, 50, 36};
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
    }
}