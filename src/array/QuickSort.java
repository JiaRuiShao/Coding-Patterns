package array;

import java.util.Arrays;

/**
 * In-place QuickSort using Hoare's algorithm (pick the first element as the pivot).
 */
public class QuickSort {

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void quickSortRec(int[] arr, int left, int right) {
        int lp = left + 1, rp = right, pivot = arr[left];

        while (lp <= rp) {
            while (arr[lp] < pivot) { // in the right position
                lp++;
            } // terminate the while loop when arr[left] >= pivot

            while (arr[rp] > pivot) { // in the right position
                rp--;
            } // terminate the while loop when arr[right] <= pivot

            if (lp <= rp && lp <= right) {
                swap(arr, lp, rp);
                lp++;
                rp--;
            }
        }

        // the lp now point at the left-most element >= pivot
        // the rp now point at the right-most element <= pivot

        // Put the pivot to its right position so that all the elements index from 0 to the pivot are <= pivot
        // & all elements from the pivot the end of the array are > pivot.
        swap(arr, left, rp);

        // recursion
        if (left < rp - 1) { // go left side
            quickSortRec(arr, left, rp - 1);
        }
        if (rp + 1 < right) { // go right side
            quickSortRec(arr, rp + 1, right);
        }
    }

    public void quickSort(int[] arr) {
        quickSortRec(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] a = new int[] {55, 23, 26, 2, 18, 78, 23, 8, 2, 3};

        System.out.print("Before Sorting\n");
        System.out.print(Arrays.toString(a) + "\n");

        QuickSort qs = new QuickSort();
        qs.quickSort(a);

        System.out.print("After Sorting\n");
        System.out.print(Arrays.toString(a));
    }

}
