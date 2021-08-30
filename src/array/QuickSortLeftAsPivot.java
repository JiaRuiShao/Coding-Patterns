package array;

import java.util.Arrays;

/**
 * QuickSort in Ascending Order.
 * Choose the leftmost element as the pivot (Hoare's algorithm).
 */
public class QuickSortLeftAsPivot {

    /**
     * Private helper function to swap two elements.
     * @param arr input array
     * @param i first index
     * @param j second index
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Choose the left most element as the pivot.
     * @param arr input array
     * @param left left boundary
     * @param right right boundary
     */
    public void quicksortRec(int[] arr, int left, int right) {

        // base case
        if (left >= right || left < 0 || right >= arr.length) {
            return;
        }

        // partition
        int leftPointer = left + 1, rightPointer = right, pivot = arr[left];
        while (leftPointer <= rightPointer) { // ** here MUST have equal sign! What if only two elements left and they are not in the right order
            while (leftPointer <= right && arr[leftPointer] < pivot) {
                leftPointer++;
            }
            while (rightPointer >= left && arr[rightPointer] > pivot) {
                rightPointer--;
            }
            if (leftPointer <= rightPointer) { // *** here MUST have equal sign! What if two equal elements left
                swap(arr, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }
        // terminate when leftPointer > rightPointer
        // swap the pivot and the element who is less than the pivot
        swap(arr, left, rightPointer);

        // recursion
        quicksortRec(arr, left, rightPointer - 1);
        quicksortRec(arr, rightPointer + 1, right);
    }

    public void quicksort(int[] unsorted) {
        if (unsorted == null || unsorted.length == 0) {
            return;
        }
        quicksortRec(unsorted, 0, unsorted.length - 1);
    }

    public boolean isSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        QuickSortLeftAsPivot solution = new QuickSortLeftAsPivot();
        int[] array = new int[] {55, 23, 26, 2, 18, 3, 23, 8, 2, 78};
        System.out.printf("Array before sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n", solution.isSorted(array));
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));
        assert solution.isSorted(array);

        array = new int[] {2};
        System.out.printf("Array before sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n", solution.isSorted(array));
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));
        assert solution.isSorted(array);

        array = new int[] {2, 2};
        System.out.printf("Array before sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n", solution.isSorted(array));
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));
        assert solution.isSorted(array);

        array = new int[] {3, 2};
        System.out.printf("Array before sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n", solution.isSorted(array));
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));
        assert solution.isSorted(array);

        array = new int[] {42, 12, 89, 27, 94, 63, 3, 78};
        System.out.printf("Array before sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n", solution.isSorted(array));
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));
        assert solution.isSorted(array);

        array = new int[] {42, 89, 63, 12, 94, 27, 78, 3, 50, 36};
        System.out.printf("Array before sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n", solution.isSorted(array));
        solution.quicksort(array);
        System.out.printf("Array after sorting: %s%n", Arrays.toString(array));
        System.out.printf("The array is sorted: %b%n%n", solution.isSorted(array));
        assert solution.isSorted(array);
    }

}
