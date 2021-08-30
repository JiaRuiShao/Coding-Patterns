/* Move all zeros to the left of an array while maintaining its order.
Given an integer array, move all elements that are 0 to the left
while maintaining the order of other elements in the array.
The array has to be modified in-place. */

package array;

import java.util.Arrays;

public class MoveZeros {
    public void moveZerosToLeft(int[] arr) {
        // corner case
        if (arr == null || arr.length < 2) return;

        // pointer i is to keep track of next zeros, & j is to track next non-zeros
        int i = arr.length - 1, j = i;
        while (i >= 0 && j >= 0) {
            while (arr[i] != 0) i--;
            if (j >= i) j = i - 1;
            while (j >= 0 && arr[j] == 0) j--;
            if (j >= 0) {
                swap(arr, i, j);
                i--;
                j--;
            }
        }
    }

    public void moveZerosToLeft2(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int writeIndex = arr.length - 1;
        int readIndex = arr.length - 1;

        while(readIndex >= 0) {
            if(arr[readIndex] != 0) {
                arr[writeIndex] = arr[readIndex];
                writeIndex--;
            }
            readIndex--;
        }

        while(writeIndex >= 0) arr[writeIndex--] = 0;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        MoveZeros mz = new MoveZeros();
        int[] v = new int[]{1, 10, 20, 0, 59, 63, 0, 88, 0};
        System.out.println("Original Array: " + Arrays.toString(v));
        mz.moveZerosToLeft2(v);
        System.out.println("After Moving Zeroes to Left: " + Arrays.toString(v));
        System.out.println();

        v = new int[]{0, 0, 1, 10, -1, 11, 5, -7, 25, -35};
        System.out.println("Original Array: " + Arrays.toString(v));
        mz.moveZerosToLeft2(v);
        System.out.println("After Moving Zeroes to Left: " + Arrays.toString(v));
        System.out.println();
    }
}
