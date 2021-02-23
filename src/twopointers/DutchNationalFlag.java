// Dutch National Flag Problem (medium)
// Given an array containing 0s, 1s and 2s, sort the array in-place.
// You should treat numbers of the array as objects, hence,
// we can’t count 0s, 1s, and 2s to recreate the array.
// The flag of the Netherlands consists of three colors: red, white and blue;
// and since our input array also consists of three different numbers
// that is why it is called Dutch National Flag problem.

package twopointers;

public class DutchNationalFlag {
    /**
     * Sorting the array in-place -- Medium.
     * Put all the 0s in the left hand side of left pointer
     * and all the 2s in the right hand side of the right pointer
     * the 1s are between the left and right pointers
     *
     * TC: O(n)
     * SC: O(1)
     *
     * @param arr the arr to be sorted
     */
    public void sort(int[] arr) {
        int left = 0, right = arr.length - 1;
        int i = 0;
        while (i <= right) {
            if (arr[i] < 1) {
                swap(arr, i, left);
                left++;
                i++;
            } else if (arr[i] > 1) {
                swap(arr, i, right);
                right--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i != j && arr[i] != arr[j]) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        DutchNationalFlag tp = new DutchNationalFlag();
        int[] arr = new int[]{1, 0, 2, 1, 0};
        tp.sort(arr);
        for (int num : arr) System.out.print(num + " ");
        System.out.println();

        arr = new int[]{2, 2, 0, 1, 2, 0};
        tp.sort(arr);
        for (int num : arr) System.out.print(num + " ");
    }
}
