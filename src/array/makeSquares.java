/*
 * Given a sorted array, create a new array containing squares of
 * all the numbers of the input array in the sorted order.
 *
 * Input: [-2, -1, 0, 2, 3]
 * Output: [0, 1, 4, 4, 9]
 *
 * */

package array;

public class makeSquares {

    /**
     * Move from middle to the two ends.
     *
     * @param arr the input array
     * @return the result squared integer array
     *//*

    public static int[] makeSortedSquares(int[] arr) {
        // corner cases
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        // two pointers to check the elements' absolute value
        int[] result = new int[arr.length];
        int left = 0, right = 0, index = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) {
                left = i - 1;
                right = i;
                break;
            }
        }

        while (left >= 0 && right < arr.length) {
            if (Math.abs(arr[left]) <= Math.abs(arr[right])) {
                result[index++] = arr[left] * arr[left];
                left--;
            } else {
                result[index++] = arr[right] * arr[right];
                right++;
            }
        }
        while (left >= 0) {
            result[index++] = arr[left] * arr[left];
            left--;
        }

        while (right < arr.length) {
            result[index++] = arr[right] * arr[right];
            right++;
        }

        return result;
    }
*/

    /**
     * Two Pointers.
     *
     * 0   1  2  3  4
     * -3 -1  0  2  4
     * l ->      <- r
     *
     * @param arr the input array
     * @return the squared integer array
     */
    public static int[] makeSortedSquares(int[] arr) {
        // corner case
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        // two pointers moving from the left/right side to the middle
        int[] result = new int[arr.length];
        int left = 0, right = arr.length - 1, index = arr.length - 1;
        while (left <= right) {
            if (Math.abs(arr[left]) > Math.abs(arr[right])) {
                result[index--] = arr[left] * arr[left];
                left++;
            } else {
                result[index--] = arr[right] * arr[right];
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = makeSortedSquares(new int[]{-2, -1, 0, 2, 3});
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = makeSortedSquares(new int[]{-3, -1, 0, 1, 2});
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }
}
