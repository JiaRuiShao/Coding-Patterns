// Squaring a Sorted Array
// Given a sorted array, create a new array containing squares of
// all the numbers of the input array in the sorted order.

package twopointers;

public class SquaringSortedArray {

    /**
     * Two Pointers -- Easy.
     * Find the first non-negative index and start moving two pointers in opposite direction
     * to put the squared values from smallest to largest into the output arr.     *
     *
     * TC: O(n)
     * SC: O(n)
     *
     * @param arr the input array
     * @return the squared sorted Integer arr given the input arr
     */
    public int[] squaring(int[] arr) {

        int outputIdx = 0;
        int[] squared = new int[arr.length];

        // left pointer is used to iterate negative integers
        // right pointer is used to iterate the non-negative integers
        int left, right = 0;
        for (Integer i : arr) {
            if (i < 0) {
                right++;
            } else {
                break;
            }
        }
        // the right pointer is now pointed at the 1st non-negative int in the input arr
        // and left pointer is now pointed at the smallest negative int in the input arr
        left = right - 1;
        while (left >= 0 && right < arr.length) {
            if (Math.abs(arr[left]) > arr[right]) {
                squared[outputIdx++] = arr[right] * arr[right];
                right++;
            } else {
                squared[outputIdx++] = arr[left] * arr[left];
                left--;
            }
        }

        // add the elements left into the squared arr
        while (left >= 0) {
            squared[outputIdx++] = arr[left] * arr[left];
            left--;
        }

        while (right < arr.length) {
            squared[outputIdx++] = arr[right] * arr[right];
            right++;
        }

        return squared;
    }

    /**
     * Another way to solve the question. Easier than the previous method.
     * Moving from the left and right side to the middle.
     * Put the largest square values into the output arr first,
     * then two pointers move towards each other.
     *
     * TC: O(n)
     * SC: O(n)
     *
     * @param arr the input integer arr
     * @return the squared arr given the input arr
     */
    public int[] squaring2(int[] arr) {

        if (arr == null || arr.length == 0) {
            return null;
        }

        int[] squared = new int[arr.length];
        int squaredIdx = arr.length - 1, left = 0, right = arr.length - 1, leftSquare, rightSquare;
        while (squaredIdx >= 0) {
            leftSquare = arr[left] * arr[left];
            rightSquare = arr[right] * arr[right];
            if (leftSquare >= rightSquare) {
                squared[squaredIdx--] = leftSquare;
                left++;
            } else {
                squared[squaredIdx--] = rightSquare;
                right--;
            }
        }
        return squared;
    }

    public static void main(String[] args) {
        SquaringSortedArray tp = new SquaringSortedArray();
        int[] result = tp.squaring(new int[]{-2, -1, 0, 2, 3});
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
        result = tp.squaring2(new int[]{-2, -1, 0, 2, 3});
        for (int num : result) {
            System.out.print(num + " ");
        }
        // 0 1 4 4 9
        System.out.println();

        result = tp.squaring(new int[]{-3, -1, 0, 1, 2});
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
        result = tp.squaring2(new int[]{-3, -1, 0, 1, 2});
        for (int num : result) {
            System.out.print(num + " ");
        }
        // 0 1 1 4 9
        System.out.println();

    }
}
